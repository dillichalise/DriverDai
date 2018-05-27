package com.example.dillichalise.driverdai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PassengerRegisterActivity extends AppCompatActivity {
    EditText name, username, mn, email, password, reenterPassword;
    String nameStr, userNameStr,mnStr,emailStr,passwordStr,reenterPasswordStr;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_register);
    }


    public void onPSignUpClick(View v) {
        if (v.getId() == R.id.button2) {
             name = (EditText) findViewById(R.id.pname);
             username = (EditText) findViewById(R.id.puname);
             mn = (EditText) findViewById(R.id.pmn);
             email = (EditText) findViewById(R.id.pemail);
             password = (EditText) findViewById(R.id.ppassword);
             reenterPassword = (EditText) findViewById(R.id.preenterpass);

             nameStr = name.getText().toString();
             userNameStr = username.getText().toString();
             mnStr = mn.getText().toString();
             emailStr = email.getText().toString();
             passwordStr = password.getText().toString();
             reenterPasswordStr = reenterPassword.getText().toString();
            if (!validate()) {
                Toast pass = Toast.makeText(PassengerRegisterActivity.this, "Register Failed !!", Toast.LENGTH_SHORT);
                pass.show();
            } else {
                Contact c = new Contact();
                c.setName(nameStr);
                c.setUname(userNameStr);
                c.setMn(mnStr);
                c.setEmail(emailStr);
                c.setPass(passwordStr);
                helper.insertContact(c);

                Toast pass2 = Toast.makeText(PassengerRegisterActivity.this, "Account Successfully Created.", Toast.LENGTH_SHORT);
                pass2.show();

                name.setText("");username.setText("");
                mn.setText("");
                email.setText("");
                password.setText("");
                reenterPassword.setText("");

                Intent i = new Intent(PassengerRegisterActivity.this, PassengerLoginActivity.class);
                startActivity(i);
            }
        }
    }

    public boolean validate() {
        boolean valid = true;
        if (name.equals("")||name.length()<5){
            name.setError("Please enter valid name !!");
            valid = false;
        }

        if (username.equals("")||username.length()>20){
            username.setError("Please enter valid username !!");
            valid = false;
        }

        if (mn.equals("")|| !(mnStr.length() ==10)){
            mn.setError("Please enter valid mn !!");
            valid = false;
        }

        if (email.equals("")||!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
            email.setError("Please enter valid email address !!");
            valid = false;
        }

        if (!passwordStr.equals(reenterPasswordStr)){
            password.setError("Password do not match !!");
            valid = false;
        }

    /*    if (password.equals("")||reenterPassword.equals("")){
            password.setError("Please enter password !!");
            valid = false;
        }
    */

        if ((passwordStr.length()<6) || (reenterPasswordStr.length()<6)){
            password.setError("Please enter at least 8 characters !!");
            valid = false;
        }

        return valid;
    }

    public void onPLoginClick(View v) {
        if (v.getId() == R.id.plogin1) {
            Intent i = new Intent(PassengerRegisterActivity.this, PassengerLoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(PassengerRegisterActivity.this, PassengerLoginActivity.class);
        startActivity(intent);
    }



}
