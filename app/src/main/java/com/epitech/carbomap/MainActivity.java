package com.epitech.carbomap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
/* MainActivity is the first activity started by the mobile app. */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*launchSimulation is used to start the simulation activity
    * This function is called when simul_launch button is pressed.*/
    public void launchSimulation(View v)
    {
        Intent i = new Intent(this, SimulationActivity.class);
        startActivity(i);
    }
}
