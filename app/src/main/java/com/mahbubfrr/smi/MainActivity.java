package com.mahbubfrr.smi;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public String Y, M, D;
    String RES;
    Date RDV;
    Button button;
    Date RDVmin;
    Date RDVmax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText yearField = (EditText) findViewById(R.id.year);
        final EditText monthField = (EditText) findViewById(R.id.month);
        final EditText dayField = (EditText) findViewById(R.id.day);
        final TextView res = (TextView) findViewById(R.id.result2);
        final TextView rdv = (TextView) findViewById(R.id.result1);
        button= (Button) findViewById(R.id.submit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Y = yearField.getText().toString();
                M = monthField.getText().toString();
                Log.v("M: ", String.valueOf(Integer.parseInt(M)));
                D = dayField.getText().toString();
                Calendar c = new GregorianCalendar();
                c.set(Calendar.YEAR, Integer.parseInt(Y));
                c.set(Calendar.MONTH, Integer.parseInt(M) - 1);
                c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(D));
                Date xmas = c.getTime();
                Date today = new Date();
                long diff = today.getTime() - xmas.getTime();

                diff = diff / (1000L * 60L * 60L * 24L);
                int total_days = (int) (long) diff;
                float total_month = (float) total_days / 30;
                String note;
                SimpleDateFormat dformat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
                if (diff > 0) {
                    if (diff < 0) {
                        note = "Invalid Date";

                    } else {
                        note = "You are " + total_days + " days old, (" + total_month + " months) and " + dformat.format(today) + " and " + dformat.format(xmas);
                    }
                    Log.v("Age: ", note);

                    if (total_month > 0 && total_month <= 1) {
                        RES = "HB1n 24  si il n'a pas administre a la masion d'accouchement , BCG , VPO 0 ";
                        Log.v("RES m>0 and <=1: ", RES);
                        int minxday = (int) (2 - total_month) * 30;
                        int maxxday = (int) (3 - total_month) * 30;
                        Date RDVmin = new Date(today.getTime() + (long) minxday * 24 * 60 * 60 * 1000);
                        Date RDVmax = new Date(today.getTime() + (long) maxxday * 24 * 60 * 60 * 1000);
                        rdv.setText("RDVmax: "+dformat.format(RDVmax) + "\n" +"RDVmin: "+ dformat.format(RDVmin));


                    } else if (total_month >= 2 && total_month < 3) {
                        RES = "VPO 1 , Pneumo 1 , Rota 1 , DTC-hib-hb 1";
                        Log.v("RES m=>2 and <3: ", RES);
                        Date RDV = new Date(today.getTime() + (long) 30 * 24 * 60 * 60 * 1000);
                        rdv.setText("RDV: "+dformat.format(RDV));

                    } else if (total_month >= 3 && total_month < 4) {
                        RES = "VPO 2 , Rota 2 , DTC-hib-hb 2";
                        Log.v("RES m=>3 and <4: ", RES);
                        Date RDV = new Date(today.getTime() + (long) 30 * 24 * 60 * 60 * 1000);
                        rdv.setText("RDV: "+ dformat.format(RDV));

                    } else if (total_month >= 4 && total_month < 5) {
                        RES = "VPO 3 , Pneumo 2 , Rota 3 , DTC-hib-hb , VPI ";
                        Log.v("RES m>=4 and <5: ", RES);

                    } else if (total_month >= 5 && total_month < 6) {
                        RES = "VPO 3 , Pneumo 2 , Rota 3 , DTC-hib-hb , VPI  ";
                        Log.v("RES: ", RES);

                    } else if (total_month >= 6 && total_month < 7) {
                        RES = "vitamin A et D";
                        Date RDV = new Date(xmas.getTime() + (long) 274 * 24 * 60 * 60 * 1000);
                        rdv.setText("RDV: "+dformat.format(RDV));

                    } else if (total_month >= 9 && total_month < 10) {
                        RES = "RR";
                        Log.v("RES: ", RES);
                        Date RDV = new Date(xmas.getTime() + (long) 365 * 24 * 60 * 60 * 1000);
                        rdv.setText(RDV.toString());

                    } else if (total_month >= 12 && total_month < 13) {
                        RES = "Pneumo 3";
                        Log.v("RES: ", RES);
                        Date RDV = new Date(xmas.getTime() + (long) 548 * 24 * 60 * 60 * 1000);
                        rdv.setText("RDV: "+dformat.format(RDV));

                    } else if (total_month >= 18 && total_month < 19) {
                        RES = "Polio 4 , DTC 1 er RAPPel , VAR ";
                        Log.v("RES: ", RES);

                    } else if (total_month >= 60) {
                        RES = "POLIO 5 , DTC 2 RAPPEL";
                        Log.v("RES: ", RES);

                    } else {
                        RES = "il va faire rien aujourd'hui";
                        Log.v("RES: ", RES);
                    }
                    if (total_month >= 4 && total_month < 6) {

                        int minxday = (int) (6 - total_month) * 30;
                        int maxxday = (int) (7 - total_month) * 30;
                        Date RDVmin = new Date(today.getTime() + (long) minxday * 24 * 60 * 60 * 1000);
                        Date RDVmax = new Date(today.getTime() + (long) maxxday * 24 * 60 * 60 * 1000);
                        rdv.setText("RDVmax: "+dformat.format(RDVmax) + "\n" +"RDVmin: "+ dformat.format(RDVmin));
                    } else if (total_month >= 18) {
                        Date RDV = new Date(xmas.getTime() + (long) 1825 * 24 * 60 * 60 * 1000);
                        rdv.setText("RDV: "+ dformat.format(RDV));
                    }

                    if (RES != null) {
                        res.setText(RES);
                    }

                }else {
                    res.setText("Please Provide Correct Date");
                    rdv.setText("You have provided Future Date");
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
