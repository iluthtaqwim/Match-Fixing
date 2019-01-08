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
import com.example.iluth.finalprojectpam.model.EventResponse;
import com.example.iluth.finalprojectpam.model.EventsItem;
import com.example.iluth.finalprojectpam.presenter.event.EventContract;
import com.example.iluth.finalprojectpam.presenter.event.EventPresenter;
import com.example.iluth.finalprojectpam.ui.adapter.EventAdapter;

import java.util.ArrayList;
import java.util.List;

public class LastMatchFragment extends Fragment implements EventContract.view {
    private View view;
    private ProgressBar progressBar;
    private AppCompatSpinner spinner;
    private RecyclerView recyclerView;
    private EventPresenter presenter;
    private String[] leagueNames;
    private String[] leagueIDs;
    private ArrayAdapter spinnerAdapter;
    private EventAdapter eventAdapter;
    private List<EventsItem> eventsItemList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new EventPresenter(this);
        leagueNames= getContext().getResources().getStringArray(R.array.ALL_LEAGUE_NAMES);
        leagueIDs= getContext().getResources().getStringArray(R.array.ALL_LEAGUE_IDS);
        spinnerAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, leagueNames);
        eventsItemList = new ArrayList<>();
        eventAdapter = new EventAdapter(getContext(), eventsItemList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_spinner_recyclerview,container,false);
        bindUI();
        setupSpinner();
        spinner.setSelection(0);
        return view;
    }

    private void bindUI() {
        progressBar = view.findViewById(R.id.progressBar);
        spinner = view.findViewById(R.id.spinner);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(eventAdapter);
    }

    private void setupSpinner(){
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.getLastEvent(leagueIDs[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onRequestSuccess(EventResponse data) {
        eventsItemList.clear();
        eventsItemList.addAll(data.getEvents());
        eventAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestFailed(String message) {
        Toast.makeText(getContext(), "error: "+ message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        eventsItemList.clear();
        eventAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
