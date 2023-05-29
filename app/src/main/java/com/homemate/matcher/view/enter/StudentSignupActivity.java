package com.homemate.matcher.view.enter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.homemate.matcher.databinding.ActivityStudentsignupBinding;
import com.homemate.matcher.models.Department;
import com.homemate.matcher.models.SearchStatus;
import com.homemate.matcher.models.User;
import com.homemate.matcher.reposiory.AuthenticationRepository;
import com.homemate.matcher.reposiory.DepartmentRepository;
import com.homemate.matcher.utils.Utils;

import java.io.Console;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class StudentSignupActivity extends AppCompatActivity {

    private DepartmentRepository departmentRepository;
    private AuthenticationRepository authenticationRepository;

    private Button signupButton ;
    private Button sendTokenButton ;
    private EditText firstName ;
    private EditText lastName ;
    private Spinner department ;
    private String selectedDepartment ;
    private List<Department> departmentList;
    private Spinner status ;
    private String selectedStatus ;
    private EditText distanceFrom ;
    private EditText hostingDuration ;
    private EditText phoneNumber ;
    private EditText email ;
    private EditText schoolEmail ;
    private EditText password ;
    private EditText tokenValue ;
    private ActivityStudentsignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentsignupBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        this.departmentRepository = new DepartmentRepository();
        this.authenticationRepository = new AuthenticationRepository();
        this.signupButton = binding.signUp;
        this.sendTokenButton = binding.sendToken;
        this.firstName = binding.firstName;
        this.lastName = binding.lastName;
        this.department = binding.department;
        this.status = binding.status;
        this.distanceFrom = binding.distanceFrom;
        this.hostingDuration = binding.hostingDuration;
        this.phoneNumber = binding.phoneNumber;
        this.email = binding.email;
        this.schoolEmail = binding.schoolEmail;
        this.password = binding.password;
        this.tokenValue = binding.tokenValue;
        tokenValue.setVisibility(View.INVISIBLE);


        this.departmentRepository.getAllDepartments()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                departmentList = new Utils()
                                                .convertDocumentToDepartmentObject(
                                                        task.getResult().getDocuments());
                                List<String> departmentItemList =
                                        departmentList.stream().map((doc) -> {
                                    return  doc.getName() + " (" + doc.getLabel() + ")";
                                }) .collect(Collectors.toList());
                                ArrayAdapter<String> departmentAdapter =
                                        new ArrayAdapter<String>(StudentSignupActivity.this,
                                        android.R.layout.simple_spinner_item, departmentItemList);

                                departmentAdapter.setDropDownViewResource(
                                        android.R.layout.simple_spinner_dropdown_item);
                                department.setAdapter(departmentAdapter);
                                department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        selectedDepartment = adapterView.getItemAtPosition(i).toString();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                            } else {
                                Toast.makeText(StudentSignupActivity.this,
                                        "Give error for ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        List<String> statusList =  Arrays.stream(SearchStatus.values()).map((sts) ->
                        new Utils().getStatusViews(sts.getValue()))
                .collect(Collectors.toList());
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(StudentSignupActivity.this,
                android.R.layout.simple_spinner_item, statusList);

        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(statusAdapter);
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedStatus = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                SearchStatus searchStatus = new Utils().getStatusFromViewa(selectedStatus);
                Department departmentIns = departmentList.stream().filter((doc) ->
                        selectedDepartment.endsWith( doc.getLabel() + ")")).findFirst().get();
                /*User user = new User(firstName.getText().toString(),
                        lastName.getText().toString(),
                        searchStatus,
                        departmentIns ,
                        Long.parseLong(distanceFrom.getText().toString()),
                        Long.parseLong(hostingDuration.getText().toString()),
                        Boolean.FALSE,
                        email.getText().toString(),
                        schoolEmail.getText().toString(),
                        phoneNumber.getText().toString());*/


//                SharedPreferences sharedPreferences =
//                        getSharedPreferences("com.graduate.app", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                Set<String> user_infos = new HashSet<>();
//                user_infos.add("firstname: " + firstnameValue);
//                user_infos.add("lastname: " + lastnameValue);
//                user_infos.add("entranceYear: " + entanceDateValue);
//                user_infos.add("graduateYear: " + graduateDateValue);
//                user_infos.add("email: " + emailValue);
//                user_infos.add("password: " + passwordValue);
//                editor.putStringSet(UUID.randomUUID().toString(), user_infos);
//                editor.apply();

                startActivity(new Intent(StudentSignupActivity.this,
                        StudentLoginActivity.class));
            }

        });
        signupButton.setVisibility(View.INVISIBLE);

        sendTokenButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String schoolEmailValue = schoolEmail.getText().toString();
                String passwordValue = password.getText().toString();

                authenticationRepository.signUp(schoolEmailValue, passwordValue)
                        .addOnCompleteListener(StudentSignupActivity.this,
                                new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = authenticationRepository.getCurrentUser();

                                    user.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(
                                                                StudentSignupActivity.this,
                                                                "Email verification sended",
                                                                Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    Toast.makeText(StudentSignupActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

//                SharedPreferences sharedPreferences =
//                        getSharedPreferences("com.graduate.app", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                Set<String> user_infos = new HashSet<>();
//                user_infos.add("firstname: " + firstnameValue);
//                user_infos.add("lastname: " + lastnameValue);
//                user_infos.add("entranceYear: " + entanceDateValue);
//                user_infos.add("graduateYear: " + graduateDateValue);
//                user_infos.add("email: " + emailValue);
//                user_infos.add("password: " + passwordValue);
//                editor.putStringSet(UUID.randomUUID().toString(), user_infos);
//                editor.apply();

                signupButton.setVisibility(View.VISIBLE);
                tokenValue.setVisibility(View.INVISIBLE);
                sendTokenButton.setVisibility(View.INVISIBLE);
            }

        });



    }



}