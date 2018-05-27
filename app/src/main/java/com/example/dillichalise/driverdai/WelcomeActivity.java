package com.example.dillichalise.driverdai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by dillichalise on 3/3/18.
 */

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onOperatorSelectClick(View v) {
        if (v.getId() == R.id.OperatorSelect) {
            Intent j = new Intent(WelcomeActivity.this, OperatorLoginActivity.class);
            startActivity(j);
        }
    }

    public void onPassengerSelectClick(View v) {
        if (v.getId() == R.id.PassengerSelect) {
            Intent j = new Intent(WelcomeActivity.this, PassengerLoginActivity.class);
            startActivity(j);
        }
    }

}
