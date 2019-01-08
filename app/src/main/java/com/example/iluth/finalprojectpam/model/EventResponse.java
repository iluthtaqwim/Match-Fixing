package com.example.iluth.finalprojectpam.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EventResponse{

	@SerializedName("events")
	private List<EventsItem> events;

	public List<EventsItem> getEvents(){
		return events;
	}
}