package com.example.afinal;

import android.app.ProgressDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esewa.android.sdk.payment.ESewaConfiguration;
import com.esewa.android.sdk.payment.ESewaPayment;
import com.esewa.android.sdk.payment.ESewaPaymentActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Pattern;

import khalti.checkOut.api.OnCheckOutListener;
import khalti.checkOut.helper.KhaltiCheckOut;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.amazonaws.regions.ServiceAbbreviations.Email;

public class Booking_activity extends AppCompatActivity {

    EditText useremail, username, phonenumber, password, no_of_tickets;
    Button booking, cancel_booking , khalti_button;
    String email, name, phn, tickets,title;
    public Model model;



    private DatabaseReference mdatabaseRef;
    ProgressDialog mProgressDialog;
    private FirebaseAuth firebaseAuth;

    private final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";

    private int REQUEST_CODE_PAYMENT​ = 2011;

    SharedPreferences sharedPreference;
    String code;
    String refID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_activity);
        useremail = findViewById(R.id.booking_email);
        username = findViewById(R.id.booking_username);
        phonenumber = findViewById(R.id.booking_phonenumber);
        password = findViewById(R.id.booking_password);
        no_of_tickets = findViewById(R.id.booking_tickets);
        booking = findViewById(R.id.book_button);
//        cancel_booking = findViewById(R.id.cancelbook_button);
//        khalti_button = findViewById(R.id.khalti_button);

        mProgressDialog = new ProgressDialog(Booking_activity.this);
        mdatabaseRef = FirebaseDatabase.getInstance().getReference("Booking details");

        sharedPreference = getSharedPreferences("payment",MODE_PRIVATE);


        code = randomAlphaNumeric(4);

        no_of_tickets.setText(code);

        booking.setOnClickListener(v -> {

            final ESewaConfiguration eSewaConfiguration = new ESewaConfiguration()
                    .clientId("JB0BBQ4aD0UqIThFJwAKBgAXEUkEGQUBBAwdOgABHD4DChwUAB0R")
                    .secretKey("BhwIWQQADhIYSxILExMcAgFXFhcOBwAKBgAXEQ==")
                    .environment(ESewaConfiguration.ENVIRONMENT_TEST);



            String rate = getIntent().getExtras().getString("rate");
            System.out.println("Rate : "+rate);

            ESewaPayment eSewaPayment = new ESewaPayment(rate,"Event Booking","Event"+code,"https://ir-user.esewa.com.np/epay/main");

            Intent intent = new Intent(getApplicationContext(), ESewaPaymentActivity.class);
            intent.putExtra(ESewaConfiguration.ESEWA_CONFIGURATION,eSewaConfiguration);
            intent.putExtra(ESewaPayment.ESEWA_PAYMENT,eSewaPayment);
            startActivityForResult(intent,REQUEST_CODE_PAYMENT​);
//                getData();
//

        });

        title = getIntent().getExtras().getString("title");
        Toast.makeText(this, "Title : "+title, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PAYMENT​){
            if (resultCode == RESULT_OK) {
                String message = data.getStringExtra(ESewaPayment.EXTRA_RESULT_MESSAGE);

                if(message != null)
                    try {
                        JSONObject jObj = new JSONObject(message);
                        String productId =  jObj.optString("productId");
                        String totalAmount = jObj.optString("totalAmount");
                        refID = jObj.optJSONObject("transactionDetails").optString("referenceId");
                        message = jObj.getJSONObject("message").optString("successMessage");
                        System.out.println("Pid : "+productId);
                        System.out.println("refid : "+refID);
                        System.out.println("message : "+message);
                        System.out.println("totlaAmount : "+totalAmount);
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        getData();



                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Oops ! something went wrong", Toast.LENGTH_SHORT).show();
                    }


            }else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Cancel By User", Toast.LENGTH_SHORT).show();
            }else if (resultCode == ESewaPayment.RESULT_EXTRAS_INVALID){
                String s = data.getStringExtra(ESewaPayment.EXTRA_RESULT_MESSAGE);
                System.out.println("Proof of payment1 : "+s);
            }
        }
    }
//    private Boolean validate(){
//        Boolean res=false;
//        String email = useremail.getText().toString();
//        String name = username.getText().toString();
//        String phone = phonenumber.getText().toString();
//        String pwd = password.getText().toString();
//
//
//        if (email.isEmpty() && name.isEmpty() && phone.isEmpty() && pwd.isEmpty() ) {
//            Toast.makeText(Booking_activity.this, " fields should not be blank ", Toast.LENGTH_SHORT).show();
//        }
//        else {
//           if (email.equals("") && password.equals(""))
//           {
//
//           }
//        }
//        return res;


    //  }

    public void getData() {
        email = useremail.getText().toString().trim();
        name = username.getText().toString().trim();
        phn = phonenumber.getText().toString().trim();
        tickets = no_of_tickets.getText().toString().trim();

        final Pattern phonePattern = Pattern.compile(
                "^"+ "(0|94)" + "\\d{9,10}");



        if (email.isEmpty()) {
            useremail.setError("Email is required");
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            Toast.makeText(this, "Email pattern is wrong", Toast.LENGTH_SHORT).show();}



        if (phn.equals("")) {
            phonenumber.setError("Phone number is required ");
            return;
        }
        else if(!phonePattern.matcher(phn).matches()){

            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
        }


        if (tickets.equals("")) {
            no_of_tickets.setError("Ticket field shouldnot be blank ");
            return;
        }



        uploadFile(email, name, phn, tickets);



    }

    public void uploadFile(final String email, final String name, final String phn, final String tickets ) {


        File file = new File(email, name, phn, tickets);
        mdatabaseRef.push().setValue(file).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    SharedPreferences.Editor editor = sharedPreference.edit();
                    editor.putString("name",name);
                    editor.putString("refId",refID);
                    editor.putString("ticket",code);
                    editor.putString("event",title);
                    editor.putString("email",email);
                    editor.putString("phone",phn);
                    editor.apply();


                    finish();


                }
            }
        });
        Toast.makeText(Booking_activity.this, "booking details uploaded successful", Toast.LENGTH_LONG).show();


    }
    private void validate(String userEmail, String userPassword) {


        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    checkEmailVerification();
                    FirebaseMessaging.getInstance().subscribeToTopic("eventsUser");

                }else{
                    Toast.makeText(Booking_activity.this, "Book Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(Booking_activity.this, MainActivity.class));

    }

    public String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}