package me.mitul.aij.aij;

import android.os.Bundle;
import android.view.Menu;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import me.mitul.aij.utils.PlaceHolderFragment;
import me.mitul.aij.R;

public class CourseActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cources);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ViewPager viewPager = findViewById(R.id.viewpager_home_screen_aij);
        if (viewPager != null) setupViewPager(viewPager);
        ((TabLayout) findViewById(R.id.tabs_courses)).setupWithViewPager(viewPager);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_collage, menu);
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        CourseAdapter adapter = new CourseAdapter(getSupportFragmentManager());
        adapter.addFragment(PlaceHolderFragment.newInstance(6), "Architect");
        adapter.addFragment(PlaceHolderFragment.newInstance(7), "Automobile");
        adapter.addFragment(PlaceHolderFragment.newInstance(8), "Civil");
        adapter.addFragment(PlaceHolderFragment.newInstance(9), "Computer");
        adapter.addFragment(PlaceHolderFragment.newInstance(10), "Electrical");
        adapter.addFragment(PlaceHolderFragment.newInstance(11), "Inst. & Control");
        adapter.addFragment(PlaceHolderFragment.newInstance(12), "Mechanical");
        viewPager.setAdapter(adapter);
    }

    private static class CourseAdapter extends FragmentPagerAdapter {
        private final ArrayList<String> titleList = new ArrayList<>();
        private final ArrayList<Fragment> fragmentList = new ArrayList<>();

        CourseAdapter(FragmentManager manager) {
            super(manager);
        }

        void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            titleList.add(title);
        }

        public int getCount() {
            return fragmentList.size();
        }

        @NonNull
        public Fragment getItem(int index) {
            return this.fragmentList.get(index);
        }

        public CharSequence getPageTitle(int index) {
            return this.titleList.get(index);
        }
    }
}
