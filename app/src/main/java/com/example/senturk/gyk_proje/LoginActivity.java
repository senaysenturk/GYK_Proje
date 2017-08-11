package com.example.senturk.gyk_proje;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {


    private EditText email;
    private EditText sifre;
    private Button giris;
    private TextView kaydol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final DatabaseReference myRef=database.getReference("Users");

        email=(EditText)findViewById(R.id.etMail);
        sifre=(EditText)findViewById(R.id.etSifre);
        giris=(Button)findViewById(R.id.btGiris);
        kaydol=(TextView)findViewById(R.id.tvKayit);

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText().toString())|| TextUtils.isEmpty(sifre.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Bütün alanları doldurun!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String mail=email.getText().toString();
                    String pass=sifre.getText().toString();

                    mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Giriş Başarılı",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(LoginActivity.this,"Giriş Hatası!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
       /* giris.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyEvent.getAction()==KeyEvent.ACTION_DOWN){
                    switch (keyCode){
                        case keyEvent
                    }
                }
                return false;
            }
        });*/

        kaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,KayitActivity.class));
            }
        });

    }

}

