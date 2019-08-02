package com.example.pengendaliapi.debtbook;

import android.content.Intent;
import android.os.Bundle;

import com.example.pengendaliapi.debtbook.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText edEmail, edUsername, edFullname, edPhone, edPassword, edReTypePassword;
    private Button btnSubmit;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edEmail = (EditText) findViewById(R.id.EdEmail);
        edUsername = (EditText) findViewById(R.id.EdUsername);
        edFullname = (EditText) findViewById(R.id.EdFullname);
        edPhone = (EditText) findViewById(R.id.EdPhone);
        edPassword = (EditText) findViewById(R.id.EdPassword);
        edReTypePassword = (EditText) findViewById(R.id.EdRePassword);
        btnSubmit = (Button) findViewById(R.id.btnSubmitReg);

        mAuth = FirebaseAuth.getInstance();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edEmail.getText().toString().trim();
                String username = edUsername.getText().toString().trim();
                String fullname  = edFullname.getText().toString().trim();
                String phone = edPhone.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                String retype = edReTypePassword.getText().toString().trim();

                if (email.isEmpty() && username.isEmpty() && fullname.isEmpty() && phone.isEmpty() && password.isEmpty() && retype.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "please Complete field", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(retype)){
                    Toast.makeText(RegisterActivity.this, "your password is different", Toast.LENGTH_SHORT).show();
                } else {
                    createNewAccount();
                    sendUserToDashboardActivity();
                }
            }
        });

    }

    private void createNewAccount(){
        String email = edEmail.getText().toString().trim();
        String password = edPassword.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    String uid = mAuth.getCurrentUser().getUid();
                    String email = edEmail.getText().toString().trim();
                    String username = edUsername.getText().toString().trim();
                    String fullname  = edFullname.getText().toString().trim();
                    String phone = edPhone.getText().toString().trim();
                    String password = edPassword.getText().toString().trim();

                    Users users = new Users(uid,email, username, fullname, phone, password);
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
                    mDatabase.child(uid).setValue(users);

                    Toast.makeText(RegisterActivity.this, "Account has been created", Toast.LENGTH_SHORT).show();

                } else {
                    String errorMsg = task.getException().getMessage();
                    Toast.makeText(RegisterActivity.this, "Failed : "+ errorMsg, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void sendUserToDashboardActivity(){
        Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}
