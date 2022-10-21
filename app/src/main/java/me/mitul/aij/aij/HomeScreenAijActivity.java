package me.mitul.aij.aij;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import me.mitul.aij.adapter.AdapterViewPager;
import me.mitul.aij.utils.PlaceHolderFragment;
import me.mitul.aij.R;

public class HomeScreenAijActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_aij);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        drawerLayout = findViewById(R.id.drawer_layout_home_screen_aij);
        NavigationView navigationView = findViewById(R.id.navigation_drawer_home_screen_aij);
        if (navigationView != null) setupDrawerContent(navigationView);
        ViewPager viewPager = findViewById(R.id.viewpager_home_screen_aij);
        if (viewPager != null) setupViewPager(viewPager);
        ((TabLayout) findViewById(R.id.tabs11)).setupWithViewPager(viewPager);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_collage, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterViewPager adapter = new AdapterViewPager(getSupportFragmentManager());
        adapter.addFragment(PlaceHolderFragment.newInstance(1), getResources().getString(R.string.welcome_lbl));
        adapter.addFragment(PlaceHolderFragment.newInstance(16), getResources().getString(R.string.about_lbl));
        adapter.addFragment(PlaceHolderFragment.newInstance(21), getResources().getString(R.string.vision_lbl));
        adapter.addFragment(PlaceHolderFragment.newInstance(20), getResources().getString(R.string.mission_lbl));
        adapter.addFragment(PlaceHolderFragment.newInstance(22), getResources().getString(R.string.why_aij_lbl));
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(true);
            drawerLayout.closeDrawers();
            int id = findMenuId(menuItem.getItemId());
            if (id == 0) return false;
            Intent intent = new Intent(HomeScreenAijActivity.this, AijExplorerActivity.class);
            startActivity(intent.putExtra(getString(R.string.item_id), id));
            return true;
        });
    }

    private int findMenuId(int id) {
        if (id == R.id.nav_aij_faqs) {
            return 1;
        } else if (id == R.id.nav_aij_carrier) {
            return 3;
        } else if (id == R.id.nav_aij_admission_procedure) {
            return 4;
        } else if (id == R.id.nav_aij_eligibility_criteria) {
            return 99;
        } else if (id == R.id.nav_aij_facilities) {
            return 13;
        } else if (id == R.id.nav_aij_Activities) {
            return 14;
        } else if (id == R.id.nav_aij_policy) {
            return 15;
        } else if (id == R.id.nav_aij_about_shect) {
            return 17;
        } else if (id == R.id.nav_aij_infrastructure) {
            return 18;
        } else if (id == R.id.nav_placement) {
            return 19;
        } else if (id == R.id.nav_courses) {
            startActivity(new Intent(HomeScreenAijActivity.this, CourseActivity.class));
            return 0;
        }
        return 0;
    }
}
