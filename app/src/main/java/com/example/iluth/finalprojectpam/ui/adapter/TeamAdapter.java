package com.example.iluth.finalprojectpam.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iluth.finalprojectpam.R;
import com.example.iluth.finalprojectpam.model.EventsItem;
import com.example.iluth.finalprojectpam.model.TeamsItem;
import com.example.iluth.finalprojectpam.ui.activity.TeamDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private Context context;
    private List<TeamsItem> teamsItems;

    public TeamAdapter(Context context, List<TeamsItem> teamsItems) {
        this.context = context;
        this.teamsItems = teamsItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_team,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(teamsItems.get(position));
    }

    @Override
    public int getItemCount() {
        return teamsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgTeamBadge;
        private TextView tvTeamName;
        public ViewHolder(View itemView) {
            super(itemView);
            imgTeamBadge = itemView.findViewById(R.id.imgTeamBadge);
            tvTeamName = itemView.findViewById(R.id.tvTeamName);

        }

        @SuppressLint("DefaultLocale")
        public void bindData(final TeamsItem teamsItem){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, TeamDetailActivity.class);
                    intent.putExtra("team", teamsItem);
                    context.startActivity(intent);
                }
            });
            tvTeamName.setText(teamsItem.getStrTeam());
            Picasso.get().load(teamsItem.getStrTeamBadge()).fit().into(imgTeamBadge);
        }

    }
}
