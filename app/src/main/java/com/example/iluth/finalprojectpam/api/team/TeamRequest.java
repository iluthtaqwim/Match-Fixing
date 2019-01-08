package com.example.iluth.finalprojectpam.api.team;

import com.example.iluth.finalprojectpam.model.EventResponse;
import com.example.iluth.finalprojectpam.model.TeamResponse;
import com.example.iluth.finalprojectpam.model.TeamsItem;
import com.example.iluth.finalprojectpam.presenter.team.TeamContract;
import com.example.iluth.finalprojectpam.retrofit.RetrofitClient;
import com.example.iluth.finalprojectpam.retrofit.TheSportDBInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamRequest implements TeamContract.interactor {
    private TeamContract.presenter presenter;
    private TheSportDBInterface api;

    public TeamRequest(TeamContract.presenter presenter) {
        this.presenter = presenter;
        api = RetrofitClient.getClient().create(TheSportDBInterface.class);
    }

    @Override
    public void getAllTeamInLeague(String leagueId) {
        api.getAllTeamInLeague(leagueId).enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                presenter.onRequestSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                presenter.onRequestFailed(t);
            }
        });
    }

    @Override
    public void getTeamDetail(String teamId) {
        api.getTeamDetail(teamId).enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                presenter.onRequestSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                presenter.onRequestFailed(t);
            }
        });
    }
}
