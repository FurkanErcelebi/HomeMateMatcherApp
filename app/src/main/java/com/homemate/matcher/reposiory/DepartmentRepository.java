package com.homemate.matcher.reposiory;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.homemate.matcher.models.SearchStatus;

public class DepartmentRepository implements DepartmentRepositoryInterface{

    private static final String DEPARTMENTS_COLLECTION = "departments";

    private CollectionReference usersCollection;

    public DepartmentRepository() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        this.usersCollection = firebaseFirestore.collection(DEPARTMENTS_COLLECTION);
    }


    @Override
    public Task<DocumentSnapshot> getDepartmentDetail(String id) {
        return  this.usersCollection.document(id).get();
    }

    @Override
    public Task<QuerySnapshot> getAllDepartments() {
        return null;
    }
}
