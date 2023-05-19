package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Models.Club;
import com.google.api.client.util.Lists;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.List;

// ClubController is a service that handles all the CRUD operations for the Controller model
@Service
public class ClubController {
    public static List<Club> allClubs = Lists.newArrayList();
    public static List<Club> memberClubs = Lists.newArrayList();
    public static List<Club> coheadClubs = Lists.newArrayList();
    public static List<Club> nonClubs = Lists.newArrayList();
    public static final String COL_NAME="clubs";

    public static Club getClub(String ID) {
        for (Club club : allClubs) {
            if (club.getId().toString().equals(ID)) {
                return club;
            }
        }

        return null;
    }
   

    // Reads all clubs in the database
    public static List<Club> read(String email) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        // asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = db.collection(COL_NAME).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Club> clubs = Lists.newArrayList();

        allClubs.clear();
        coheadClubs.clear();
        memberClubs.clear();
        nonClubs.clear();


        for (DocumentSnapshot document : documents) {
            Club club = document.toObject(Club.class);

            removeNulls(club);

            clubs.add(club);

            filterClub(club, email);
        }

        System.out.println("Read all clubs successfully.");

        return clubs;
    }

    // Creates a club in the database
    public static String create(Club club, String email) throws InterruptedException, ExecutionException {
        removeNulls(club);
        
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(club.getId().toString()).set(club);
        System.out.println("Created club: " + club.getId().toString() + " successfully.");
        read(email);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public static String delete(String ID, String email) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(ID).delete();
        System.out.println("Deleted club: " + ID + " successfully.");
        read(email);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    // Prevents null exceptions
    private static void removeNulls(Club club) {
        if (club.getHeads() == null) {
            club.setHeads(Lists.newArrayList());
        }

        if (club.getMembers() == null) {
            club.setMembers(Lists.newArrayList());
        }
    }

    // Sorts the club into the appropriate list
    private static void filterClub(Club club, String email) {
        if (!allClubs.contains(club)) {
        allClubs.add(club);
        }

        if (club.getHeads().contains(email)) {
            if (!coheadClubs.contains(club)) {
                coheadClubs.add(club);
                System.out.println("Added club: " + club.getId().toString() + " to coheadClubs.");
            }
            memberClubs.remove(club);
            nonClubs.remove(club);
        } else if (club.getMembers().contains(email)) {
            if (!memberClubs.contains(club)) {
                memberClubs.add(club);
                System.out.println("Added club: " + club.getId().toString() + " to memberClubs.");
            }
            coheadClubs.remove(club);
            nonClubs.remove(club);
        } else {
            if (!nonClubs.contains(club)) {
                nonClubs.add(club);
                System.out.println("Added club: " + club.getId().toString() + " to nonClubs.");
            }
            coheadClubs.remove(club);
            memberClubs.remove(club);
        }
    }
}
