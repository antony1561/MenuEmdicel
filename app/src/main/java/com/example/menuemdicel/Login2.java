package com.example.menuemdicel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login2 extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.correo2);
        e2 = (EditText)findViewById(R.id.contra2);
        b1 = (Button)findViewById(R.id.ingresar);
        b2 = (Button)findViewById(R.id.reg);
        /////////////////////////////////////////////////////////
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login2.this,login.class);
                startActivity(i);
            }
        });
        //////////////////////////////////////////////////////////////
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = e1.getText().toString();
                String contraseña = e2.getText().toString();
                Boolean chkcorreocontraseña = db.correocontraseña(correo, contraseña);
                if(correo.equals("")||contraseña.equals("")){
                    Toast.makeText(getApplicationContext(),"CAMPOS VACIOS", Toast.LENGTH_SHORT).show();
                }else if(chkcorreocontraseña == true){
                        Toast.makeText(getApplicationContext(), "INGRESO EXITOSO", Toast.LENGTH_SHORT).show();
                    Intent i2 = new Intent(Login2.this, MainActivity.class);
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "USUARIO O CONTRASEÑA INCORRECTOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
