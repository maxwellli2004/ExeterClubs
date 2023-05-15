package com.exeterclubs.web.app.Models;

import java.util.UUID;

public class Club {
	public boolean approved;
	public String description;
	public UUID[] heads;
	public UUID[] members;
	//public Announcements[] announcements;
	//public Date[] meetingTimes;
	public UUID id;
	
	public Club() { }

	public Club(boolean approved, String description, UUID[] heads, UUID[] members, UUID id) {
        this.approved = approved;
        this.description = description;
        this.heads = heads;
        this.members = members;
        //this.announcements = announcements;
        //this.meetingTimes = meetingTimes;
        this.id = id;
    }
	
	// Getters and setters
	public boolean getApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID[] getHeads() {
		return heads;
	}

	public void setHeads(UUID[] heads) {
		this.heads = heads;
	}

	public UUID[] getMembers() {
		return members;
	}

	public void setMembers(UUID[] members) {
		this.members = members;
	}

	/*public Announcements[] getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(Announcements[] announcements) {
		this.announcements = announcements;
	}

	public Date[] getMeetingTimes() {
		return meetingTimes;
	}

	public void setMeetingTimes(Date[] meetingTimes) {
		this.meetingTimes = meetingTimes;
	}*/

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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
