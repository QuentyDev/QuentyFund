package com.quenty.quentyfund.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.gson.Gson;
import com.quenty.quentyfund.R;
import com.quenty.quentyfund.entity.Proyecto;
import com.quenty.quentyfund.ui.UIDetalleProyectoActivity;
import com.quenty.quentyfund.ui.UIViewProjectActivity;

/**
 * Created by DavorLimachi on 10/21/15.
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private Proyecto[] notes;
    private Context context;

    public ProjectAdapter(Context context, Proyecto[] proyectos) {
//        notes = generateNotes(context, numNotes);
        this.notes = proyectos;
        this.context = context;
    }

    public ProjectAdapter(Context context, int numNotes) {
//        notes = generateNotes(context, numNotes);
        this.context = context;
    }

    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_proyecto, parent,
                false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Proyecto proyectoModel = notes[position];
        final String nombre = proyectoModel.getNombre();
        final String descripcion = proyectoModel.getDescripcionCorta();
        final String info = String.valueOf(proyectoModel.getMonto());
        String infoImage = proyectoModel.getCategoria();
        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
// generate random color
        int color = generator.getRandomColor();
//        int color = Color.parseColor("#883F51B5");

        // Set text
        holder.titleTextView.setText(nombre);
        holder.noteTextView.setText(descripcion);
        holder.infoTextView.setText(info);
        holder.categoriaTextView.setText(proyectoModel.getCategoria());

        // Set image
        holder.infoImageView.setImageResource(R.drawable.ic_quenty);

        // Set visibilities
        holder.titleTextView.setVisibility(TextUtils.isEmpty(nombre) ? View.GONE : View.VISIBLE);
        holder.noteTextView.setVisibility(TextUtils.isEmpty(descripcion) ? View.GONE : View.VISIBLE);
//        holder.infoLayout.setVisibility(TextUtils.isEmpty(info) ? View.GONE : View.VISIBLE);

        // Set padding
        int paddingTop = (holder.titleTextView.getVisibility() != View.VISIBLE) ? 0
                : holder.itemView.getContext().getResources()
                .getDimensionPixelSize(R.dimen.note_content_spacing);
        holder.noteTextView.setPadding(holder.noteTextView.getPaddingLeft(), paddingTop,
                holder.noteTextView.getPaddingRight(), holder.noteTextView.getPaddingBottom());

        // Set background color
        ((CardView) holder.itemView).setCardBackgroundColor(color);
        ((CardView) holder.itemView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Click" + nombre);
                Intent intent = new Intent(context, UIViewProjectActivity.class);
                Gson gson=new Gson();
                intent.putExtra("project",gson.toJson(proyectoModel));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.length;
    }

    private Proyecto[] generateNotes(Context context, int numNotes) {
        Proyecto[] notes = new Proyecto[numNotes];
        for (int i = 0; i < notes.length; i++) {
            String descr = "";
            for (int j = 0; j < i; j++) {
                descr += "Descripcion ";
            }
            notes[i] = new Proyecto(i, "Nombre " + i, descr, (i + 1) * 1000, (i + 1) * 10, "");
        }
        return notes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView noteTextView;
        public TextView categoriaTextView;
        //        public LinearLayout infoLayout;
        public TextView infoTextView;
        public ImageView infoImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.note_title);
            noteTextView = (TextView) itemView.findViewById(R.id.note_text);
            categoriaTextView = (TextView) itemView.findViewById(R.id.tvCategoria);
//            infoLayout = (LinearLayout) itemView.findViewById(R.id.note_info_layout);
            infoTextView = (TextView) itemView.findViewById(R.id.note_info);
            infoImageView = (ImageView) itemView.findViewById(R.id.note_info_image);
        }
    }

}