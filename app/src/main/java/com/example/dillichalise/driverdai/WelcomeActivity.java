package com.example.dillichalise.driverdai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by dillichalise on 3/3/18.
 */

public class WelcomeActivity extends Activity {
    boolean doubleTap= false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onOperatorSelectClick(View v) {
        if (v.getId() == R.id.OperatorSelect) {
            finish();
            Intent j = new Intent(WelcomeActivity.this, OperatorLoginActivity.class);
            startActivity(j);
        }
    }

    public void onPassengerSelectClick(View v) {
        if (v.getId() == R.id.PassengerSelect) {
            finish();
            Intent j = new Intent(WelcomeActivity.this, PassengerLoginActivity.class);
            startActivity(j);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleTap) {
            super.onBackPressed();
        }
        else {
            Toast.makeText(this, "Press back again to exit the app!", Toast.LENGTH_SHORT).show();
            doubleTap = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleTap = false;
                }
            }, 500); //500 means half second.
        }
    }
}
