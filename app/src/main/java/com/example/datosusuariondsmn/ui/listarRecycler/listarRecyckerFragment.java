package com.example.datosusuariondsmn.ui.listarRecycler;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datosusuariondsmn.R;
import com.example.datosusuariondsmn.ui.Adaptador.ListaUsersAdapter;

import java.util.ArrayList;

import Utils.Utils;
import entidades.ConexionSqlHelper;
import entidades.Usuario;

public class listarRecyckerFragment extends Fragment {
    ListView lstusers;
    ConexionSqlHelper conn;
    RecyclerView recyclerViewUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listUsuario;
    private listarRecyclerViewModel listarRecyclerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista =inflater.inflate(R.layout.fragment_listarrecycler, container, false);
        super.onCreate(savedInstanceState);

        conn= new ConexionSqlHelper(getContext(),"db_usuarios",null,1);

        listUsuario =new ArrayList<>();
        recyclerViewUsuarios = (RecyclerView) vista.findViewById(R.id.recyclerUsuarios);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(getContext()));
        consultarUsuarios();
        ListaUsersAdapter adapter = new ListaUsersAdapter(listUsuario);
        recyclerViewUsuarios.setAdapter(adapter);
        return vista;
    }

    private void consultarUsuarios() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Usuario usuario=null;
        Cursor cursor=db.rawQuery("Select * from " + Utils.TABLA_USUARIO, null);
        while (cursor.moveToNext()){
            usuario= new Usuario( );
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            usuario.setUrlimg(cursor.getString(3));


            listUsuario.add(usuario);
        }
    }


}