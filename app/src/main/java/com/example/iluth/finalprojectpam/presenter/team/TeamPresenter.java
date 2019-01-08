package com.example.iluth.finalprojectpam.presenter.team;

import com.example.iluth.finalprojectpam.api.event.EventRequest;
import com.example.iluth.finalprojectpam.api.team.TeamRequest;
import com.example.iluth.finalprojectpam.model.EventResponse;
import com.example.iluth.finalprojectpam.model.TeamResponse;

public class TeamPresenter implements TeamContract.presenter{
    private TeamContract.view view;
    private TeamContract.interactor interactor;

    public TeamPresenter(TeamContract.view view) {
        this.view = view;
        interactor = new TeamRequest(this);
    }

    @Override
    public void getAllTeamInLeague(String leagueId) {
        view.showLoading();
        interactor.getAllTeamInLeague(leagueId);
    }

    @Override
    public void getTeamDetail(String teamId) {
        view.showLoading();
        interactor.getTeamDetail(teamId);
    }

    @Override
    public void onRequestSuccess(TeamResponse data) {
        view.hideLoading();
        view.onRequestSuccess(data);
    }

    @Override
    public void onRequestFailed(Throwable t) {
        view.hideLoading();
        view.onRequestFailed(t.getMessage());
    }
}
