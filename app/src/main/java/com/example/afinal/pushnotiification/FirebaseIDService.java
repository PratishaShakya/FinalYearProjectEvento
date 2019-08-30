package com.example.afinal.pushnotiification;

import android.util.Log;

import com.google.firebase.database.core.Context;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseIDService extends FirebaseInstanceIdService {
    private static final String REG_TOKEN = "REG_TOKEN";
    //    DBHandler db;
    Context context;
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.


        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN,refreshedToken);
        System.out.println("RegToken : "+refreshedToken);



    }
}



