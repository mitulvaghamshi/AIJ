package me.mitul.aij.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import me.mitul.aij.model.AllClosingList;
import me.mitul.aij.R;

public class AdapterAllClosing extends BaseAdapter {
    private final Activity activity;
    private final ArrayList<AllClosingList> list;

    public AdapterAllClosing(Activity activity, ArrayList<AllClosingList> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int index) {
        return 0L;
    }

    @Override
    public Object getItem(int index) {
        return list.get(index);
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        View row = view;
        ViewAllClosingHolder holder;
        if (row == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(R.layout.closing_list_listview_items, null);
            holder = new ViewAllClosingHolder(row);
            row.setTag(holder);
        } else holder = (ViewAllClosingHolder) row.getTag();
        holder.closingTextViewBranchName.setText(list.get(index).getBranchName());
        holder.closingTextViewTfwsValue.setText(String.valueOf(list.get(index).getTfwsValue()));
        holder.closingTextViewOpenValue.setText(String.valueOf(list.get(index).getOpenValue()));
        holder.closingTextViewSebcValue.setText(String.valueOf(list.get(index).getSebcValue()));
        holder.closingTextViewEbcValue.setText(String.valueOf(list.get(index).getEbcValue()));
        holder.closingTextViewScValue.setText(String.valueOf(list.get(index).getScValue()));
        holder.closingTextViewStValue.setText(String.valueOf(list.get(index).getStValue()));
        return view;
    }

    private static class ViewAllClosingHolder {
        private final TextView closingTextViewBranchName;
        private final TextView closingTextViewTfwsValue;
        private final TextView closingTextViewOpenValue;
        private final TextView closingTextViewEbcValue;
        private final TextView closingTextViewSebcValue;
        private final TextView closingTextViewScValue;
        private final TextView closingTextViewStValue;

        private ViewAllClosingHolder(View view) {
            closingTextViewBranchName = view.findViewById(R.id.all_closing_tv_branch);
            closingTextViewTfwsValue = view.findViewById(R.id.all_closing_tv_tfw);
            closingTextViewOpenValue = view.findViewById(R.id.all_closing_tv_open);
            closingTextViewSebcValue = view.findViewById(R.id.all_closing_tv_sebc);
            closingTextViewEbcValue = view.findViewById(R.id.all_closing_tv_ebc);
            closingTextViewScValue = view.findViewById(R.id.all_closing_tv_sc);
            closingTextViewStValue = view.findViewById(R.id.all_closing_tv_st1);
        }
    }
}
