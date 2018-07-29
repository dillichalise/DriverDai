package com.example.dillichalise.driverdai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BusDetailActivity extends AppCompatActivity {

    TextView txt;
    Button detailsbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_detail);

        Bundle extras = getIntent().getExtras();

        if (extras.containsKey("username")) {
            String username = extras.getString("username");
            txt = (TextView) findViewById(R.id.text);
            detailsbutton = (Button) findViewById(R.id.viewdetails);

            txt.setText("Welcome " + username);

        }

    }
    public void onViewDetailsClick(View V){
        finish();
        Intent intent = new Intent(BusDetailActivity.this, BlankActivity.class);
        startActivity(intent);
    }

}
