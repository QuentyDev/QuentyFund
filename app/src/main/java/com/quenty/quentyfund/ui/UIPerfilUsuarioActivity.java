package com.quenty.quentyfund.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.quenty.quentyfund.R;
import com.quenty.quentyfund.bll.BLLUser;
import com.quenty.quentyfund.entity.User;

public class UIPerfilUsuarioActivity extends AppCompatActivity {
    private EditText etNombre;
    private EditText etApellido;
    private EditText etDireccion;
    private EditText etTelefono;
    private Spinner spCiudad;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiperfil_usuario);
        etNombre=(EditText)findViewById(R.id.etNombre);
        etApellido=(EditText)findViewById(R.id.etApellido);
        etDireccion=(EditText)findViewById(R.id.etDireccion);
        etTelefono=(EditText)findViewById(R.id.etTelefono);
        spCiudad=(Spinner)findViewById(R.id.spCuidad);
        btnGuardar=(Button)findViewById(R.id.btnGuardarCambio);
        BLLUser bllUser=new BLLUser(getBaseContext());
        User user=bllUser.get();
        etNombre.setText(user.getFirstName());
        etApellido.setText(user.getLastName());
        etDireccion.setText(user.getDireccion());
        etTelefono.setText(user.getTelefono());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
