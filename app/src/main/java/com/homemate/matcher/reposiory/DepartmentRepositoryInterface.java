package com.homemate.matcher.reposiory;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.homemate.matcher.models.SearchStatus;

public interface DepartmentRepositoryInterface {

    Task<DocumentSnapshot> getDepartmentDetail(String id);

    public Task<QuerySnapshot> getAllDepartments();

    public DocumentReference getDepartmentReference(String id);

}
