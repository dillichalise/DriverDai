package com.example.dillichalise.driverdai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PassengerLoginActivity extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_login);
    }

    public void onPLogInClick(View v) {
        if (v.getId() == R.id.plogin) {
            EditText a = (EditText) findViewById(R.id.puname);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.ppassword);
            String pass = b.getText().toString();
            String password = helper.searchPass(str);

            if (pass.equals(password)) {
                Intent intent = new Intent(PassengerLoginActivity.this, MapActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Welcome " + a.getText(), Toast.LENGTH_LONG).show();
            } else {
                Toast pass1 = Toast.makeText(PassengerLoginActivity.this, "Username and password don't match!", Toast.LENGTH_SHORT);
                pass1.show();
            }

            a.setText("");
            b.setText("");
        }
    }

    public void onPRegisterClick(View v) {
        if (v.getId() == R.id.psignup) {
            Intent j = new Intent(PassengerLoginActivity.this, PassengerRegisterActivity.class);
            startActivity(j);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PassengerLoginActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }
}








