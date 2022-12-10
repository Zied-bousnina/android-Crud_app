package com.example.androidprojet;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    EditText username, Tel, password, confirm_password;
    TextView login;
    Button signUp;
    String name_txt, tel_txt, password_txt, confirmPass_txt;
    Intent i;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project-final-884ee-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        Tel = findViewById(R.id.Tel);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.register);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);

            }

        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from EditText
                name_txt = username.getText().toString();
                tel_txt = Tel.getText().toString();
                password_txt = password.getText().toString();
                confirmPass_txt = confirm_password.getText().toString();

                // check if user fill all the fields before sending data to firebase
                if(name_txt.isEmpty() || tel_txt.isEmpty() || password_txt.isEmpty() ) {
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();

                    // check if passwords are matching with each other
                }else if(!(password_txt.equals(confirmPass_txt))) {
                    Toast.makeText(RegisterActivity.this, "Passswords are not matching", Toast.LENGTH_SHORT).show();
                }else {


                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            // check email is not registred before
                            if (snapshot.hasChild(tel_txt)) {
                                Toast.makeText(RegisterActivity.this, "Tel already Registred", Toast.LENGTH_SHORT).show();
                            } else{
                                // sending data to firebbase realtime database

                                databaseReference.child("users").child(tel_txt).child("username").setValue(name_txt);
                                databaseReference.child("users").child(tel_txt).child("password").setValue(password_txt);
                                Toast.makeText(RegisterActivity.this, "user Registred successfully", Toast.LENGTH_SHORT).show();

                                finish();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });





                }



            }
        });


    }
}