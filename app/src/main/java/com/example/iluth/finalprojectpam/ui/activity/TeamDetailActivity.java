package com.example.iluth.finalprojectpam.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iluth.finalprojectpam.R;
import com.example.iluth.finalprojectpam.model.TeamResponse;
import com.example.iluth.finalprojectpam.model.TeamsItem;
import com.example.iluth.finalprojectpam.presenter.team.TeamContract;
import com.example.iluth.finalprojectpam.presenter.team.TeamPresenter;
import com.squareup.picasso.Picasso;

public class TeamDetailActivity extends AppCompatActivity implements TeamContract.view {
    private ImageView imageClub;
    private TextView tvClub,tvTahun,tvStadium,tvDesc;
    private TeamsItem teamsItem;
    private TeamPresenter teamPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);
        imageClub = findViewById(R.id.imgTeamBadge);
        tvClub = findViewById(R.id.tvTeamName);
        tvTahun = findViewById(R.id.tvFormedYear);
        tvStadium = findViewById(R.id.tvStadium);
        tvDesc = findViewById(R.id.tvDesc);
        teamPresenter = new TeamPresenter(this);
        teamsItem = (TeamsItem)getIntent().getSerializableExtra("team");
        teamPresenter.getTeamDetail(teamsItem.getIdTeam());


        tvClub.setText(teamsItem.getStrTeam());
        tvTahun.setText(teamsItem.getIntFormedYear());
        tvStadium.setText(teamsItem.getStrStadium());
        tvDesc.setText(teamsItem.getStrDescriptionEN());
        tvDesc.setMovementMethod(new ScrollingMovementMethod());



    }

    @Override
    public void onRequestSuccess(TeamResponse data) {
        Picasso.get().load(data.getTeams().get(0).getStrTeamBadge()).fit().into(imageClub);
    }

    @Override
    public void onRequestFailed(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
