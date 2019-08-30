package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static com.example.afinal.SharedPreferenceHelper.getInstance;

public class FirebaseHelper {
    private static final String TAG = "FirebaseHelper";
    private static FirebaseHelper instance;

    private final SharedPreferenceHelper sharedPreference;
    private final StorageReference mStorageReference;
    private String user_id;
    private Context mContext;
    private FirebaseAuth mAuth;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private int mPhotoUploadProgress = 0;

    public static FirebaseHelper getFirebaseInstance(Context context) {
        if (instance == null) {
            instance = new FirebaseHelper(context);
        }

        return instance;
    }

    public FirebaseHelper(Context mContext) {
        this.mContext = mContext;
        mAuth = FirebaseAuth.getInstance();
        //inititalize storage

        mStorageReference = FirebaseStorage.getInstance().getReference();
//        initialize the firebase database
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        sharedPreference = getInstance(mContext);

        if (mAuth.getCurrentUser() != null) {
            this.user_id = mAuth.getCurrentUser().getUid();
            Log.e(TAG, "FirebaseHelper: " + this.user_id);
        }

        Log.e(TAG, "FirebaseHelper: " + myRef.toString());
    }

    public StorageReference getmStorageReference() {
        return mStorageReference;
    }


    public DatabaseReference getMyRef() {
        return myRef;
    }

    public void setUserID(String userID) {
        this.user_id = userID;
    }


    public void signOut() {
        mAuth.signOut();

//        sharedPreference.saveUserInfo(new User("", "", "", UserTypes.NORMAL, "",""));
        Intent intent = new Intent(mContext, SignIn.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        mContext.startActivity(intent);
    }


    public FirebaseAuth getAuth() {
        return mAuth;
    }

    /**
     * sets the selected user by admin to staff
     *
     * @param selected_user_id - user_id of the user selected by the admin in profile activity
     */
    public void setUserAsNormal(String selected_user_id) {
        myRef.child("User");
    }


    public SharedPreferenceHelper getSharedPreference() {
        return sharedPreference;
    }




    public String getUser_id() {
        return user_id;
    }
}

