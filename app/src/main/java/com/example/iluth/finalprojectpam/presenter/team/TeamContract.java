package com.example.iluth.finalprojectpam.presenter.team;

import com.example.iluth.finalprojectpam.model.EventResponse;
import com.example.iluth.finalprojectpam.model.TeamResponse;

public class TeamContract {
    public interface presenter{
        void getAllTeamInLeague(String leagueId);
        void getTeamDetail(String teamId);
        void onRequestSuccess(TeamResponse data);
        void onRequestFailed(Throwable t);
    }

    public interface view{
        void onRequestSuccess(TeamResponse data);
        void onRequestFailed(String message);
        void showLoading();
        void hideLoading();
    }

    public interface interactor{
        void getAllTeamInLeague(String leagueId);
        void getTeamDetail(String teamId);
    }


}
