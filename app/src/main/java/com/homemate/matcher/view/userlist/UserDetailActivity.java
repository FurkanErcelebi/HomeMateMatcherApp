package com.homemate.matcher.view.userlist;


import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.homemate.matcher.R;
import com.homemate.matcher.databinding.UserDetailsBinding;
import com.homemate.matcher.reposiory.DepartmentRepository;
import com.homemate.matcher.reposiory.UserRepository;
import com.homemate.matcher.utils.Utils;

import java.util.Map;

import io.grpc.okhttp.internal.Util;

public class UserDetailActivity extends AppCompatActivity {

    private TextView firstNameText;
    private TextView lastNameText;
    private TextView departmentText;
    private TextView durationText;
    private TextView distanceText;
    private TextView statusText;
    private UserRepository userRepository;
    private DepartmentRepository departmentRepository;

    private UserDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String id = (String) bundle.get("firebase_id");

        binding = UserDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.userRepository = new UserRepository();
        this.departmentRepository = new DepartmentRepository();
        this.userRepository.getUserDetails(id)
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                setUserDetails(document.getData());
                            } else {
                                System.err.println("Data isn not exist");
                            }
                        } else {
                            System.err.println("Cannot get result");
                        }
                    }
                });
    }

    private void setUserDetails(Map<String, Object> docData){

        this.firstNameText = binding.firstName;
        this.lastNameText = binding.lastName;
        this.departmentText = binding.department;
        this.durationText = binding.duration;
        this.distanceText = binding.distance;
        this.statusText = binding.status;
        firstNameText.setText((String) docData.get("firstName"));
        lastNameText.setText((String) docData.get("lastName"));
        durationText.setText(String.valueOf(docData.get("hostingDuration")));
        distanceText.setText(String.valueOf(docData.get("distanceToCampus")));
        departmentRepository.getDepartmentDetail(
                                    ((DocumentReference) docData.get("department")).getId())
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String departmenName = (String) document.getData().get("name");
                                departmentText.setText(departmenName);
                            } else {
                                System.err.println("Data is not exist");
                            }
                        } else {
                            System.err.println("Cannot get result");
                        }
                    }
                });

    }
}
