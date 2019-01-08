package com.example.iluth.finalprojectpam.presenter.event;

import android.util.Log;

import com.example.iluth.finalprojectpam.api.event.EventRequest;
import com.example.iluth.finalprojectpam.model.EventResponse;

public class EventPresenter implements EventContract.presenter{
    private EventContract.view view;
    private EventContract.interactor interactor;

    public EventPresenter(EventContract.view view) {
        this.view = view;
        interactor = new EventRequest(this);
    }

    @Override
    public void getNextEvent(String leagueId) {
        view.showLoading();
        interactor.getNextEvent(leagueId);
    }

    @Override
    public void getLastEvent(String leagueId) {
        view.showLoading();
        interactor.getLastEvent(leagueId);
    }

    @Override
    public void onRequestSuccess(EventResponse data) {
        view.onRequestSuccess(data);
        view.hideLoading();
    }

    @Override
    public void onRequestFailed(Throwable t) {
        view.onRequestFailed(t.getMessage());
        view.hideLoading();
    }
}
