package com.example.fatim.makeawish;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 1:
                PrivateListsLayout privateLists = new PrivateListsLayout();
                return privateLists;
            case 0:
                PublicListFragment publicList = new PublicListFragment();
                return publicList;
            default:
                return null;
        }
    }
}
