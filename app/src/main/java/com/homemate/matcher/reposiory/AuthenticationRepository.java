package com.homemate.matcher.reposiory;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationRepository implements AuthenticationRepositoryInterface{


    private FirebaseAuth firebaseAuth;

    public AuthenticationRepository() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public FirebaseUser getCurrentUser(){
        return firebaseAuth.getCurrentUser();
    }

    @Override
    public Task<AuthResult> signUp(String email, String password){
        return this.firebaseAuth.createUserWithEmailAndPassword(email, password);
    }

}
