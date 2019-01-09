package com.example.iluth.finalprojectpam.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.iluth.finalprojectpam.R;
import com.example.iluth.finalprojectpam.model.EventsItem;
import com.example.iluth.finalprojectpam.model.TeamsItem;
import com.example.iluth.finalprojectpam.ui.fragment.FavoriteFragment;
import com.example.iluth.finalprojectpam.ui.fragment.MatchFragment;
import com.example.iluth.finalprojectpam.ui.fragment.TeamFragment;



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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_option, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {

            case R.id.btm_setting:
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                break;
            case R.id.btm_match:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btm_team:
                replaceFragment(teamFragment, matchFragment, favoriteFragment);
                break;
            case R.id.btm_fav:
                replaceFragment(favoriteFragment, teamFragment, matchFragment);
                break;
            case R.id.btm_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Your Body Here";
                String shareSubject = "Your Subject here";

                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
                startActivity(Intent.createChooser(sharingIntent, "Share Using"));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
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
