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

public class AdapterIntake extends BaseAdapter {
    private final Activity activity;
    private final ArrayList<Collage> list;

    public AdapterIntake(Activity activity, ArrayList<Collage> list) {
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
        ViewIntakeHolder holder;
        if (view == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(R.layout.intake_list_listview_items, null);
            holder = new ViewIntakeHolder(row);
            row.setTag(holder);
        } else holder = (ViewIntakeHolder) row.getTag();
        holder.txtClgDetailListBranch.setText(this.list.get(index).getLvBranch());
        holder.txtClgDetailListSeat.setText(String.valueOf(this.list.get(index).getLvSeat()));
        holder.txtClgDetailListVecent.setText(String.valueOf(this.list.get(index).getLvVacant()));
        return view;
    }

    private static class ViewIntakeHolder {
        private final TextView txtClgDetailListBranch;
        private final TextView txtClgDetailListSeat;
        private final TextView txtClgDetailListVecent;

        private ViewIntakeHolder(View view) {
            txtClgDetailListBranch = view.findViewById(R.id.intake_detail_listview_branch);
            txtClgDetailListSeat = view.findViewById(R.id.intake_detail_listview_seat);
            txtClgDetailListVecent = view.findViewById(R.id.intake_detail_listview_vacant);
        }
    }
}
