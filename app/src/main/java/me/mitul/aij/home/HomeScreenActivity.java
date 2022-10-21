package me.mitul.aij.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import me.mitul.aij.R;
import me.mitul.aij.aij.ContactUs;
import me.mitul.aij.aij.HomeScreenAijActivity;
import me.mitul.aij.frags.MainActivity;
import me.mitul.aij.helper.HelperBackupRestore;

public class HomeScreenActivity extends Activity implements NavigationView.OnNavigationItemSelectedListener, OnClickListener {
    //private Animation buttonClick;
    private Button btnFind;
    private Button btnCollage;
    private Button btnBranch;
    private Button btnUniversity;
    private Button btnScholarship;
    private Button btnBank;
    private Button btnHelp;
    private Button btnQA;
    private Button btnAdmissionStep;
    private Button btnDeveloper;
    private Button btnNew2;
    private Button btnNew3;
    private Button btnNew4;
    private Button btnNew5;
    private Button btnNew6;
    private Button btnNew7;
    private Button btnStudentCorner;
    private FloatingActionButton fabBackup;
    private FloatingActionButton fabRestore;
    private FloatingActionButton fabExit;
    private boolean localChecked = false;
    private AlertDialog.Builder exitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        exitDialog = new AlertDialog.Builder(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Share this app", Snackbar.LENGTH_LONG).setAction("Share", null).show();
            //animate();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnFind = findViewById(R.id.find);
        btnCollage = findViewById(R.id.collage);
        btnDeveloper = findViewById(R.id.developer);
        btnHelp = findViewById(R.id.help);
        btnUniversity = findViewById(R.id.university);
        btnAdmissionStep = findViewById(R.id.webAdmissionView2);
        btnScholarship = findViewById(R.id.scholarship);
        btnQA = findViewById(R.id.qa);
        btnBranch = findViewById(R.id.branches);
        btnBank = findViewById(R.id.bank);
        btnNew2 = findViewById(R.id.new2);
        btnNew3 = findViewById(R.id.new3);
        btnNew4 = findViewById(R.id.new4);
        btnNew5 = findViewById(R.id.new5);
        btnNew6 = findViewById(R.id.new6);
        btnNew7 = findViewById(R.id.new7);
        btnStudentCorner = findViewById(R.id.student_corner);
        fabBackup = findViewById(R.id.fab_backup_go);
        fabRestore = findViewById(R.id.fab_restore_go);
        fabExit = findViewById(R.id.fab_exit_now);

        int userID = getIntent().hasExtra("UserID") ? Integer.parseInt(getIntent().getStringExtra("UserID")) : -1;

        btnFind.setOnClickListener(this);
        btnCollage.setOnClickListener(this);
        btnDeveloper.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
        btnUniversity.setOnClickListener(this);
        btnAdmissionStep.setOnClickListener(this);
        btnScholarship.setOnClickListener(this);
        btnQA.setOnClickListener(this);
        btnBranch.setOnClickListener(this);
        btnBank.setOnClickListener(this);
        btnNew2.setOnClickListener(this);
        btnNew3.setOnClickListener(this);
        btnNew4.setOnClickListener(this);
        btnNew5.setOnClickListener(this);
        btnNew6.setOnClickListener(this);
        btnNew7.setOnClickListener(this);
        fabBackup.setOnClickListener(this);
        fabRestore.setOnClickListener(this);
        fabExit.setOnClickListener(this);
        btnStudentCorner.setOnClickListener(this);
        //buttonClick=AnimationUtils.loadAnimation(getApplicationContext(), R.anim.click));
    }

    private void animate() {
        new Handler(Looper.getMainLooper()).post(() -> {
            btnFind.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_1));
            btnCollage.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_2));
            btnDeveloper.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_3));
            btnHelp.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_4));
            btnUniversity.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_5));
            btnAdmissionStep.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_6));
            btnScholarship.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_7));
            btnQA.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_8));
            btnBranch.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_9));
            btnBank.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_1));
            btnNew2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_2));
            btnNew3.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_3));
            btnNew4.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_4));
            btnNew5.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_5));
            btnNew6.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_6));
            btnNew7.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_7));
            btnStudentCorner.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_1));
            fabBackup.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_8));
            fabRestore.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_9));
            fabExit.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_5));
        });
    }

    @Override
    public void onClick(final View view1) {
        view1.animate().setDuration(500L).alpha(0).withEndAction(() -> {
            view1.animate().alpha(1);
            if (view1.equals(btnFind))
                Snackbar.make(view1, "Coming soon...", Snackbar.LENGTH_LONG).setAction("ok", view -> {
                }).show();
            if (view1.equals(btnCollage))
                startActivity(new Intent(getApplicationContext(), CollageListActivity.class).putExtra(getString(R.string.selected_or_all_collages), getString(R.string.retrieve_all_collages)));
            if (view1.equals(btnDeveloper))
                startActivity(new Intent(getApplicationContext(), DeveloperActivity.class));
            if (view1.equals(btnHelp))
                startActivity(new Intent(getApplicationContext(), HelpCenterListActivity.class));
            if (view1.equals(btnUniversity))
                startActivity(new Intent(getApplicationContext(), UniversityListActivity.class));
            if (view1.equals(btnBranch))
                startActivity(new Intent(getApplicationContext(), BranchListActivity.class));
            if (view1.equals(btnBank))
                startActivity(new Intent(getApplicationContext(), BankBranchListActivity.class));
            if (view1.equals(btnNew7))
                startActivity(new Intent(getApplicationContext(), HomeScreenAijActivity.class));
            if (view1.equals(fabBackup)) HelperBackupRestore.exportDb();
            if (view1.equals(fabRestore)) HelperBackupRestore.restoreDb();
            if (view1.equals(fabExit)) performExit();
        });
    }

    private void performExit() {
        localChecked = false;
        exitDialog
                .setTitle("Do you want to exit?")
                .setMultiChoiceItems(new CharSequence[]{"Log out?"}, null, (dialog, indexSelected, isChecked) -> localChecked = isChecked)
                .setPositiveButton("Yes", (dialog, id) -> {
                    if (localChecked)
                        getSharedPreferences("Login1", 0)
                                .edit()
                                .putBoolean("keepMeLoggdIn", false)
                                .putString("Email1", "mady@me")
                                .putString("Pass1", "123456")
                                .apply();
                    finish();
                })
                .setNegativeButton("No", null)
                .create()
                .show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) drawer.closeDrawer(GravityCompat.START);
        else performExit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_collage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.m_1) return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_contact) {
            startActivity(new Intent(this, ContactUs.class));
        } else if (itemId == R.id.home_exit) {
            performExit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
