package com.sim.chongwukongjing.ui.Base.wigdet;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * fragment适配器
 * Created by fly on 2017/12/28 0028.
 */

public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private List<String> titleList = null;

    public FragAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int postion) {
        return list.get(postion);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (titleList == null){
            return "第"+(position+1)+"页";
        }else{
            return titleList.get(position);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void setTitleList(List<String> titleList){
        this.titleList = titleList;
    }

}
