package com.example.hasalp.smarthome;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import connection.Connection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navBar;
    LightFragment lightFragment;
    TemperatureFragment temperatureFragment;
    public static final String IP = "tcp://10.42.0.1:1883";
    ArrayList<Fragment> fragments;
    FragmentTransaction fragmentTransaction;
    private boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        navBar = findViewById(R.id.nav_view);

        setupConnection();

        lightFragment = new LightFragment();
        temperatureFragment = new TemperatureFragment();

        fragments = new ArrayList<>();
        fragments.add(lightFragment);
        fragments.add(temperatureFragment);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragments.get(0));
        fragmentTransaction.commit();
    }

    public void setupConnection(){

        (new MqttTask(IP,"Hasan")).execute();

        View header = navBar.getHeaderView(0);
        TextView id = header.findViewById(R.id.idText);
        final TextView status = header.findViewById(R.id.statusText);

        if(Connection.isConnected()){
            connected = true;
            id.setText("Hasan");
            status.setText("Connected");
        }else{
            Log.d("Error","Connection Error");
            connected = false;
            id.setText("Hasan");
            status.setText("Disconnected");
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (connected) {
            if (id == R.id.nav_light) {
                fragmentTransaction.replace(R.id.frameLayout, fragments.get(0));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else if (id == R.id.nav_temperature) {
                fragmentTransaction.replace(R.id.frameLayout, fragments.get(1));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            } else if (id == R.id.nav_share) {

            } else if (id == R.id.nav_send) {

            }
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.closedConnection), Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
