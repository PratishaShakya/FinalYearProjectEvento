package com.example.afinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ViewFlipper viewFlipper;
    private DrawerLayout drawer;
    GridView gridView;
    MainAdapter adapter;
    ImageView imageView;
    DatabaseReference userRef;
    SliderLayout sliderLayout;


    String currentUserId;
//    String categoryType;
//    String[] category = {"Educational","Art","Food","Sport","Musical","Sale"};


    List<String> imageList;

    private final int sports = R.drawable.sports;

    String[] grid = {"Educational","Art","Food","Sport","Musical","Sale"};
   int[] gridimages = {R.drawable.edu, R.drawable.art, R.drawable.food,R.drawable.sports,R.drawable.music,R.drawable.sales};


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderLayout = findViewById(R.id.imageslider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(3);
        setSliderViews();


        gridView = findViewById(R.id.grid_view);

        MainAdapter adapter = new MainAdapter(MainActivity.this, grid, gridimages);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "You clicked "+grid[position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                intent.putExtra("categoryType", grid[position]);
                startActivity(intent);

            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("eventsUser");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getUserDetails(navigationView);







    }





        private void getUserDetails(final NavigationView navigationView){
            currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            userRef = FirebaseDatabase.getInstance().getReference("User");
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            if (ds.getKey().equals(currentUserId)) {
                                User user = ds.getValue(User.class);
                                TextView name = navigationView.findViewById(R.id.nav_name);
                                TextView email = navigationView.findViewById(R.id.nav_email);
                                name.setText(user.userName);
                                email.setText(user.userEmail);
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_profile:
                // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;

            case R.id.nav_addevent:
                startActivity(new Intent(MainActivity.this,AddEventActivity.class));
                break;

                case R.id.logout:
                startActivity(new Intent(getApplicationContext(),SignIn.class));
                FirebaseAuth.getInstance().signOut();


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setSliderViews() {

    String[] slider = {"\"https://firebasestorage.googleapis.com/v0/b/events-b9343.appspot.com/o/uploads%2F1565494944618.jpg?alt=media&token=4282f198-7222-4a02-ba79-e9e2328a3d4a\" );\n" +
            "                "};

        for ( int i = 0; i <= 2; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/events-b9343.appspot.com/o/uploads%2F1565494944618.jpg?alt=media&token=4282f198-7222-4a02-ba79-e9e2328a3d4a" );

                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/events-b9343.appspot.com/o/uploads%2F1565495955037.jpg?alt=media&token=ba233151-39c0-4792-a4b8-f083e6555d56");
                    sliderView.setOnSliderClickListener(sliderView1 -> {
                        System.out.println("I am here!!!");
                        Toast.makeText(getApplicationContext(), "clicked image", Toast.LENGTH_SHORT).show();
                    });
                    break;
                case 2:
                    sliderView.setOnSliderClickListener(sliderView1 -> {
                        System.out.println("I am here!!!");
                        Toast.makeText(getApplicationContext(), "clicked image", Toast.LENGTH_SHORT).show();
                    });
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/events-b9343.appspot.com/o/uploads%2F1565495176912.jpg?alt=media&token=25693cb3-f065-4d9f-a550-baa6e69743c8");
                    break;

            }

            sliderView.setOnSliderClickListener(sliderView1 -> {
                System.out.println("I am here!!!");
                Toast.makeText(getApplicationContext(), "clicked image", Toast.LENGTH_SHORT).show();
            });


            sliderLayout.addSliderView(sliderView);




        }




    }

}
