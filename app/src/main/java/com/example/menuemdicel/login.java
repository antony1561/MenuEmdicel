package com.example.menuemdicel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        db = new DatabaseHelper(this);
        e1=(EditText)findViewById(R.id.correo);
        e2=(EditText)findViewById(R.id.contra);
        e3=(EditText)findViewById(R.id.confircontra);
        b1=(Button)findViewById(R.id.registrarse);
        b2=(Button)findViewById(R.id.login);
        ////////////////////////////////////////////////////////////////////////////////
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,Login2.class);
                startActivity(i);
            }
        });
        //////////////////////////////////////////////////////////////////////
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals("")){
                    Toast.makeText(getApplicationContext(),"LOS CAMPOS ESTAN VACIOS",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(s2.equals(s3)){
                        Boolean chkcorreo = db.chkcorreo(s1);
                        if(chkcorreo==true){
                            Boolean insert = db.insert(s1,s2);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"REGISTRO SATISFACTORIO",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(login.this,Login2.class);
                                startActivity(i);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"ESTE CORREO YA EXISTE",Toast.LENGTH_SHORT).show();
                        }
                    }
                    Toast.makeText(getApplicationContext(),"LAS CONTRASEÑAS NO COINCIDE",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
