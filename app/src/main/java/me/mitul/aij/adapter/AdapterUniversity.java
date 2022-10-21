package me.mitul.aij.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import me.mitul.aij.model.University;
import me.mitul.aij.R;

public class AdapterUniversity extends BaseAdapter {
    private final Activity activity;
    private final ArrayList<University> list;

    public AdapterUniversity(Activity activity, ArrayList<University> list) {
        this.activity = activity;
        this.list = list;
    }

    public int getCount() {
        return list.size();
    }

    public Object getItem(int index) {
        return list.get(index);
    }

    public long getItemId(int index) {
        return 0L;
    }

    public View getView(int index, View view, ViewGroup viewGroup) {
        View row = view;
        ViewUniversityHolder holder;
        if (row == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(R.layout.simple_one_tv_listview_items, null);
            holder = new ViewUniversityHolder(row);
            row.setTag(holder);
        } else holder = (ViewUniversityHolder) row.getTag();
        holder.universityID.setText(String.valueOf(list.get(index).getUniversityID()));
        holder.universityName.setText(list.get(index).getUniversityName());
        return row;
    }

    private static class ViewUniversityHolder {
        private final TextView universityID;
        private final TextView universityName;

        private ViewUniversityHolder(View view) {
            universityID = view.findViewById(R.id.list_simple_public_id);
            universityName = view.findViewById(R.id.list_simple_public_name);
        }
    }
}
