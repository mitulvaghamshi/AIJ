package me.mitul.aij.frags;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import me.mitul.aij.helper.HelperBankBranch;
import me.mitul.aij.R;
import me.mitul.aij.adapter.AdapterExpandableList;
import me.mitul.aij.model.Common;

public class FragmentLeft extends Fragment {
    private List<String> list;
    private ExpandableListView expListView;
    private List<String> cityList;
    private HashMap<String, List<Common>> bankBranchMap;
    private EditText edSearch;
    private final HelperBankBranch dbHelper = new HelperBankBranch(getContext());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_common_listview, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        expListView = getActivity().findViewById(R.id.expandableListView);
        expListView.setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.common_listview).setVisibility(View.GONE);
        edSearch = getActivity().findViewById(R.id.edSearchCommon);
        prepareListData();
        ExpandableListAdapter listAdapter = new AdapterExpandableList(getActivity(), cityList, bankBranchMap);
        expListView.setAdapter(listAdapter);
        expListView.setTag(-1);
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String str = edSearch.getText().toString().toLowerCase(Locale.getDefault());
                ArrayList<String> list1 = new ArrayList<>();
                expListView.setAdapter(new AdapterExpandableList(getActivity(), list1, bankBranchMap));
                if (!str.isEmpty()) {
                    for (int i = 0; i < list.size(); i++)
                        if (list.get(i).toLowerCase(Locale.getDefault()).startsWith(str) || (list.get(i)).toLowerCase(Locale.getDefault()).contains(str))
                            list1.add(list.get(i));
                    expListView.setAdapter(new AdapterExpandableList(getActivity(), list1, bankBranchMap));
                } else
                    expListView.setAdapter(new AdapterExpandableList(getActivity(), list, bankBranchMap));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        expListView.setOnGroupClickListener((parent, view, groupPosition, id) -> {
            //   Toast.makeText(getApplicationContext(), "Group Clicked " + listDataHeader.get(groupPosition), Toast.LENGTH_SHORT).show();
            return false;
        });

        expListView.setOnGroupExpandListener(groupPosition -> {
            int tempPos = (int) expListView.getTag();
            if (tempPos != groupPosition && tempPos != -1) expListView.collapseGroup(tempPos);
            expListView.setTag(groupPosition);
            //   Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT).show();
        });

        expListView.setOnGroupCollapseListener(groupPosition -> {
            //    Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();
        });

        expListView.setOnChildClickListener((parent, view, groupPosition, childPosition, id) -> {
            PopupMenu popup = new PopupMenu(getActivity(), view);
            popup.getMenu().add(cityList.get(groupPosition));
            popup.getMenu().add((bankBranchMap.get(cityList.get(groupPosition)).get(childPosition)).getAddress());
            //popup.getMenuInflater().inflate(R.menu.popup_menu,);
            popup.setOnMenuItemClickListener(item -> {
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            });
            popup.show();
            return false;
        });
    }

    private void prepareListData() {
        cityList = dbHelper.selectCityForBankBranch();
        bankBranchMap = new HashMap<>();
        for (String name : cityList) {
            bankBranchMap.put(name, dbHelper.selectBankBranch(name));
        }
    }
}
