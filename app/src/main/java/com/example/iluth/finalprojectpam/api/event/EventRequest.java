package com.example.iluth.finalprojectpam.api.event;

import com.example.iluth.finalprojectpam.model.EventResponse;
import com.example.iluth.finalprojectpam.presenter.event.EventContract;
import com.example.iluth.finalprojectpam.presenter.event.EventPresenter;
import com.example.iluth.finalprojectpam.retrofit.RetrofitClient;
import com.example.iluth.finalprojectpam.retrofit.TheSportDBInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EventRequest implements EventContract.interactor {
    TheSportDBInterface api;
    private EventContract.presenter presenter;

    public EventRequest(EventContract.presenter presenter) {
        api = RetrofitClient.getClient().create(TheSportDBInterface.class);
        this.presenter = presenter;
    }

    @Override
    public void getNextEvent(String leagueId) {
        api.getNextEvent(leagueId).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                presenter.onRequestSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                presenter.onRequestFailed(t);
            }
        });
    }

    @Override
    public void getLastEvent(String leagueId) {
        api.getLastEvent(leagueId).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                presenter.onRequestSuccess(response.body());
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                presenter.onRequestFailed(t);
            }
        });
    }
}
