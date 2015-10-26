package com.quenty.quentyfund.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.quenty.quentyfund.R;
import com.quenty.quentyfund.entity.Proyecto;

public class UIDetalleProyectoActivity extends AppCompatActivity {
    private EditText etNombre;
    private EditText etDescripcion;
    private EditText etMonto;
    private EditText etDiasVigencia;
    private Spinner spCategoria;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uidetalle_proyecto);
        etNombre=(EditText) findViewById(R.id.etNombre);
        etDescripcion=(EditText) findViewById(R.id.etDescripcion);
        etMonto=(EditText) findViewById(R.id.etMonto);
        etDiasVigencia=(EditText) findViewById(R.id.etDiasVigencia);
        spCategoria=(Spinner) findViewById(R.id.spCategoria);
        btnGuardar=(Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Bundle extras = getIntent().getExtras();
        Proyecto proyecto=new Proyecto();;
        if(extras!=null) {
                    proyecto.setProyectoID(extras.getInt("id",-1));
//            Cargar e; pryecto

        }

        if(proyecto.getProyectoID()>0){

//            Cargar datos del proyecto

            etNombre.setText(proyecto.getNombre());
            etDescripcion.setText(proyecto.getDescripcionLarga());
            etMonto.setText(proyecto.getMonto());
            etDiasVigencia.setText(proyecto.getDiasVigencia());
//            spCategoria.setSelection(proyecto.getCategoria());

        }
    }
}
