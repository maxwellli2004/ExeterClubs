package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Models.Club;
import com.google.api.client.util.Lists;
import com.google.api.core.ApiFuture;
import com.google.api.gax.rpc.InternalException;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.UUID;

// ClubController is a service that handles all the CRUD operations for the Controller model
@Service
public class ClubController {
    public static List<Club> allClubs;
    public static final String COL_NAME="clubs";

    public static List<Club> getAllClubs() {
        try {
            allClubs = read();
            return allClubs;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Club getClub(String ID) {
        for (Club club : allClubs) {
            if (club.getId().toString().equals(ID)) {
                return club;
            }
        }

        return null;
    }
   

    // Reads all clubs in the database
    public static List<Club> read() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        // asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = db.collection(COL_NAME).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<Club> clubs = Lists.newArrayList();

        for (DocumentSnapshot document : documents) {
            clubs.add(document.toObject(Club.class));
        }

        return clubs;
    }

    // Creates a club in the database
    public static String create(Club club) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(club.getId().toString()).set(club);
        System.out.println("Created club: " + club.getId().toString() + " successfully.");
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public static String delete(String ID) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(ID).delete();
        System.out.println("Deleted club: " + ID + " successfully.");
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}
