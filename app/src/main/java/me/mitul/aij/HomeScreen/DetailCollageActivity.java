package me.mitul.aij.homescreen;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import me.mitul.aij.models.Collage;
import me.mitul.aij.helper.HelperCollage;
import me.mitul.aij.R;

public class DetailCollageActivity extends Activity {
    private final HelperCollage dbHelper = new HelperCollage(DetailCollageActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_collage);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final String id = getIntent().getStringExtra(getString(R.string.id_to_find));
        final Collage collage = dbHelper.selectCollageByID(Integer.parseInt(id));
        ((TextView) findViewById(R.id.collegedetail_tv_collegelabel)).setText(collage.getLabel());
        ((TextView) findViewById(R.id.collegedetail_tv_collegeshortname)).setText(collage.getClgShortName());
        ((TextView) findViewById(R.id.collegedetail_tv_collegefullname)).setText(collage.getClgFullName());
        ((TextView) findViewById(R.id.collegedetail_tv_collegeaddress)).setText(collage.getClgAddress());
        ((TextView) findViewById(R.id.collegedetail_tv_phone_value)).setText(collage.getPhone());
        ((TextView) findViewById(R.id.collegedetail_tv_website_value)).setText(collage.getWeb());
        ((TextView) findViewById(R.id.collegedetail_tv_email_value)).setText(collage.getEmail());
        ((TextView) findViewById(R.id.collegedetail_tv_fees_value)).setText(String.format(getString(R.string.fees_collage), collage.getFees()));
        ((TextView) findViewById(R.id.collegedetail_tv_collegetype_value)).setText(collage.getType());
        ((TextView) findViewById(R.id.collegedetail_tv_collegetype_hostel)).setText(collage.getHostel());
        ((TextView) findViewById(R.id.collegedetail_tv_collegetype_university)).setText(collage.getUniversity());
        DetailCollageActivity.this.setTitle(getString(R.string.collage_code) + (((TextView) findViewById(R.id.collegedetail_tv_collegelabel))).getText());

        // findViewById(R.id.collegedetail_btn_Intake).setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         startActivity(new Intent(DetailCollageActivity.this, IntakeDetailActivity.class).putExtra(getString(R.string.id_intake_to_find), id));
        //     }
        // });
        //
        // findViewById(R.id.collegedetail_btn_closing).setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         startActivity(new Intent(DetailCollageActivity.this, AllClosingActivity.class).putExtra(getString(R.string.id_to_find_closing), id).putExtra(getString(R.string.closing_collage_name), localBeanCollageList.getClgFullName()));
        //     }
        // });
    }
}
