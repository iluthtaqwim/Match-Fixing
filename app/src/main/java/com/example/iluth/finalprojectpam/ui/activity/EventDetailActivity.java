package com.example.iluth.finalprojectpam.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.iluth.finalprojectpam.DB.DatabaseHelper;
import com.example.iluth.finalprojectpam.R;
import com.example.iluth.finalprojectpam.model.EventsItem;
import com.example.iluth.finalprojectpam.model.TeamResponse;
import com.example.iluth.finalprojectpam.presenter.team.TeamContract;
import com.example.iluth.finalprojectpam.presenter.team.TeamPresenter;
import com.squareup.picasso.Picasso;

import com.allyants.notifyme.NotifyMe;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class EventDetailActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,TeamContract.view, View.OnClickListener {

    Calendar now = Calendar.getInstance();
    TimePickerDialog tpd;
    DatePickerDialog dpd;
    FloatingActionButton shareButton;

    ToggleButton toggleButton;
    DatabaseHelper mDatabaseHelper;
    private TeamPresenter teamPresenter;
    private ImageView imageHome;
    private ImageView imageAway;
    private TextView tvEventDate, tvEventTime, tvHomeClub, tvHomeScore, tvHomeGoals, tvHomeShots, tvHomeGoalkeeper, tvHomeDefense, tvHomeMidlefield, tvHomeForward, tvHomeSubtituties, tvAwayClub, tvAwayScore, tvAwayGoals, tvAwayShots, tvAwayGoalkeeper, tvAwayDefense, tvAwayMidlefield, tvAwayForward, tvAwaySubtituties;
    private EventsItem eventsItem;


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, monthOfYear);
        now.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String home = eventsItem.getStrHomeTeam();
        String away = eventsItem.getStrAwayTeam();
        eventsItem = (EventsItem) getIntent().getSerializableExtra("event");
        now.set(Calendar.HOUR_OF_DAY, hourOfDay);
        now.set(Calendar.MINUTE, minute);
        now.set(Calendar.SECOND, second);
        Intent intent = new Intent(getApplicationContext(), TestActivity.class);
        intent.putExtra("test", "I am a String");
        NotifyMe notifyMe = new NotifyMe.Builder(getApplicationContext())
                .title(home + " vs " + away)
                .content(eventsItem.getStrDate())
                .color(255, 0, 0, 255)
                .led_color(255, 255, 255, 255)
                .time(now)
                .addAction(intent, "Snooze", false)
                .key("test")
                .addAction(new Intent(), "Dismiss", true, false)
                .addAction(intent, "Done")
                .large_icon(R.mipmap.ic_launcher_round)
                .build();
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);
        eventsItem = (EventsItem) getIntent().getSerializableExtra("event");
        String home = eventsItem.getStrHomeTeam();
        String away = eventsItem.getStrAwayTeam();
        bindUI();
        toggleButton.setChecked(false);
        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_fav_border));
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_fav));
                else
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_fav_border));

            }
        });

        shareButton = (FloatingActionButton) findViewById(R.id.btnShare);
        shareButton.setOnClickListener(this);

        getSupportActionBar().setTitle(home + " vs " + away);
        getSupportActionBar().setSubtitle(eventsItem.getStrDate());
        teamPresenter = new TeamPresenter(this);

        loadData();


        Button btnNotify = findViewById(R.id.btnNotify);


        dpd = DatePickerDialog.newInstance(
                EventDetailActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        tpd = TimePickerDialog.newInstance(
                EventDetailActivity.this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                false
        );
        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnShare) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Your Body Here";
            String shareSubject = "Your Subject here";

            sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
            startActivity(Intent.createChooser(sharingIntent, "Share Using"));
        }
    }

    private void loadData() {
        teamPresenter.getTeamDetail(eventsItem.getIdHomeTeam());
        teamPresenter.getTeamDetail(eventsItem.getIdAwayTeam());
        tvEventDate.setText(eventsItem.getDateEvent());
        tvEventTime.setText(eventsItem.getStrTime());
        tvHomeClub.setText(eventsItem.getStrHomeTeam());
        tvAwayClub.setText(eventsItem.getStrAwayTeam());
        tvHomeScore.setText(eventsItem.getIntHomeScore());
        tvAwayScore.setText(eventsItem.getIntAwayScore());
        tvHomeGoals.setText(parser(eventsItem.getStrHomeGoalDetails()));
        tvAwayGoals.setText(parser(eventsItem.getStrAwayGoalDetails()));
        tvHomeShots.setText(parser(eventsItem.getIntHomeShots()));
        tvAwayShots.setText(parser(eventsItem.getIntAwayShots()));
        tvHomeGoalkeeper.setText(eventsItem.getStrHomeLineupGoalkeeper());
        tvAwayGoalkeeper.setText(eventsItem.getStrAwayLineupGoalkeeper());
        tvHomeDefense.setText(parser(eventsItem.getStrHomeLineupDefense()));
        tvAwayDefense.setText(parser(eventsItem.getStrAwayLineupDefense()));
        tvHomeMidlefield.setText(parser(eventsItem.getStrHomeLineupMidfield()));
        tvAwayMidlefield.setText(parser(eventsItem.getStrAwayLineupMidfield()));
        tvHomeForward.setText(parser(eventsItem.getStrHomeLineupForward()));
        tvAwayForward.setText(parser(eventsItem.getStrAwayLineupForward()));
        tvHomeSubtituties.setText(parser(eventsItem.getStrHomeLineupSubstitutes()));
        tvAwaySubtituties.setText(parser(eventsItem.getStrAwayLineupSubstitutes()));
    }

    private void bindUI() {
        tvEventDate = findViewById(R.id.eventDate);
        tvEventTime = findViewById(R.id.eventTime);
        imageHome = findViewById(R.id.homeEmblem);
        imageAway = findViewById(R.id.awayEmblem);
        tvHomeClub = findViewById(R.id.homeTeam);
        tvAwayClub = findViewById(R.id.awayTeam);
        tvHomeScore = findViewById(R.id.homeScore);
        tvAwayScore = findViewById(R.id.awayScore);
        tvHomeGoals = findViewById(R.id.homeGoals);
        tvAwayGoals = findViewById(R.id.awayGoals);
        tvHomeShots = findViewById(R.id.homeShots);
        tvAwayShots = findViewById(R.id.awayShots);
        tvHomeGoalkeeper = findViewById(R.id.homeGk);
        tvAwayGoalkeeper = findViewById(R.id.awayGk);
        tvHomeDefense = findViewById(R.id.homeDef);
        tvAwayDefense = findViewById(R.id.awayDef);
        tvHomeMidlefield = findViewById(R.id.homeMdf);
        tvAwayMidlefield = findViewById(R.id.awayMdf);
        tvHomeForward = findViewById(R.id.homeOfen);
        tvAwayForward = findViewById(R.id.awayOfen);
        tvHomeSubtituties = findViewById(R.id.homeSubs);
        tvAwaySubtituties = findViewById(R.id.awaySubs);
        toggleButton = findViewById(R.id.myToggleButton);
        mDatabaseHelper = new DatabaseHelper(this);
    }

    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    public void AddData (String newEntry){
        boolean insertData = mDatabaseHelper.addData(newEntry);
        if (insertData){
            toastMessage("Data telah berhasil masuk");
        }else {
            toastMessage("Data tidak masuk ada yang salah");
        }
    }

    private String parser(@Nullable String input) {
        String output = "";
        if (input != null) {
            output = input.replace(";", "\n");
        }
        return output;
    }

    @Override
    public void onRequestSuccess(TeamResponse data) {
        if (data.getTeams().get(0).getIdTeam().equals(eventsItem.getIdHomeTeam())) {
            Picasso.get().load(data.getTeams().get(0).getStrTeamBadge()).fit().into(imageHome);
        } else {
            Picasso.get().load(data.getTeams().get(0).getStrTeamBadge()).fit().into(imageAway);
        }
    }

    @Override
    public void onRequestFailed(String message) {
        Toast.makeText(this, "error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {}

    @Override
    public void hideLoading() {}


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
