package com.example.afinal;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_changepassword extends AppCompatActivity {

    EditText oldpassword, newpassword, confirmpassword;
    Button change_password;
    FirebaseAuth firebaseAuth;
    String oldpass;
    String newpass;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);


        oldpassword = (EditText) findViewById(R.id.oldpwd);
        newpassword = findViewById(R.id.newpwd);
        confirmpassword = findViewById(R.id.confirmpwd);
        change_password = findViewById(R.id.change_button);

        change_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ioldpass = getIntent();
                oldpass = ioldpass.getStringExtra("oldpassw");
                if (oldpass == (oldpassword.getText().toString())) {
                    newpass = newpassword.getText().toString();
                    Intent intentnew = new Intent(activity_changepassword.this, SignIn.class);
                    intentnew.putExtra("passnew", newpass);
                    startActivity(intentnew);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });


    }


}
