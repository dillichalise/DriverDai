package com.example.dillichalise.driverdai;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class PassengerRegisterActivity extends AppCompatActivity {


    String RName, RUsername, RMobile, REmail, RPassword, RReenterpass;
    EditText name, username, mobile, email, password, reenterPassword;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_register);

        firebaseAuth = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);
        name = (EditText) findViewById(R.id.pname);
        username = (EditText) findViewById(R.id.pemail);
        mobile = (EditText) findViewById(R.id.pmn);
        email = (EditText) findViewById(R.id.pemail);
        password = (EditText) findViewById(R.id.ppassword);
        reenterPassword = (EditText) findViewById(R.id.preenterpass);

    }

    public void onPSignUpClick(View v) {

        RName = name.getText().toString().trim();
        RUsername = username.getText().toString().trim();
        RMobile = mobile.getText().toString().trim();
        REmail = email.getText().toString().trim();
        RPassword = password.getText().toString().trim();
        RReenterpass = reenterPassword.getText().toString().trim();

        progress.setMessage("Registering,   please wait");
        progress.show();

        firebaseAuth.createUserWithEmailAndPassword(REmail, RPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(PassengerRegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), PassengerProfileActivity.class));
                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(FirebaseAuth.getInstance().getCurrentUser());
                    FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("balance").setValue(400);

                } else {
                    Log.e("PassengerRegister", task.getException().getMessage());
                    Toast.makeText(PassengerRegisterActivity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();

                }
                progress.dismiss();
            }
        });


    }

    public void onPLoginClick(View v) {

        finish();
        startActivity(new Intent(this, PassengerLoginActivity.class));
    }


}









