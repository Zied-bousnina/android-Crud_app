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

public class LoginActivity extends AppCompatActivity {

    EditText tel, password;
    Button login ;
    TextView register;
    Intent i;

    String tel_text, password_txt;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://project-final-884ee-default-rtdb.firebaseio.com/");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tel = findViewById(R.id.tel);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tel_text = tel.getText().toString();
                password_txt = password.getText().toString();

                if(tel_text.isEmpty() || password_txt.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter your mobile or password", Toast.LENGTH_SHORT).show();

                }else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            // check user exist
                            if(snapshot.hasChild(tel_text)) {
                                // tel is exist in firebase
                                String getPassword = snapshot.child(tel_text).child("password").getValue(String.class);

                                if(getPassword.equals(password_txt)) {
                                    Toast.makeText(LoginActivity.this, "Successfully Logged !", Toast.LENGTH_SHORT ).show();
                                    i = new Intent(getApplicationContext(), MainActivity.class);
                                    i.putExtra("tel", tel_text);
                                    startActivity(i);
                                    finish();


                                }else {
                                    Toast.makeText(LoginActivity.this, "Wrong Password !", Toast.LENGTH_SHORT ).show();


                                }

                            }else {
                                Toast.makeText(LoginActivity.this, "Wrong Tel ! !", Toast.LENGTH_SHORT ).show();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);

            }
        });





    }
}