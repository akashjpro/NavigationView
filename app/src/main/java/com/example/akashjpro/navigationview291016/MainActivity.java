package com.example.akashjpro.navigationview291016;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    android.app.FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;

        fragmentTransaction.replace(R.id.linearLayout, new fragment_android());
        fragmentTransaction.commit();

        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.BLUE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);// mui ten
        toolbar.setNavigationIcon(R.drawable.menu);//set icon
        navigationView.setItemIconTintList(null);// cho icon ve mau goc

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               drawerLayout.openDrawer(GravityCompat.START);
            }
        });



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.menuAndroid :
                        fragment = new fragment_android();
                        break;

                    case R.id.menuIOS :
                        fragment = new FragmentIOS();
                        break;

                    case R.id.menuPHP :
                        fragment = new FragmentPHP();
                        break;


                }

                fragmentTransaction.replace(R.id.linearLayout, fragment);
                fragmentTransaction.commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void addControls() {
        toolbar         = (Toolbar) findViewById(R.id.toolBar);
        navigationView  = (NavigationView) findViewById(R.id.myNavigationView);
        drawerLayout    = (DrawerLayout) findViewById(R.id.myDrawerLayout);
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount()>0){
            fragmentManager.popBackStack();
        }else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }
}
