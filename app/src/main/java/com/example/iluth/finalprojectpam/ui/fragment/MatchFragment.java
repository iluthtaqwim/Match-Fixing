package com.example.iluth.finalprojectpam.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iluth.finalprojectpam.R;
import com.example.iluth.finalprojectpam.ui.adapter.PagerAdapter;

public class MatchFragment extends Fragment {

    private View view;
    private PagerAdapter pagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagerAdapter = new PagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(new NextMatchFragment(), "Next");
        pagerAdapter.addFragment(new LastMatchFragment(), "Last");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_pager,container,false);
        bindUI();
        return view;
    }

    private void bindUI() {
        TabLayout tabLayout = view.findViewById(R.id.tablayout);
        ViewPager viewPager = view.findViewById(R.id.viewpager);

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
