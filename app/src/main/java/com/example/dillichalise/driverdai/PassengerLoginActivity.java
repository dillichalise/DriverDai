package com.example.dillichalise.driverdai;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PassengerLoginActivity extends Activity {

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progress;


    EditText email, password;
    String Lemail, Lpassword;
    String st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_login);

        firebaseAuth = FirebaseAuth.getInstance();
        progress = new ProgressDialog(this);

        if (firebaseAuth.getCurrentUser() != null) {
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), PassengerProfileActivity.class));
        }
        email = (EditText) findViewById(R.id.pemail);
        password = (EditText) findViewById(R.id.ppassword);
    }

    public void onPLogInClick(View v) {
        Lemail = email.getText().toString();
        Lpassword = password.getText().toString().trim();
        if (TextUtils.isEmpty(Lemail)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(Lpassword)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        progress.setMessage("Logging In,   please wait");
        progress.show();

        firebaseAuth.signInWithEmailAndPassword(Lemail, Lpassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {
                    startActivity(new Intent(PassengerLoginActivity.this, PassengerProfileActivity.class));
                    Toast.makeText(PassengerLoginActivity.this, "Welcome " + email.getText(), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Log.e("PassengerLogin", task.getException().getMessage());
                    Toast.makeText(PassengerLoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }
                progress.dismiss();
            }
        });
    }

    public void onPRegisterClick(View v) {
        if (v.getId() == R.id.psignup) {
            finish();
            Intent j = new Intent(PassengerLoginActivity.this, PassengerRegisterActivity.class);
            startActivity(j);
        }
    }


}







