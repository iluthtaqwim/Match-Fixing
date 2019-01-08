package com.example.iluth.finalprojectpam.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.iluth.finalprojectpam.R;
import com.example.iluth.finalprojectpam.model.EventResponse;
import com.example.iluth.finalprojectpam.model.EventsItem;
import com.example.iluth.finalprojectpam.ui.activity.EventDetailActivity;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private Context context;
    private List<EventsItem> eventsItemList;

    public EventAdapter(Context context, List<EventsItem> eventsItemList) {
        this.context = context;
        this.eventsItemList = eventsItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_match,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(eventsItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textviewhome;
        TextView textviewaway;
        TextView textviewjam;
        TextView textviewtanggal;
        TextView tvHomeScore;
        TextView tvAwayScore;

        public ViewHolder(View itemView) {
            super(itemView);
            textviewhome = itemView.findViewById(R.id.tvHomeTeam);
            textviewaway = itemView.findViewById(R.id.tvAwayTeam);
            textviewjam = itemView.findViewById(R.id.tvMatchTime);
            textviewtanggal = itemView.findViewById(R.id.tvMatchDate);
            tvHomeScore = itemView.findViewById(R.id.tvHomeScore);
            tvAwayScore = itemView.findViewById(R.id.tvAwayScore);

        }

        @SuppressLint("DefaultLocale")
        public void bindData(final EventsItem eventsItem){
            textviewhome.setText(eventsItem.getStrHomeTeam());
            textviewaway.setText(eventsItem.getStrAwayTeam());
            textviewtanggal.setText(eventsItem.getStrDate());
            textviewjam.setText((eventsItem.getStrTime()));
            if(eventsItem.getIntHomeScore() != null){
                tvHomeScore.setText(eventsItem.getIntHomeScore());
            }

            if(eventsItem.getIntAwayScore() != null){
                tvAwayScore.setText(eventsItem.getIntAwayScore());
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*TODO create EventDetailActivity*/
                    Intent intent = new Intent(context, EventDetailActivity.class);
                    intent.putExtra("event", eventsItem);
                    context.startActivity(intent);
                }
            });
        }

    }
}
