package com.csnewengineeringbuilding;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dn2757 on 2016. 10. 6..
 */
public class FloorFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    public FloorFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public static class Holder {
        private final List<Fragment> fragments = new ArrayList<>();
        private FragmentManager manager;
        public Holder(FragmentManager manager) {
            this.manager = manager;
        }

        public Holder add(Fragment f) {
            fragments.add(f);
            return this;
        }

        public FloorFragmentAdapter set() {
            return new FloorFragmentAdapter(manager, fragments);
        }
    }
}
