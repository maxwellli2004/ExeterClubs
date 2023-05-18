package com.exeterclubs.web.app.Auth;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

// Handles all Authentication requests to FireBase
public class AuthService {
    private final static FirebaseAuth auth = FirebaseAuth.getInstance();
    private final static String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyApdEMjVnLlD3DUu5eYkJBcZSuxoN7GF6k";
    // Creates a new user on FireBase
    public static void createUser(String email, String password) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
            .setEmail(email)
            .setPassword(password);

        auth.createUser(request);
        
        System.out.println("Created user " + email + " successfully");
    }

    // Validates user on FireBase
    public static String validateUser(String email, String password) throws FirebaseAuthException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\n\t\"email\": \"" + email + "\",\n\t\"password\": \"" + password + "\",\n\t\"returnSecureToken\": true\n}"))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        String idToken = parseObject(body);

        System.out.println("User " + email + " validated successfully");

        return idToken;
    }

    // Parses the JSON response from FireBase and returns the idToken
    private static String parseObject(String body) {
        JsonElement element = JsonParser.parseString(body);
        String idToken = element.getAsJsonObject().get("idToken").toString();
        // String refreshToken = object.get("refreshToken").toString();

        return idToken;
    }

     public static void addAuth(Model model, HttpSession session) {
        String idToken = (String) session.getAttribute("idToken");

        Boolean authenticated = idToken != null;

        System.out.println("Authenticated: " + authenticated);
        
        model.addAttribute("authenticated", authenticated);
    }
}
