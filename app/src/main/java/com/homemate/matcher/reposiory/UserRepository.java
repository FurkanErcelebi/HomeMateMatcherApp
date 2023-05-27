package com.homemate.matcher.reposiory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.homemate.matcher.models.SearchStatus;
import com.homemate.matcher.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

    private static final String USERS_COLLECTION = "users";

    private CollectionReference usersCollection;
    private ObjectMapper objectMapper;

    public UserRepository() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        this.usersCollection = firebaseFirestore.collection(USERS_COLLECTION);
        this.objectMapper = new ObjectMapper();
    }

    private List<User> convertDocumentsToUserObject(List<DocumentSnapshot> documentSnapshots){
        return documentSnapshots.stream().map((doc) ->
                objectMapper.convertValue(doc.getData(), User.class)).collect(Collectors.toList());
    }

    public List<User> getAllUsers(){
        return  convertDocumentsToUserObject(this.usersCollection.get().getResult().getDocuments());
    }

    public List<User> filterUsersByNames(String name){

         return convertDocumentsToUserObject(this.usersCollection
                                    .where(Filter.or(Filter.equalTo("firstName", name)
                                        , Filter.equalTo("lastName", name)))
                                    .get().getResult().getDocuments());
    }

    public List<User> filterUsersByStatus(SearchStatus status){

         return convertDocumentsToUserObject(this.usersCollection
                                    .where(Filter.equalTo("status", status.getValue()))
                                    .get().getResult().getDocuments());
    }

    public List<User> filterUsersByDistance(Double distance){

         return convertDocumentsToUserObject(this.usersCollection
                                    .where(Filter.equalTo("distance", distance))
                                    .get().getResult().getDocuments());
    }

    public List<User> filterUsersByHostingTime(Long hostingTime){

         /*this.usersCollection
                                    .where(Filter.equalTo("hostingTime", hostingTime))
                                    .get()
                 .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                     @Override
                     public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                         queryDocumentSnapshots.getDocuments();
                     }
                 });*/
         return convertDocumentsToUserObject(this.usersCollection
                                    .where(Filter.equalTo("hostingTime", hostingTime))
                                    .get().getResult().getDocuments());
    }

}
