package com.example.iluth.finalprojectpam.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.iluth.finalprojectpam.R;
import com.example.iluth.finalprojectpam.model.EventsItem;
import com.example.iluth.finalprojectpam.model.TeamsItem;
import com.example.iluth.finalprojectpam.ui.fragment.FavoriteFragment;
import com.example.iluth.finalprojectpam.ui.fragment.MatchFragment;
import com.example.iluth.finalprojectpam.ui.fragment.TeamFragment;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView btmview;
    private String matchFragmentTAG, teamFragmentTAG, favoriteFragmentTAG;
    private Fragment matchFragment, teamFragment, favoriteFragment;
    private TeamsItem teamDetail;
    private EventsItem eventsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btmview = findViewById(R.id.btmnav);
        btmview.setOnNavigationItemSelectedListener(this);

        teamDetail = (TeamsItem) getIntent().getSerializableExtra("team");
        eventsItem = (EventsItem) getIntent().getSerializableExtra("event");

        matchFragmentTAG = MatchFragment.class.getSimpleName();
        teamFragmentTAG = TeamFragment.class.getSimpleName();
        favoriteFragmentTAG = FavoriteFragment.class.getSimpleName();
        initiateFragments();

    }

    private void initiateFragments() {
        matchFragment = new MatchFragment();
        teamFragment = new TeamFragment();
        favoriteFragment = new FavoriteFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mainframe, favoriteFragment, favoriteFragmentTAG)
                .add(R.id.mainframe, teamFragment, teamFragmentTAG)
                .add(R.id.mainframe, matchFragment, matchFragmentTAG)
                .detach(favoriteFragment)
                .detach(teamFragment)
                .commitNow();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btm_match:
                replaceFragment(matchFragment, teamFragment, favoriteFragment);
                break;
            case R.id.btm_team:
                replaceFragment(teamFragment, matchFragment, favoriteFragment);
                break;
            case R.id.btm_fav:
                replaceFragment(favoriteFragment, teamFragment, matchFragment);
                break;
        }
        return true;

    }

    private void replaceFragment(Fragment toAttach, Fragment toDetach1, Fragment toDetach2) {
        getSupportFragmentManager()
                .beginTransaction()
                .detach(toDetach1)
                .detach(toDetach2)
                .attach(toAttach)
                .commitNow();

    }
}
