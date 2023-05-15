package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Models.Club;
import com.google.api.client.util.Lists;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.UUID;

// ClubController is a service that handles all the CRUD operations for the Controller model
@Service
public class ClubController {
    public static final String COL_NAME="clubs";

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
}
