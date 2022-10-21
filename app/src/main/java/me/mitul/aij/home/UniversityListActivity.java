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

import me.mitul.aij.helper.HelperUniversity;
import me.mitul.aij.R;
import me.mitul.aij.adapter.AdapterUniversity;
import me.mitul.aij.model.University;

public class UniversityListActivity extends Activity {
    private EditText edSearch;
    private final HelperUniversity dbHelper = new HelperUniversity(UniversityListActivity.this);
    private ArrayList<University> list;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_listview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final ListView listview = findViewById(R.id.common_listview);
        listview.setVisibility(View.VISIBLE);
        findViewById(R.id.expandableListView).setVisibility(View.GONE);
        edSearch = findViewById(R.id.edSearchCommon);
        list = dbHelper.selectAllUniversity();
        listview.setAdapter(new AdapterUniversity(UniversityListActivity.this, list));
        listview.setTextFilterEnabled(true);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String str = edSearch.getText().toString().toLowerCase(Locale.getDefault());
                ArrayList<University> list1 = new ArrayList<>();
                listview.setAdapter(new AdapterUniversity(UniversityListActivity.this, list1));
                if (!str.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getUniversityName().toLowerCase(Locale.getDefault()).startsWith(str) || list.get(i).getUniversityName().toLowerCase(Locale.getDefault()).contains(str)) {
                            list1.add(list.get(i));
                        }
                    }
                    listview.setAdapter(new AdapterUniversity(UniversityListActivity.this, list1));
                } else {
                    listview.setAdapter(new AdapterUniversity(UniversityListActivity.this, list));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        listview.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(UniversityListActivity.this, DetailUniversityActivity.class);
            intent.putExtra(getString(R.string.id_university_to_find), ((TextView) view.findViewById(R.id.list_simple_public_id)).getText().toString());
            startActivity(intent);
        });
    }
}
