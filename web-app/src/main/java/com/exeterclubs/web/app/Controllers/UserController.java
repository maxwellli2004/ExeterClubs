package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Models.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.ArrayList;

@Service
public class UserController {
    public static final String COL_NAME="users";
    
    // Creates a new user in the database
    public static String create(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getId().toString()).set(user);
        System.out.println("Created user: " + user.getId().toString() + " succesfully.");
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public static void read() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = dbFirestore.collection(COL_NAME);
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        QuerySnapshot querySnapshot = future.get();
        List<User> users = querySnapshot.toObjects(User.class);

        for (User user: users) {
            System.out.println(user.email);
        }
    }
}