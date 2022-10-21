package me.mitul.aij.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdapterViewPager extends FragmentPagerAdapter {
    private final ArrayList<String> titleList = new ArrayList<>();
    private final ArrayList<Fragment> fragmentList = new ArrayList<>();

    public AdapterViewPager(FragmentManager manager) {
        super(manager);
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        titleList.add(title);
    }

    public int getCount() {
        return fragmentList.size();
    }

    @NonNull
    public Fragment getItem(int index) {
        return fragmentList.get(index);
    }

    public CharSequence getPageTitle(int index) {
        return titleList.get(index);
    }
}
