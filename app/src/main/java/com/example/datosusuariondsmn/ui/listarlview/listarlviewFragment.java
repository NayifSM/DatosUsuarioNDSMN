package com.example.datosusuariondsmn.ui.listarlview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.datosusuariondsmn.R;

import java.util.ArrayList;

import Utils.Utils;
import entidades.ConexionSqlHelper;
import entidades.Usuario;

public class listarlviewFragment extends Fragment {
    ListView lstusers;
    ConexionSqlHelper conn;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;
    private listarlviewViewModel listarlviewViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista =inflater.inflate(R.layout.fragment_listarlview, container, false);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_listarlview);
        lstusers=vista.findViewById(R.id.lstUsuarios);
        conn= new ConexionSqlHelper(getContext(),"db_usuarios",null,1);
        consultarListaUsuarios();
        ArrayAdapter adaptador = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,
                listaInformacion);
        lstusers.setAdapter(adaptador);
        lstusers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String informacion="Id: " + listaUsuarios.get(i).getId()+"\n";
                informacion+="Nombre: "+ listaUsuarios.get(i).getNombre()+"\n";
                informacion+="telefono: " + listaUsuarios.get(i).getTelefono()+"\n";
                Toast.makeText(getContext(), informacion, Toast.LENGTH_SHORT).show();
            }
        });
        return  vista;
    }

    private void consultarListaUsuarios() {

        SQLiteDatabase db=conn.getReadableDatabase();
        Usuario usuario=null;
        listaUsuarios= new ArrayList<Usuario>();
        Cursor cursor=db.rawQuery("Select * from " + Utils.TABLA_USUARIO, null);
        while (cursor.moveToNext()){
            usuario= new Usuario( );
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }
    private void obtenerLista() {
        listaInformacion= new ArrayList<String>();
//listaInformacion.add("Documen Nombre Telefono");
        for ( int i=0; i<listaUsuarios.size();i++){
            listaInformacion.add(listaUsuarios.get(i).getId()+" - "+
                    listaUsuarios.get(i).getNombre() + " - " + listaUsuarios.get(i).getTelefono());
        }
    }
}