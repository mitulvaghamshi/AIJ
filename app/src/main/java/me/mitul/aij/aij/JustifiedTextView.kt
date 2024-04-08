package me.mitul.aij.aij

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint.Align
import android.graphics.Rect
import android.graphics.Typeface
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener

class JustifiedTextView : View {
    private var hasTextBeenDrown = false
    private var context: Context? = null
    private var textPaint: TextPaint? = null
    private var lineSpace = 0
    private var lineHeight = 0
    private var textAreaWidth = 0
    private var measuredViewHeight = 0
    private var measuredViewWidth = 0

    var text: String? = null
        set(text) {
            field = text
            calculate()
            invalidate()
        }

    private var lineList: MutableList<String> = ArrayList()

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) :
            super(context, attrs, defStyle) {
        constructor(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        constructor(context, attrs)
    }

    constructor(context: Context) : super(context) {
        constructor(context, null)
    }

    private fun constructor(context: Context, attrs: AttributeSet?) {
        this.context = context
        val xmlParser = XmlToClassAttribHandler(this.context, attrs)
        initTextPaint()
        if (attrs != null) {
            val text: String = xmlParser.textValue
            val textSize: Int = xmlParser.textSize
            val textColor: Int = xmlParser.colorValue
            val textSizeUnit: Int = xmlParser.gettextSizeUnit()
            this.text = text
            this.textColor = textColor
            if (textSizeUnit == -1) this.textSize = textSize.toFloat()
            else setTextSize(textSizeUnit, textSize.toFloat())
            typeFace = xmlParser.typeFace
            //setTypeFace(Typeface.create("serif", Typeface.NORMAL));
        }
        val observer = getViewTreeObserver()
        observer.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (hasTextBeenDrown) return
                hasTextBeenDrown = true
                textAreaWidth = width - (getPaddingLeft() + getPaddingRight())
                calculate()
            }
        })
    }

    private fun calculate() {
        setLineHeight(textPaint)
        lineList.clear()
        lineList = divideOriginalTextToStringLineList(text)
        setMeasuredDimentions(lineList.size, lineHeight, lineSpace)
        measure(measuredViewWidth, measuredViewHeight)
    }

    private fun initTextPaint() {
        textPaint = TextPaint(TextPaint.ANTI_ALIAS_FLAG)
        textPaint!!.textAlign = Align.LEFT
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (measuredViewWidth > 0) {
            requestLayout()
            setMeasuredDimension(measuredViewWidth, measuredViewHeight)
        } else super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        var rowIndex = paddingTop
        val colIndex: Int =
            if (alignment == Align.RIGHT) getPaddingLeft() + textAreaWidth
            else getPaddingLeft()
        for (i in lineList.indices) {
            rowIndex += lineHeight + lineSpace
            canvas.drawText(lineList[i], colIndex.toFloat(), rowIndex.toFloat(), textPaint!!)
        }
    }

    private fun divideOriginalTextToStringLineList(originalText: String?): MutableList<String> {
        val listStringLine = ArrayList<String>()
        var line = ""
        var textWidth: Float
        val paragraphList = originalText!!.split("\\n".toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()
        for (paragraph in paragraphList) {
            val wordList = paragraph.split("\\s".toRegex())
                .dropLastWhile { it.isEmpty() }.toTypedArray()
            var i = 0
            while (i < wordList.size) {
                line += wordList[i] + " "
                textWidth = textPaint!!.measureText(line)
                if (textAreaWidth.toFloat() == textWidth) {
                    listStringLine.add(line)
                    line = "" //make line clear
                    i++
                    continue
                } else if (textAreaWidth < textWidth) {
                    val lastWordCount = wordList[i].length
                    line = line.substring(0, line.length - lastWordCount - 1)
                    if (line.trim { it <= ' ' }.isEmpty()) {
                        i++
                        continue
                    }
                    line = justifyTextLine(textPaint, line.trim { it <= ' ' }, textAreaWidth)
                    listStringLine.add(line)
                    line = ""
                    i++
                    i++
                    continue
                }
                if (i == wordList.size - 1) {
                    listStringLine.add(line)
                    line = ""
                }
                i++
            }
        }
        return listStringLine
    }

    private fun justifyTextLine(
        textPaint: TextPaint?,
        lineString: String,
        textAreaWidth: Int,
    ): String {
        var lineString = lineString
        var gapIndex = 0
        var lineWidth = textPaint!!.measureText(lineString)
        while (lineWidth < textAreaWidth && lineWidth > 0) {
            gapIndex = lineString.indexOf(" ", gapIndex + 2)
            if (gapIndex == -1) {
                gapIndex = 0
                gapIndex = lineString.indexOf(" ", gapIndex + 1)
                if (gapIndex == -1) return lineString
            }
            lineString = lineString.substring(0, gapIndex) + "  " +
                    lineString.substring(gapIndex + 1)
            lineWidth = textPaint.measureText(lineString)
        }
        return lineString
    }

    private fun setLineHeight(textPaint: TextPaint?) {
        val bounds = Rect()
        val sampleStr = "bla bla, who is the crazy world of all"
        textPaint!!.getTextBounds(sampleStr, 0, sampleStr.length, bounds)
        setLineHeight(bounds.height())
    }

    fun setMeasuredDimentions(lineListSize: Int, lineHeigth: Int, lineSpace: Int) {
        var height = lineListSize * (lineHeigth + lineSpace) + lineSpace
        height += getPaddingRight() + getPaddingLeft()
        measuredViewHeight = height
        measuredViewWidth = width
    }

    private fun setLineHeight(lineHeight: Int) {
        this.lineHeight = lineHeight
    }

    fun setText(resId: Int) {
        text = context!!.resources.getString(resId)
    }

    var typeFace: Typeface?
        get() = textPaint!!.typeface
        set(typeFace) {
            textPaint!!.setTypeface(typeFace)
        }
    var textSize: Float
        get() = textPaint!!.textSize
        private set(textSize) {
            textPaint!!.textSize = textSize
            calculate()
            invalidate()
        }

    fun setTextSize(unit: Int, textSize: Float) {
        var textSize = textSize
        textSize = TypedValue.applyDimension(unit, textSize, context!!.resources.displayMetrics)
        this.textSize = textSize
    }

    fun setLineSpacing(lineSpace: Int) {
        this.lineSpace = lineSpace
        invalidate()
    }

    var textColor: Int
        get() = textPaint!!.color
        set(textColor) {
            textPaint!!.setColor(textColor)
            invalidate()
        }
    var alignment: Align?
        get() = textPaint!!.textAlign
        set(align) {
            textPaint!!.textAlign = align
            invalidate()
        }
}

internal class XmlToClassAttribHandler(
    private val mContext: Context?,
    private val mAttributeSet: AttributeSet?,
) {
    private val KEY_TEXT_SIZE = "textSize"
    private val mRes: Resources = mContext!!.resources
    private val namespace = "http://me.mitul.aij"

    val textValue: String
        get() {
            val KEY_TEXT = "text"
            var value = mAttributeSet!!.getAttributeValue(namespace, KEY_TEXT) ?: return ""
            if (value.length > 1 && value[0] == '@' && value.contains("@string/")) {
                val resId = mRes.getIdentifier(
                    mContext!!.packageName + ":" + value.substring(1),
                    null,
                    null
                )
                value = mRes.getString(resId)
            }
            return value
        }

    val typeFace: Typeface = Typeface.createFromAsset(mContext!!.assets, "font/f011.ttf")

    val colorValue: Int
        get() {
            val KEY_TEXT_COLOR = "textColor"
            val value = mAttributeSet!!.getAttributeValue(namespace, KEY_TEXT_COLOR)
            var color = Color.BLACK
            if (value == null) return color
            if (value.length > 1 && value[0] == '@' && value.contains("@color/")) {
                val resId = mRes.getIdentifier(
                    mContext!!.packageName + ":" + value.substring(1),
                    null,
                    null
                )
                color = mRes.getColor(resId)
                return color
            }
            color = try {
                Color.parseColor(value)
            } catch (e: Exception) {
                return Color.BLACK
            }
            return color
        }
    val textSize: Int
        get() {
            var textSize = 30
            val value =
                mAttributeSet!!.getAttributeValue(namespace, KEY_TEXT_SIZE) ?: return textSize
            if (value.length > 1 && value[0] == '@' && value.contains("@dimen/")) {
                val resId = mRes.getIdentifier(
                    mContext!!.packageName + ":" + value.substring(1),
                    null,
                    null
                )
                textSize = mRes.getDimensionPixelSize(resId)
                return textSize
            }
            textSize = try {
                value.substring(0, value.length - 2).toInt()
            } catch (e: Exception) {
                return 14
            }
            return textSize
        }

    fun gettextSizeUnit(): Int {
        val value = mAttributeSet!!.getAttributeValue(namespace, KEY_TEXT_SIZE)
            ?: return TypedValue.COMPLEX_UNIT_SP
        try {
            val type = value.substring(value.length - 2)
            when (type) {
                "dp" -> return TypedValue.COMPLEX_UNIT_DIP
                "sp" -> return TypedValue.COMPLEX_UNIT_SP
                "pt" -> return TypedValue.COMPLEX_UNIT_PT
                "mm" -> return TypedValue.COMPLEX_UNIT_MM
                "in" -> return TypedValue.COMPLEX_UNIT_IN
                "px" -> return TypedValue.COMPLEX_UNIT_PX
            }
        } catch (e: Exception) {
            return -1
        }
        return -1
    }
}
