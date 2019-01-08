package com.example.iluth.finalprojectpam.retrofit;

import com.example.iluth.finalprojectpam.model.EventResponse;
import com.example.iluth.finalprojectpam.model.TeamResponse;
import com.example.iluth.finalprojectpam.model.TeamsItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheSportDBInterface {
    @GET("eventsnextleague.php")
    Call<EventResponse> getNextEvent(
            @Query("id") String leagueId
    );

    @GET("eventspastleague.php")
    Call<EventResponse> getLastEvent(
            @Query("id") String leagueId
    );

    @GET("lookup_all_teams.php")
    Call<TeamResponse> getAllTeamInLeague(
            @Query("id") String leagueId
    );

    @GET("lookupteam.php")
    Call<TeamResponse> getTeamDetail(
            @Query("id") String teamId
    );


}
