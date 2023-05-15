package com.exeterclubs.web.app.Models;

import java.util.UUID;

public class Club {
	private String name;
	private boolean approved;
	private String description;
	private UUID[] heads;
	private UUID[] members;
	//public Announcements[] announcements;
	//public Date[] meetingTimes;
	private String id;
	
	public Club() { }

	public Club(String name, boolean approved, String description, UUID[] heads, UUID[] members, String id) {
		this.name = name;
        this.approved = approved;
        this.description = description;
        this.heads = heads;
        this.members = members;
        //this.announcements = announcements;
        //this.meetingTimes = meetingTimes;
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
