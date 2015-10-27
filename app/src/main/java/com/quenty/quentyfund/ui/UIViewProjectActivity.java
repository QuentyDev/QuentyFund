package com.quenty.quentyfund.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quenty.quentyfund.R;
import com.quenty.quentyfund.entity.Proyecto;

import java.lang.reflect.Type;

public class UIViewProjectActivity extends AppCompatActivity {
    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvMonto;
    private TextView tvDiasVigencia;
    private TextView tvCategoria;
private Proyecto proyecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiview_project);
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);
        tvMonto = (TextView) findViewById(R.id.tvMonto);
        tvDiasVigencia = (TextView) findViewById(R.id.tvDiasVigencia);
        tvCategoria = (TextView) findViewById(R.id.tvCategoria);

        Bundle extra=getIntent().getExtras();
        if(extra!=null){
            Gson gson=new Gson();
            Type type = new TypeToken<Proyecto>() {
                    }.getType();
            proyecto=    gson.fromJson(extra.getString("project"), type);
        }
        tvNombre.setText(proyecto.getNombre());
        tvDescripcion.setText(proyecto.getDescripcionLarga());
        tvCategoria.setText(proyecto.getCategoria());
        tvMonto.setText(String.valueOf(proyecto.getMonto()));
        tvDiasVigencia.setText(String.valueOf(proyecto.getDiasVigencia()));
    }
}
