package com.homemate.matcher.reposiory;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public interface AuthenticationRepositoryInterface {
    FirebaseUser getCurrentUser();

    Task<AuthResult> signUp(String email, String password);
}
