package me.mitul.aij.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import me.mitul.aij.helper.HelperUniversity;
import me.mitul.aij.R;
import me.mitul.aij.model.University;

public class DetailUniversityActivity extends Activity {
    private TextView universityID;
    private final HelperUniversity dbHelper = new HelperUniversity(DetailUniversityActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_university);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final String id = getIntent().getStringExtra(getString(R.string.id_university_to_find));
        University university = dbHelper.selectUniversityByID(Integer.parseInt(id));
        universityID = findViewById(R.id.university_detail_tv_university_id);
        universityID.setText(String.valueOf(university.getUniversityID()));
        ((TextView) findViewById(R.id.university_detail_tv_university_shortname)).setText(university.getUniversityShortName());
        ((TextView) findViewById(R.id.university_detail_tv_university_full_name)).setText(university.getUniversityName());
        ((TextView) findViewById(R.id.university_detail_tv_university_address)).setText(university.getUniversityAddress());
        ((TextView) findViewById(R.id.university_detail_tv_university_phone)).setText(university.getUniversityPhone());
        ((TextView) findViewById(R.id.university_detail_tv_university_website)).setText(university.getUniversityWebsite());
        ((TextView) findViewById(R.id.university_detail_tv_university_email)).setText(university.getUniversityEmail());
        ((TextView) findViewById(R.id.university_detail_tv_university_type)).setText(university.getUniversityType());
        Button universityButton = findViewById(R.id.university_detail_tv_university_button);
        universityButton.setText(String.format(getString(R.string.collage_in_ss), university.getUniversityShortName()));
        universityButton.setOnClickListener(v -> {
            Intent intent = new Intent(DetailUniversityActivity.this, CollageListActivity.class);
            intent.putExtra(getString(R.string.selected_or_all_collages), getString(R.string.university_id_to_find_university))
                    .putExtra(getString(R.string.id_university_to_find), universityID.getText().toString());
            startActivity(intent);
        });
    }
}
