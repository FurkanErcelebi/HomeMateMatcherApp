package com.homemate.matcher.reposiory;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.homemate.matcher.models.SearchStatus;

public interface UserRepositoryInterface {

    Task<DocumentSnapshot> getUserDetails(String id);

    public Task<QuerySnapshot> getAllUsers();
    public Task<QuerySnapshot> filterUsersByNames(String name);
    public Task<QuerySnapshot> filterUsersByStatus(SearchStatus status);
    public Task<QuerySnapshot> filterUsersByDistance(Double distance);
    public Task<QuerySnapshot> filterUsersByHostingTime(Long hostingTime);

}
