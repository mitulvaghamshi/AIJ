package me.mitul.aij.home;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
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

public class BankBranchListActivity extends Activity {
    private ExpandableListView expListView;
    //    private List<String> listDataHeader;
    private HashMap<String, List<Common>> listDataChild;
    private EditText edSearch;
    private final HelperBankBranch mDbHelper = new HelperBankBranch(BankBranchListActivity.this);
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_listview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        expListView = findViewById(R.id.expandableListView);
        expListView.setVisibility(View.VISIBLE);
        findViewById(R.id.common_listview).setVisibility(View.GONE);
        edSearch = findViewById(R.id.edSearchCommon);
        prepareListData();
        ExpandableListAdapter listAdapter = new AdapterExpandableList(this, list, listDataChild);
        expListView.setAdapter(listAdapter);
        //expListView.setTag(-1);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String str = edSearch.getText().toString().toLowerCase(Locale.getDefault());
                ArrayList<String> list1 = new ArrayList<>();
                expListView.setAdapter(new AdapterExpandableList(BankBranchListActivity.this, list1, listDataChild));
                if (!str.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).toLowerCase(Locale.getDefault()).startsWith(str) || (list.get(i)).toLowerCase(Locale.getDefault()).contains(str)) {
                            list1.add(list.get(i));
                        }
                    }
                    expListView.setAdapter(new AdapterExpandableList(BankBranchListActivity.this, list1, listDataChild));
                } else {
                    expListView.setAdapter(new AdapterExpandableList(BankBranchListActivity.this, list, listDataChild));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        // expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
        //     @Override
        //     public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        //         //   Toast.makeText(getApplicationContext(), "Group Clicked " + listDataHeader.get(groupPosition), Toast.LENGTH_SHORT).show();
        //         return false;
        //     }
        // });

        // expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
        //     @Override
        //     public void onGroupExpand(int groupPosition) {
        //         int tempPos = (int) expListView.getTag();
        //         if (tempPos != groupPosition && tempPos != -1) expListView.collapseGroup(tempPos);
        //         expListView.setTag(groupPosition);
        //         //   Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT).show();
        //     }
        // });

        //    expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
        //     @Override
        //     public void onGroupCollapse(int groupPosition) {
        //         //    Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();
        //     }
        // });
        expListView.setOnChildClickListener((parent, view, groupPosition, childPosition, id) -> {
            PopupMenu popup = new PopupMenu(BankBranchListActivity.this, view);
            popup.getMenu().add(list.get(groupPosition));
            popup.getMenu().add((listDataChild.get(list.get(groupPosition)).get(childPosition)).getAddress());
            //popup.getMenuInflater().inflate(R.menu.popup_menu,);
            popup.setOnMenuItemClickListener(item -> {
                Toast.makeText(BankBranchListActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            });
            popup.show();
            return false;
        });
    }

    private void prepareListData() {
        listDataChild = new HashMap<>();
        list = mDbHelper.selectCityForBankBranch();
        for (String name : list) {
            listDataChild.put(name, new ArrayList<>(mDbHelper.selectBankBranch(name)));
        }
    }
}
