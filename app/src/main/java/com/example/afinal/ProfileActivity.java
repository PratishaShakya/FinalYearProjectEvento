package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    public Button logout;
  public TextView myProfile , myEmail , username ,phone,txtRefNo,txtTicket;
  public TextView txtEventName;


    DatabaseReference userRef, eventRef;
    String currentUserId;

    Button signOutButton;
    ImageView imgButton;
    TextView userName, userEmail, ratedMovies, avgRating;
    Uri profilePhoto;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private static int RESULT_LOAD_IMG = 1;


    private Context mContext = ProfileActivity.this;


    private FirebaseHelper mFirebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        txtEventName = findViewById(R.id.txtEventName);
        txtTicket = findViewById(R.id.txtTicketNo);
        txtRefNo = findViewById(R.id.txtRefNo);

//        firebaseAuth = firebaseAuth.getInstance();
////        myProfile = (TextView) findViewById(R.id.my_profile);
        myEmail = (TextView) findViewById(R.id.userEmail);
        username = (TextView) findViewById(R.id.userName);
        phone = findViewById(R.id.phoneno);
//
        SharedPreferences sharedPreference = getSharedPreferences("payment",MODE_PRIVATE);
//        if (!sharedPreference.getString("refId","").equals("")){

//        Toast.makeText(this, ""+sharedPreference.getString("refId",""), Toast.LENGTH_SHORT).show();
        try{

            txtRefNo.setText("RefId : "+sharedPreference.getString("refId",""));
//            txtEventName.setText("Hello");
            txtTicket.setText("Ticket No : "+sharedPreference.getString("ticket",""));
            txtEventName.setText("Event : "+sharedPreference.getString("event",""));
//            phone.setText("9860306332");

            phone.setText(sharedPreference.getString("phone",""));
            username.setText(sharedPreference.getString("name",""));
            myEmail.setText(sharedPreference.getString("email",""));
        }catch (Exception e){
            e.printStackTrace();
        }

//        }
////
//
//        firebaseAuth = firebaseAuth.getInstance();
//        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//            userRef = FirebaseDatabase.getInstance().getReference("User");
//            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if (dataSnapshot.hasChildren()) {
//                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                            if (ds.getKey().equals(currentUserId)) {
//                                User user = ds.getValue(User.class);
//                                TextView name = findViewById(R.id.userName);
//                                TextView email = findViewById(R.id.userEmail);
//                                TextView phone=findViewById(R.id.phoneno);
//                                name.setText(user.userName);
//                                email.setText(user.userEmail);
//                                phone.setText(user.phoneNumber);
//                            }
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//
//            });
//        getSupportActionBar().setTitle("Evento Profile");
//
//
//        setContentView(R.layout.activity_profile);
//        mFirebaseHelper = new FirebaseHelper(mContext);
//        initImageLoader();
//        loadImagefromGallery();






    }
    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }
    private void loadImagefromGallery() {
        imgButton = (ImageView) findViewById(R.id.profile_photo);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMG);
            }
        });

    }

}
