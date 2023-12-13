package com.svvg.rajbika.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.svvg.rajbika.sharedservice.dto.CreateUserRequest;
import com.svvg.rajbika.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private static final String USER_SERVICE_URL = "http://localhost:8082/api/v1/users";

    public UserRecord signUpInFirebase(SignupRequest signupRequest) throws FirebaseAuthException {
        UserRecord userRecord = createUserInFirebaseAuth(signupRequest.getEmail(), signupRequest.getPassword());
        return userRecord;
    }

    public ResponseEntity<String> saveUserInDB(SignupRequest signupRequest) {
        CreateUserRequest createUserRequest = CreateUserRequest.builder()
                .email(signupRequest.getEmail())
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .phoneNumber(signupRequest.getPhoneNumber())
                .build();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(USER_SERVICE_URL, createUserRequest, String.class);
        return responseEntity;
    }



    private UserRecord createUserInFirebaseAuth(String email, String password) throws FirebaseAuthException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(email)
                .setPassword(password);

        return FirebaseAuth.getInstance().createUser(request);
    }
    public void deleteFirebaseUser(String verificationId) {
        // Delete the Firebase user by verification ID
        // ...
    }
}
