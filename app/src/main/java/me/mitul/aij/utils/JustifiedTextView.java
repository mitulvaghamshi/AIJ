package me.mitul.aij.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import java.util.ArrayList;
import java.util.List;

public class JustifiedTextView extends View {
    private boolean hasTextBeenDrown = false;
    private Context context;
    private TextPaint textPaint;
    private int lineSpace = 0;
    private int lineHeight = 0;
    private int textAreaWidth = 0;
    private int measuredViewHeight = 0;
    private int measuredViewWidth = 0;
    private String text;
    private List<String> lineList = new ArrayList<>();

    public JustifiedTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        constructor(context, attrs);
    }

    public JustifiedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        constructor(context, attrs);
    }

    public JustifiedTextView(Context context) {
        super(context);
        constructor(context, null);
    }

    private void constructor(Context context, AttributeSet attrs) {
        this.context = context;
        XmlToClassAttribHandler xmlParser = new XmlToClassAttribHandler(this.context, attrs);
        initTextPaint();

        if (attrs != null) {
            String text;
            int textColor;
            int textSize;
            int textSizeUnit;
            text = xmlParser.getTextValue();
            textColor = xmlParser.getColorValue();
            textSize = xmlParser.getTextSize();
            textSizeUnit = xmlParser.gettextSizeUnit();
            setText(text);
            setTextColor(textColor);
            if (textSizeUnit == -1) setTextSize(textSize);
            else setTextSize(textSizeUnit, textSize);
//			setText(XmlToClassAttribHandler.GetAttributeStringValue(mContext, attrs, namespace, key, ""));
            setTypeFace(xmlParser.getTypeFace());
            //setTypeFace(Typeface.create("serif", Typeface.NORMAL));
        }

        ViewTreeObserver observer = getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (hasTextBeenDrown) return;
                hasTextBeenDrown = true;
                setTextAreaWidth(getWidth() - (getPaddingLeft() + getPaddingRight()));
                calculate();
            }
        });
    }

    private void calculate() {
        setLineHeight(getTextPaint());
        lineList.clear();
        lineList = divideOriginalTextToStringLineList(getText());
        setMeasuredDimentions(lineList.size(), getLineHeight(), getLineSpace());
        measure(getMeasuredViewWidth(), getMeasuredViewHeight());
    }

    private void initTextPaint() {
        textPaint = new TextPaint(TextPaint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Align.LEFT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getMeasuredViewWidth() > 0) {
            requestLayout();
            setMeasuredDimension(getMeasuredViewWidth(), getMeasuredViewHeight());
        } else super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int rowIndex = getPaddingTop();
        int colIndex = 0;
        if (getAlignment() == Align.RIGHT) colIndex = getPaddingLeft() + getTextAreaWidth();
        else colIndex = getPaddingLeft();
        for (int i = 0; i < lineList.size(); i++) {
            rowIndex += getLineHeight() + getLineSpace();
            canvas.drawText(lineList.get(i), colIndex, rowIndex, getTextPaint());
        }
    }

    private List<String> divideOriginalTextToStringLineList(String originalText) {
        ArrayList<String> listStringLine = new ArrayList<String>();
        String line = "";
        float textWidth;
        String[] paragraphList = originalText.split("\n");
        for (String paragraph : paragraphList) {
            String[] wordList = paragraph.split(" ");
            for (int i = 0; i < wordList.length; i++) {
                line += wordList[i] + " ";
                textWidth = getTextPaint().measureText(line);
                if (getTextAreaWidth() == textWidth) {
                    listStringLine.add(line);
                    line = "";//make line clear
                    continue;
                } else if (getTextAreaWidth() < textWidth) {
                    int lastWordCount = wordList[i].length();
                    line = line.substring(0, line.length() - lastWordCount - 1);
                    if (line.trim().length() == 0)
                        continue;
                    line = justifyTextLine(textPaint, line.trim(), getTextAreaWidth());
                    listStringLine.add(line);
                    line = "";
                    i++;
                    continue;
                }

                if (i == wordList.length - 1) {
                    listStringLine.add(line);
                    line = "";
                }
            }
        }
        return listStringLine;
    }

    private String justifyTextLine(TextPaint textPaint, String lineString, int textAreaWidth) {
        int gapIndex = 0;
        float lineWidth = textPaint.measureText(lineString);
        while (lineWidth < textAreaWidth && lineWidth > 0) {
            gapIndex = lineString.indexOf(" ", gapIndex + 2);
            if (gapIndex == -1) {
                gapIndex = 0;
                gapIndex = lineString.indexOf(" ", gapIndex + 1);
                if (gapIndex == -1) return lineString;
            }
            lineString = lineString.substring(0, gapIndex) + "  " + lineString.substring(gapIndex + 1);
            lineWidth = textPaint.measureText(lineString);
        }
        return lineString;
    }

    private void setLineHeight(TextPaint textPaint) {
        Rect bounds = new Rect();
        String sampleStr = "bla bla, who is the crazy world of all";
        textPaint.getTextBounds(sampleStr, 0, sampleStr.length(), bounds);
        setLineHeight(bounds.height());
    }

    public void setMeasuredDimentions(int lineListSize, int lineHeigth, int lineSpace) {
        int height = lineListSize * (lineHeigth + lineSpace) + lineSpace;
        height += getPaddingRight() + getPaddingLeft();
        setMeasuredViewHeight(height);
        setMeasuredViewWidth(getWidth());
    }

    private int getTextAreaWidth() {
        return textAreaWidth;
    }

    private void setTextAreaWidth(int textAreaWidth) {
        this.textAreaWidth = textAreaWidth;
    }

    private int getLineHeight() {
        return lineHeight;
    }

    private void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    private int getMeasuredViewHeight() {
        return measuredViewHeight;
    }

    private void setMeasuredViewHeight(int measuredViewHeight) {
        this.measuredViewHeight = measuredViewHeight;
    }

    private int getMeasuredViewWidth() {
        return measuredViewWidth;
    }

    private void setMeasuredViewWidth(int measuredViewWidth) {
        this.measuredViewWidth = measuredViewWidth;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        calculate();
        invalidate();
    }

    public void setText(int resId) {
        setText(context.getResources().getString(resId));
    }

    public Typeface getTypeFace() {
        return getTextPaint().getTypeface();
    }

    public void setTypeFace(Typeface typeFace) {
        getTextPaint().setTypeface(typeFace);
    }

    public float getTextSize() {
        return getTextPaint().getTextSize();
    }

    private void setTextSize(float textSize) {
        getTextPaint().setTextSize(textSize);
        calculate();
        invalidate();
    }

    public void setTextSize(int unit, float textSize) {
        textSize = TypedValue.applyDimension(unit, textSize, context.getResources().getDisplayMetrics());
        setTextSize(textSize);
    }

    public TextPaint getTextPaint() {
        return textPaint;
    }

    public void setTextPaint(TextPaint textPaint) {
        this.textPaint = textPaint;
    }

    public void setLineSpacing(int lineSpace) {
        this.lineSpace = lineSpace;
        invalidate();
    }

    public int getTextColor() {
        return getTextPaint().getColor();
    }

    public void setTextColor(int textColor) {
        getTextPaint().setColor(textColor);
        invalidate();
    }

    public int getLineSpace() {
        return lineSpace;
    }

    public Align getAlignment() {
        return getTextPaint().getTextAlign();
    }

    public void setAlignment(Align align) {
        getTextPaint().setTextAlign(align);
        invalidate();
    }
}
