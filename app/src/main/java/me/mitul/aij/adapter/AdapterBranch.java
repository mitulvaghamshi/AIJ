package me.mitul.aij.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import me.mitul.aij.model.Branch;
import me.mitul.aij.R;

public class AdapterBranch extends BaseAdapter {
    private final Activity activity;
    private final ArrayList<Branch> list;

    public AdapterBranch(Activity activity, ArrayList<Branch> list) {
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
        ViewBranchHolder holder;
        if (row == null) {
            LayoutInflater layoutInflater = activity.getLayoutInflater();
            row = layoutInflater.inflate(R.layout.branch_list_listview_items, null);
            holder = new ViewBranchHolder(row);
            row.setTag(holder);
        } else holder = (ViewBranchHolder) row.getTag();
        holder.txtBranchID.setText(String.valueOf(list.get(index).getBranchId()));
        holder.txtBranchNameName.setText(this.list.get(index).getBranchName());
        holder.txtCollageNum.setText(String.format(
                Locale.getDefault(), "Found In %d Collages",
                this.list.get(index).getCollegeNumber())
        );
        return row;
    }

    private static class ViewBranchHolder {
        private final TextView txtBranchID;
        private final TextView txtBranchNameName;
        private final TextView txtCollageNum;

        private ViewBranchHolder(View view) {
            txtBranchID = view.findViewById(R.id.branch_list_item_branch_id);
            txtBranchNameName = view.findViewById(R.id.branch_list_item_branch_name);
            txtCollageNum = view.findViewById(R.id.branch_list_items_collage_count_number);
        }
    }
}
