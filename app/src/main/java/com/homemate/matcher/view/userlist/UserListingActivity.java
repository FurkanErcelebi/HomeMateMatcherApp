package com.homemate.matcher.view.userlist;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.homemate.matcher.R;
import com.homemate.matcher.models.BriefUser;
import com.homemate.matcher.models.Department;
import com.homemate.matcher.models.SearchStatus;
import com.homemate.matcher.models.User;
import com.homemate.matcher.reposiory.UserRepository;
import com.homemate.matcher.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class UserListingActivity extends AppCompatActivity {

    private UserRepository userRepository;

    private RecyclerView userListView;

    private List<BriefUser> briefUserList;

    private UsersListAdapter usersListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);
        this.userListView = findViewById(R.id.userListView);

        this.userRepository = new UserRepository();
        this.briefUserList = new ArrayList<>();
        this.usersListAdapter = new UsersListAdapter(this, briefUserList);

        this.userRepository.getAllUsers()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            loadUsers(task.getResult().getDocuments());
                        }
                        else {
                            System.err.println("Cannot get result");
                        }
                    }
                });



        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                                                                LinearLayoutManager.VERTICAL
                                                                ,false);

        this.userListView.setLayoutManager(layoutManager);
        this.userListView.setAdapter(this.usersListAdapter);
        this.userListView.addOnItemTouchListener(new UserListTouchListener(getApplicationContext(),
                new UserListTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(UserListingActivity.this, UserDetailActivity.class);
                        intent.putExtra("firebase_id", briefUserList.get(position).getId());
                        startActivity(intent);
                    }
                }));

    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadUsers(List<DocumentSnapshot> documentSnapshotList){

        this.briefUserList = new Utils().convertDocumentsToUserObject(documentSnapshotList)
                        .stream().map((userInfo) ->
                        new BriefUser(userInfo.id, userInfo.getFirstName()
                                    , userInfo.getLastName()
                                    , userInfo.getSearchStatus())).collect(Collectors.toList());

        this.usersListAdapter = new UsersListAdapter(this, this.briefUserList);
        this.userListView.setAdapter(this.usersListAdapter);
    }

}
