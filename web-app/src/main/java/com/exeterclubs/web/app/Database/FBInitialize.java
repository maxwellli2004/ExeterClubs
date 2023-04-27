package com.exeterclubs.web.app.Database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import org.springframework.stereotype.Service;

@Service
public class FBInitialize {
  @PostConstruct
  public void initialize() {
      try {

          FileInputStream serviceAccount = new FileInputStream("path/to/serviceAccountKey.json");

          FirebaseOptions options = new FirebaseOptions.Builder()
                                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                                            .setDatabaseUrl("https://exeterclubs-default-rtdb.firebaseio.com")
                                            .build();
          
          FirebaseApp.initializeApp(options);
      }
      catch(Exception e) {
          System.out.println("Error Occured: " + e.getLocalizedMessage());
      }
  }
    
}