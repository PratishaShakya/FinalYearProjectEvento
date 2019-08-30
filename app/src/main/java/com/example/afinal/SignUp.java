package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

import static com.amazonaws.regions.ServiceAbbreviations.Email;

public class SignUp extends AppCompatActivity {
    public EditText useremail, password, username, confirmpwd;
    private EditText phonenumber;
    private Button createAccount;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setupUIViews();
        firebaseAuth = FirebaseAuth.getInstance();




        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    final String userEmail = useremail.getText().toString().trim();
                    String user_password = password.getText().toString().trim();
                    final String phoneNumber = phonenumber.getText().toString().trim();
                    final String userName = username.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(userEmail, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                User user = new User(userEmail, userName, phoneNumber);
                                FirebaseDatabase.getInstance().getReference("User")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUp.this, "Registered Sucessfully", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(SignUp.this, "Registered Unsucessful", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                            } else {
                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(SignUp.this, "User with this email already exist.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }


            }


        });

    }

    private void setupUIViews() {
        useremail = (EditText) findViewById(R.id.signup_email);
        username = (EditText) findViewById(R.id.signup_username);
        phonenumber = (EditText) findViewById(R.id.signup_phonenumber);
        password = (EditText) findViewById(R.id.signup_password);
        confirmpwd = (EditText) findViewById(R.id.signup_cpassword);
        createAccount = findViewById(R.id.signup_button);
    }

    public Boolean validate() {
        Boolean res=false;
        String email = useremail.getText().toString();
        String name = username.getText().toString();
        String phone = phonenumber.getText().toString();
        String pwd = password.getText().toString();
        String cpwd = confirmpwd.getText().toString();

        final  Pattern phonePattern = Pattern.compile(
                "^"+ "(0|94)" + "\\d{9,10}");

//        final Pattern PasswordPattern = Pattern.compile("^" +
//                "(?=.*[0-9])" +
//                "(?=.*[a-z])" +
//                "(?=.*[A-Z])" +
//                "(?=.*[!@#$%^&*])" +
//                "(?=\\S+$)" +
//                ".{6,}" +
//                "$");

        if (email.isEmpty()) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();

        }  else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                Toast.makeText(this, "Email pattern is wrong", Toast.LENGTH_SHORT).show();
        } else if (name.isEmpty()) {
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();


        } else if (name.contains(" ")) {
            Toast.makeText(this, "You cannot use space", Toast.LENGTH_SHORT).show();


        }
        if(phone.isEmpty())
        {
            Toast.makeText(this, "Phone number is required", Toast.LENGTH_SHORT).show();
        }else if(!phonePattern.matcher(phone).matches()){

            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show();
        }

        if (pwd.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();


        } else if (cpwd.isEmpty()) {
            Toast.makeText(this, "confirm Your Password", Toast.LENGTH_SHORT).show();



        } else if (!pwd.equals(cpwd)) {
            Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show();


        }else {
            res=true;
        }

//
//        if (email.isEmpty() || name.isEmpty() || phone.isEmpty() && pwd.isEmpty() || cpwd.isEmpty()) {
//            Toast.makeText(SignUp.this, " fields should not be blank ", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            res = true;
//        }
//        return res;

     return  res;
    }



}
