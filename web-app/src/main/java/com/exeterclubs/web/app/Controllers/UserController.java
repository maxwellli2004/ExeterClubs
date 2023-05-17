package com.exeterclubs.web.app.Controllers;

import com.exeterclubs.web.app.Models.*;
import com.google.api.client.util.Lists;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.UUID;

// UserController is a service that handles all the CRUD operations for the User model
@Service
public class UserController {
    public static final String COL_NAME="users";

    public static List<User> getAllUsers() {
        try {
            return read();
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    // Create a user in the database
    public static String create(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getId().toString()).set(user);
        System.out.println("Created user: " + user.getId().toString() + " successfully.");
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    // Reads all users in the database
    public static List<User> read() throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        // asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = db.collection(COL_NAME).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<User> users = Lists.newArrayList();

        for (DocumentSnapshot document : documents) {
            users.add(document.toObject(User.class));
        }

        return users;
    }

    // Updates a user in the database
    public static String update(String ID, User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(ID).set(user);
        System.out.println("Updated user: " + ID + " successfully.");
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    // Deletes a user in the database
    public static String delete(String ID) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(ID).delete();
        System.out.println("Deleted user: " + ID + " sucesfully.");
        return writeResult.get().getUpdateTime().toString();
    }

    // Gets a user by their ID
    public static User getUserByID(String id) {
        try {
            List<User> users = read();
            for (User user : users) {
                if (user.getId().equals(id)) {
                    return user;
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static User getUserByEmail(String email) {
        try {
            List<User> users = read();
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    return user;
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
}