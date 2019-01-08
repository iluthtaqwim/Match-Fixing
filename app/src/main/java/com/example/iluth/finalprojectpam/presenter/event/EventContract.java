package com.example.iluth.finalprojectpam.presenter.event;

import com.example.iluth.finalprojectpam.model.EventResponse;

public class EventContract {
    public interface presenter{
        void getNextEvent(String leagueId);
        void getLastEvent(String leagueId);
        void onRequestSuccess(EventResponse data);
        void onRequestFailed(Throwable t);
    }

    public interface view{
        void onRequestSuccess(EventResponse data);
        void onRequestFailed(String message);
        void showLoading();
        void hideLoading();
    }

    public interface interactor{
        void getNextEvent(String leagueId);
        void getLastEvent(String leagueId);
    }


}
