package com.example.datosusuariondsmn.ui.listarspinner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.datosusuariondsmn.R;
import com.example.datosusuariondsmn.ui.tools.ToolsViewModel;

import java.util.ArrayList;

import Utils.Utils;
import entidades.ConexionSqlHelper;
import entidades.Usuario;



public class listaruserspinnerFragment extends Fragment {

/*
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_listarspinner, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);

        return root;
    }
    */
   Spinner cpPersonas;
    TextView txtId, txtNom, txtTel;
    ArrayList<String> listaUsuarios;
    ArrayList<Usuario> UsuariosList;
    ConexionSqlHelper conn;
    private listarusesrspinnerViewModel listarusesrspinnerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_listarspinner, container, false);
       // setContentView(R.layout.activity_listar_user_spinner);
        cpPersonas = vista.findViewById(R.id.cmbUsuarios);
        txtId = vista.findViewById(R.id.txtDocumento);
        txtNom = vista.findViewById(R.id.txtNombre);
        txtTel = vista.findViewById(R.id.txtTelefono);
        conn = new ConexionSqlHelper(getContext(), "db_usuarios", null, 1);
        consultarUsuario();
      // ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource( getActivity().getBaseContext(),R.array.options_array,android.R.layout.simple_spinner_item);
        //ArrayAdapter<CharSequence> adaptador =new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,listaUsuarios);
        ArrayAdapter<String> adaptador =new ArrayAdapter(getActivity().getBaseContext(),android.R.layout.simple_spinner_dropdown_item,listaUsuarios);
        cpPersonas.setAdapter(adaptador);
      //  En su lugar, utilice setOnItemSelectedListener (AdapterView.OnItemSelectedListener).
        cpPersonas.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
                if(posicion!=0){
                    txtId.setText(UsuariosList.get(posicion - 1).getId().toString());
                    txtNom.setText(UsuariosList.get(posicion - 1).getNombre().toString());
                    txtTel.setText(UsuariosList.get(posicion - 1).getTelefono().toString());
                }else{
                    txtId.setText("");
                    txtNom.setText("");
                    txtTel.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }



        });
        //Spinner spinner = (spinn)
        cpPersonas.setAdapter(adaptador);
        return vista;

    }

    private void consultarUsuario() {
        SQLiteDatabase db= conn.getReadableDatabase();
        Usuario usuario=null;
        UsuariosList= new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utils.TABLA_USUARIO,null);
        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            Log.i("id",usuario.getId().toString());
            Log.i("Nombre",usuario.getNombre().toString());
            Log.i("Telefono",usuario.getTelefono().toString());
            UsuariosList.add(usuario);
        }
        obtenerListaUsuarios();
    }

    private void obtenerListaUsuarios() {
        listaUsuarios= new ArrayList<String>();
        listaUsuarios.add("Seleccione");
        for ( int i=0;i<UsuariosList.size();i++ ){
            listaUsuarios.add(UsuariosList.get(i).getId()+" - "+ UsuariosList.get(i).getNombre()+" - "+
                    UsuariosList.get(i).getTelefono());
        }
    }
}