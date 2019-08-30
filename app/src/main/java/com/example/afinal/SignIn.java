package com.example.afinal;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

public class SignIn extends AppCompatActivity {
    private EditText email, password;
    private Button login;
    private TextView create_Account,sendmypassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login_button);
        create_Account = (TextView) findViewById(R.id.signuptxt);
        sendmypassword= (TextView) findViewById(R.id.forgot);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(SignIn.this, MainActivity.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(email.getText().toString().trim(), password.getText().toString().trim());



                //String emailText = email.getText().toString();
                //String passwordText = password.getText().toString();
//                   if (emailText.equals(("Admin")) && passwordText.equals("1234567")) {
//                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(SignInActivity.this, "Username Or Password Incorrect", Toast.LENGTH_SHORT).show();
//                    }
            }
        });



        create_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });
        sendmypassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
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
                    Toast.makeText(SignIn.this, "Login Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        startActivity(new Intent(SignIn.this, MainActivity.class));

    }



    }
