package com.quenty.quentyfund.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.quenty.quentyfund.R;
import com.quenty.quentyfund.entity.Proyecto;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;

public class UIViewProjectActivity extends AppCompatActivity {
    private TextView tvNombre;
    private TextView tvDescripcion;
    private TextView tvMonto;
    private TextView tvDiasVigencia;
    private TextView tvCategoria;
    private ImageButton ibContribuir;
    private ImageView ibImage;
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
        ibContribuir = (ImageButton) findViewById(R.id.follow);
        ibImage = (ImageView) findViewById(R.id.imageView3);


        ibContribuir.setTag("0");
        ibContribuir.setBackground(getResources().getDrawable(R.drawable.icon_heart_off));
        ibContribuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info="";
                if (ibContribuir.getTag().equals("0")) {
                    ibContribuir.setBackground(getResources().getDrawable(R.drawable.icon_heart));
                    ibContribuir.setTag("1");
                    info="Agregado a favoritos";

                } else {
                    ibContribuir.setBackground(getResources().getDrawable(R.drawable.icon_heart_off));
                    ibContribuir.setTag("0");
                    info="Eliminado de Favoritos";
                }
                Toast.makeText(UIViewProjectActivity.this,info,Toast.LENGTH_SHORT).show();
            }
        });
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<Proyecto>() {
            }.getType();
            proyecto = gson.fromJson(extra.getString("project"), type);
        }
        tvNombre.setText(proyecto.getNombre());
        tvDescripcion.setText(proyecto.getDescripcionLarga());
        tvCategoria.setText(proyecto.getCategoria());
        tvMonto.setText(String.valueOf(proyecto.getMonto()));
        tvDiasVigencia.setText(String.valueOf(proyecto.getDiasVigencia()));
        String aux = "http://192.168.1.123:8086/hwslim/projects_files/images/" + proyecto.getUrlImage();
        Picasso.with(UIViewProjectActivity.this).load(aux).into(ibImage);
    }
}
