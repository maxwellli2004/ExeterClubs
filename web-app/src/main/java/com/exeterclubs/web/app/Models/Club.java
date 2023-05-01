package com.exeterclubs.web.app.Models;

import java.util.UUID;
import java.util.Optional;

public class Club {
	public boolean approved;
	public String description;
	public UUID[] heads;
	public UUID[] members;
	//public Announcements[] announcements;
	//public Date[] meetingTimes;
	public UUID id;
	
	public Club(boolean approved, String description, UUID[] heads, UUID[] members, UUID id) {
        this.approved = approved;
        this.description = description;
        this.heads = heads;
        this.members = members;
        //this.announcements = announcements;
        //this.meetingTimes = meetingTimes;
        this.id = id;
    }
	`
	/*
	TODO: Implement 

	public static UUID findClub() {
		//check database for club
		
	}

	public static UUID findClub() {
		//check database for approved club
	}
	
	public static void approveClub{Club club) {
		//approve club
	}
	
	public User addUser(User user) {
		//add user from db
	}
	
	public User removeUser(User user) {
		//remove user from db
	}
	*/
}
