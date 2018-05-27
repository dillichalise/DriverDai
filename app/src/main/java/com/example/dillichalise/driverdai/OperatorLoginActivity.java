package com.example.dillichalise.driverdai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OperatorLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_login);
    }

    public void onOPLogInClick(View v) {
        EditText ou = (EditText) findViewById(R.id.opuname);
        EditText op = (EditText) findViewById(R.id.oppassword);
        String ouser = ou.getText().toString();
        String opass = op.getText().toString();

        if (((ouser.equals(""))&&(opass.equals("")))|((ouser.equals("bus0001"))&&(opass.equals("123456")))||((ouser.equals("bus0002"))&&(opass.equals("234567")))||((ouser.equals("bus0003"))&&(opass.equals("345678")))) {
            Intent intent = new Intent(OperatorLoginActivity.this, MapActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Welcome " + ou.getText(), Toast.LENGTH_LONG).show();
        } else {
            Toast pass1 = Toast.makeText(OperatorLoginActivity.this, "Username and password don't match!", Toast.LENGTH_SHORT);
            pass1.show();
        }
        op.setText("");
        ou.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OperatorLoginActivity.this, WelcomeActivity.class);
        startActivity(intent);
    }


}
