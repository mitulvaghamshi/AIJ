package me.mitul.aij.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import me.mitul.aij.helper.HelperCollage;
import me.mitul.aij.R;
import me.mitul.aij.adapter.AdapterCollage;
import me.mitul.aij.model.Collage;

public class CollageListActivity extends Activity {
    private EditText edSearch;
    private final HelperCollage dbHelper = new HelperCollage(CollageListActivity.this);
    private ArrayList<Collage> list;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_listview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ListView listview = findViewById(R.id.common_listview);
        listview.setVisibility(View.VISIBLE);
        findViewById(R.id.expandableListView).setVisibility(View.GONE);
        edSearch = findViewById(R.id.edSearchCommon);

        Intent getter = getIntent();
        String which = getter.getStringExtra(getString(R.string.selected_or_all_collages));
        if (which.equals(getString(R.string.retrieve_all_collages))) {
            list = dbHelper.selectAllCollage();
        } else if (which.equals(getString(R.string.branch_id))) {
            String which_id = getter.getStringExtra(getString(R.string.id_branch_collage));
            list = dbHelper.selectBranchWiseCollage(Integer.parseInt(which_id));
        } else if (which.equals(getString(R.string.university_id_to_find_university))) {
            String which_id = getter.getStringExtra(getString(R.string.id_university_to_find));
            list = dbHelper.selectUniversityWiseCollage(Integer.parseInt(which_id));
        }
        listview.setAdapter(new AdapterCollage(CollageListActivity.this, list));
        listview.setTextFilterEnabled(true);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String str = edSearch.getText().toString().toLowerCase(Locale.getDefault());
                ArrayList<Collage> list1 = new ArrayList<Collage>();
                listview.setAdapter(new AdapterCollage(CollageListActivity.this, list1));
                if (!str.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getCollageName().toLowerCase(Locale.getDefault()).startsWith(str) || list.get(i).getCollageName().toLowerCase(Locale.getDefault()).contains(str)) {
                            list1.add(list.get(i));
                        }
                    }
                    listview.setAdapter(new AdapterCollage(CollageListActivity.this, list1));
                } else {
                    listview.setAdapter(new AdapterCollage(CollageListActivity.this, list));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        listview.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(CollageListActivity.this, DetailCollageActivity.class);
            intent.putExtra(getString(R.string.id_to_find), ((TextView) view.findViewById(R.id.collage_list_item_collage_id)).getText().toString());
            startActivity(intent);
        });
    }
}
