package com.homemate.matcher.reposiory;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.homemate.matcher.models.SearchStatus;

public class UserRepository implements UserRepositoryInterface{

    private static final String USERS_COLLECTION = "users";

    private CollectionReference usersCollection;

    public UserRepository() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        this.usersCollection = firebaseFirestore.collection(USERS_COLLECTION);
    }

    @Override
    public Task<DocumentSnapshot> getUserDetails(String id){
        return  this.usersCollection.document(id).get();
    }
    @Override
    public Task<QuerySnapshot> getAllUsers(){
        return  this.usersCollection.get();
    }

    @Override
    public Task<QuerySnapshot> filterUsersByNames(String name){

         return this.usersCollection
                                    .where(Filter.or(Filter.equalTo("firstName", name)
                                        , Filter.equalTo("lastName", name)))
                                    .get();
    }

    @Override
    public Task<QuerySnapshot> filterUsersByStatus(SearchStatus status){

         return this.usersCollection
                                    .where(Filter.equalTo("status", status.getValue()))
                                    .get();
    }

    @Override
    public Task<QuerySnapshot> filterUsersByDistance(Double distance){

         return this.usersCollection
                                    .where(Filter.equalTo("distance", distance))
                                    .get();
    }

    @Override
    public Task<QuerySnapshot> filterUsersByHostingTime(Long hostingTime){

         return this.usersCollection
                                    .where(Filter.equalTo("hostingTime", hostingTime))
                                    .get();
    }


}
