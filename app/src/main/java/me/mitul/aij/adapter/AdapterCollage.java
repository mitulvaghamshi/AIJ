package me.mitul.aij.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import me.mitul.aij.model.Collage;
import me.mitul.aij.R;

public class AdapterCollage extends BaseAdapter {
    private final Activity activity;
    private final ArrayList<Collage> list;

    public AdapterCollage(Activity activity, ArrayList<Collage> list) {
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
        ViewCollageHolder holder;
        if (row == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(R.layout.collage_list_listview_items, null);
            holder = new ViewCollageHolder(row);
            row.setTag(holder);
        } else holder = (ViewCollageHolder) row.getTag();
        holder.txtID.setText(String.valueOf(this.list.get(index).getCollageId()));
        holder.txtName.setText(String.valueOf(this.list.get(index).getCollageName()));
        holder.txtCollageFees.setText(String.valueOf(this.list.get(index).getFees()));
        holder.txtCollageHostel.setText(this.list.get(index).getHostel());
        holder.txtCollageBranches.setText(this.list.get(index).getBranches());
        return row;
    }

    private static class ViewCollageHolder {
        private final TextView txtID;
        private final TextView txtName;
        private final TextView txtCollageFees;
        private final TextView txtCollageHostel;
        private final TextView txtCollageBranches;

        private ViewCollageHolder(View view) {
            txtID = view.findViewById(R.id.collage_list_item_collage_id);
            txtName = view.findViewById(R.id.collage_list_item_collage_name);
            txtCollageFees = view.findViewById(R.id.collage_list_item_collage_fees);
            txtCollageHostel = view.findViewById(R.id.collage_list_item_collage_hostel);
            txtCollageBranches = view.findViewById(R.id.collage_list_item_collage_branches);
        }
    }
}
