package com.example.iluth.finalprojectpam.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.iluth.finalprojectpam.R;
import com.example.iluth.finalprojectpam.model.EventsItem;
import com.example.iluth.finalprojectpam.model.TeamResponse;
import com.example.iluth.finalprojectpam.model.TeamsItem;
import com.example.iluth.finalprojectpam.presenter.team.TeamContract;
import com.example.iluth.finalprojectpam.presenter.team.TeamPresenter;
import com.example.iluth.finalprojectpam.ui.adapter.TeamAdapter;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment implements TeamContract.view {

    private View view;
    private String[] leagueNames;
    private String[] leagueIDs;
    private ArrayAdapter spinnerAdapter;
    private ProgressBar progressBar;
    private AppCompatSpinner spinner;
    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private List<TeamsItem> teamsItems;
    private TeamPresenter teamPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leagueNames= getContext().getResources().getStringArray(R.array.ALL_LEAGUE_NAMES);
        leagueIDs= getContext().getResources().getStringArray(R.array.ALL_LEAGUE_IDS);
        spinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, leagueNames);
        teamsItems = new ArrayList<>();
        teamAdapter = new TeamAdapter(getContext(), teamsItems);
        teamPresenter = new TeamPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_spinner_recyclerview, container, false);
        bindUI();
        setupSpinner();
        return view;
    }

    private void bindUI() {
        progressBar = view.findViewById(R.id.progressBar);
        spinner = view.findViewById(R.id.spinner);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(teamAdapter);
    }

    private void setupSpinner(){
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                teamPresenter.getAllTeamInLeague(leagueIDs[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onRequestSuccess(TeamResponse data) {
        teamsItems.clear();
        teamsItems.addAll(data.getTeams());
        teamAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestFailed(String message) {
        Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        teamsItems.clear();
        teamAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
