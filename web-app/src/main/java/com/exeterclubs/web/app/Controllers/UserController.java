package com.exeterclubs.web.app.Controllers;
import com.exeterclubs.web.app.Models.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class UserController {
    public static final String COL_NAME="users";
    
    public static String savePatientDetails(User user) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(user.getId().toString()).set(user);
        System.out.println("Created user: " + user.getId().toString() + " succesfully.");
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
}