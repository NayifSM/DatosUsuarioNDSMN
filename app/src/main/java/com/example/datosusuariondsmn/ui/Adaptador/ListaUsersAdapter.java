package com.example.datosusuariondsmn.ui.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datosusuariondsmn.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import entidades.Usuario;

public class ListaUsersAdapter extends RecyclerView.Adapter<ListaUsersAdapter.UsersViewHolder> {

    List<Usuario> listaUsuario;

    public ListaUsersAdapter    (List<Usuario>listaUsuario){
        this.listaUsuario= listaUsuario;
    }
    @Override
    public UsersViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        //recycler view va a modelar susu filas de acuerdo al diseño de list item ususarios
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_usuarios,parent,false);
        RecyclerView.LayoutParams layoutParams= new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new UsersViewHolder(view);

    }

     @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
//asignan los datos

         holder.documento.setText(String.valueOf(listaUsuario.get(position).getId()));
         holder.nombre.setText(String.valueOf(listaUsuario.get(position).getNombre()));
         holder.telefono.setText(String.valueOf(listaUsuario.get(position).getTelefono()));
         String ruta =listaUsuario.get(position).getUrlimg().toString();
         Picasso.get()
                //.load(listaUsuario.get(position).getUrlimg().toString())
                .load(ruta)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.urlimg);
    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView documento,nombre,telefono;
        ImageView urlimg;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            documento= itemView.findViewById(R.id.txRDocumento);
            nombre = itemView.findViewById(R.id.txRNombre);
            telefono = itemView.findViewById(R.id.txRTelefono);
            urlimg = itemView.findViewById(R.id.RImageMas);
        }
    }
}
