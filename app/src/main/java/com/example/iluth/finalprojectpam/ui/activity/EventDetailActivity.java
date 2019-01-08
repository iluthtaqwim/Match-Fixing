package com.example.iluth.finalprojectpam.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iluth.finalprojectpam.R;
import com.example.iluth.finalprojectpam.model.EventsItem;
import com.example.iluth.finalprojectpam.model.TeamResponse;
import com.example.iluth.finalprojectpam.presenter.team.TeamContract;
import com.example.iluth.finalprojectpam.presenter.team.TeamPresenter;
import com.squareup.picasso.Picasso;

public class EventDetailActivity extends AppCompatActivity implements TeamContract.view {

    private TeamPresenter teamPresenter;
    private ImageView imageHome;
    private ImageView imageAway;
    private TextView tvHomeClub, tvHomeScore, tvHomeGoals, tvHomeShots, tvHomeGoalkeeper, tvHomeDefense, tvHomeMidlefield, tvHomeForward, tvHomeSubtituties, tvAwayClub, tvAwayScore, tvAwayGoals, tvAwayShots, tvAwayGoalkeeper, tvAwayDefense, tvAwayMidlefield, tvAwayForward, tvAwaySubtituties;
    private EventsItem eventsItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
        eventsItem = (EventsItem) getIntent().getSerializableExtra("event");
        teamPresenter = new TeamPresenter(this);
        bindUI();
        loadData();
    }

    private void loadData() {
        teamPresenter.getTeamDetail(eventsItem.getIdHomeTeam());
        teamPresenter.getTeamDetail(eventsItem.getIdAwayTeam());

        tvHomeClub.setText(eventsItem.getStrHomeTeam());
        tvAwayClub.setText(eventsItem.getStrAwayTeam());
        tvHomeScore.setText(eventsItem.getIntHomeScore());
        tvAwayScore.setText(eventsItem.getIntAwayScore());
        tvHomeGoals.setText(parser(eventsItem.getStrHomeGoalDetails()));
        tvAwayGoals.setText(parser(eventsItem.getStrAwayGoalDetails()));
        tvHomeShots.setText(parser(eventsItem.getIntHomeShots()));
        tvAwayShots.setText(parser(eventsItem.getIntAwayShots()));
        tvHomeGoalkeeper.setText(eventsItem.getStrHomeLineupGoalkeeper());
        tvAwayGoalkeeper.setText(eventsItem.getStrAwayLineupGoalkeeper());
        tvHomeDefense.setText(parser(eventsItem.getStrHomeLineupDefense()));
        tvAwayDefense.setText(parser(eventsItem.getStrAwayLineupDefense()));
        tvHomeMidlefield.setText(parser(eventsItem.getStrHomeLineupMidfield()));
        tvAwayMidlefield.setText(parser(eventsItem.getStrAwayLineupMidfield()));
        tvHomeForward.setText(parser(eventsItem.getStrHomeLineupForward()));
        tvAwayForward.setText(parser(eventsItem.getStrAwayLineupForward()));
        tvHomeSubtituties.setText(parser(eventsItem.getStrHomeLineupSubstitutes()));
        tvAwaySubtituties.setText(parser(eventsItem.getStrAwayLineupSubstitutes()));
    }

    private void bindUI() {
        imageHome = findViewById(R.id.homeEmblem);
        imageAway = findViewById(R.id.awayEmblem);
        tvHomeClub = findViewById(R.id.homeTeam);
        tvAwayClub = findViewById(R.id.awayTeam);
        tvHomeScore = findViewById(R.id.homeScore);
        tvAwayScore = findViewById(R.id.awayScore);
        tvHomeGoals = findViewById(R.id.homeGoals);
        tvAwayGoals = findViewById(R.id.awayGoals);
        tvHomeShots = findViewById(R.id.homeShots);
        tvAwayShots = findViewById(R.id.awayShots);
        tvHomeGoalkeeper = findViewById(R.id.homeGk);
        tvAwayGoalkeeper = findViewById(R.id.awayGk);
        tvHomeDefense = findViewById(R.id.homeDef);
        tvAwayDefense = findViewById(R.id.awayDef);
        tvHomeMidlefield = findViewById(R.id.homeMdf);
        tvAwayMidlefield = findViewById(R.id.awayMdf);
        tvHomeForward = findViewById(R.id.homeOfen);
        tvAwayForward = findViewById(R.id.awayOfen);
        tvHomeSubtituties = findViewById(R.id.homeSubs);
        tvAwaySubtituties = findViewById(R.id.awaySubs);
    }

    private String parser(@Nullable String input) {
        String output = "";
        if (input != null) {
            output = input.replace(";", "\n");
        }
        return output;
    }

    @Override
    public void onRequestSuccess(TeamResponse data) {
        if (data.getTeams().get(0).getIdTeam().equals(eventsItem.getIdHomeTeam())) {
            Picasso.get().load(data.getTeams().get(0).getStrTeamBadge()).fit().into(imageHome);
        } else {
            Picasso.get().load(data.getTeams().get(0).getStrTeamBadge()).fit().into(imageAway);
        }
    }

    @Override
    public void onRequestFailed(String message) {
        Toast.makeText(this, "error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }
}