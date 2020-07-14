package com.example.menuemdicel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login2 extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);
        db = new DatabaseHelper(this);
        e1 = (EditText)findViewById(R.id.correo2);
        e2 = (EditText)findViewById(R.id.contra2);
        b1 = (Button)findViewById(R.id.ingresar);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = e1.getText().toString();
                String contraseña = e2.getText().toString();
                Boolean chkcorreocontraseña = db.correocontraseña(correo,contraseña);
                if ( chkcorreocontraseña==true)
                    Toast.makeText(getApplicationContext(),"INGRESO EXITOSO",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"CORREO O CONTRASEÑA EQUIVOCADA",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
