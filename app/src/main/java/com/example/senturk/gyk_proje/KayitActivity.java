package com.example.senturk.gyk_proje;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class KayitActivity extends AppCompatActivity {

    private EditText email;
    private EditText sifre;
    private Button kayit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final DatabaseReference myRef = db.getReference("Users");

        email = (EditText) findViewById(R.id.etK_Mail);
        sifre = (EditText) findViewById(R.id.etK_Sifre);
        kayit = (Button) findViewById(R.id.btK_Kaydet);

        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddr = email.getText().toString();
                String pass = sifre.getText().toString();
                mAuth.createUserWithEmailAndPassword(emailAddr, pass)
                        .addOnSuccessListener(KayitActivity.this, new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Map<String, String> yeniUser = new HashMap<String, String>();
                                yeniUser.put("mail", "Mailiniz");
                                yeniUser.put("sifre", "Şifeniz");

                                DatabaseReference databaseReference = db.getReference();
                                databaseReference.child("users")
                                        .child(mAuth.getCurrentUser().getUid())
                                        .setValue(yeniUser);
                                startActivity(new Intent(KayitActivity.this, LoginActivity.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(KayitActivity.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
