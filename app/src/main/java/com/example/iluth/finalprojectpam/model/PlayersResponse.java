package com.example.iluth.finalprojectpam.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PlayersResponse{

	@SerializedName("player")
	private List<PlayerItem> player;

	public void setPlayer(List<PlayerItem> player){
		this.player = player;
	}

	public List<PlayerItem> getPlayer(){
		return player;
	}
}