package com.example.datosusuariondsmn.ui.insertaruser;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.datosusuariondsmn.R;

import Utils.Utils;
import entidades.ConexionSqlHelper;

public class insertuserFragment extends Fragment {
    EditText edtid, edtNom, edttel,edtIUurl;
    Button btninsertar;
    private insertuserViewModel insertuserViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista =inflater.inflate(R.layout.fragment_insertaruser, container, false);
        edtid = vista.findViewById(R.id.edtIUid);
        edtNom = vista.findViewById(R.id.edtIUnom);
        edttel = vista.findViewById(R.id.edtIUtele);
        edtIUurl =vista.findViewById(R.id.edtIUurl);
        btninsertar=vista.findViewById(R.id.btnIUSregusuario);

        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuarios();
            }
        });

        return  vista;

    }

    private void registrarUsuarios() {
        ConexionSqlHelper conn = new ConexionSqlHelper(getContext(),"db_usuarios",null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Utils.CAMPO_ID, edtid.getText().toString());
        valores.put(Utils.CAMPO_NOMBRE, edtNom.getText().toString());
        valores.put(Utils.CAMPO_TELEFONO, edttel.getText().toString());
        valores.put(Utils.CAMPO_URLImagen,edtIUurl.getText().toString());
        Long resultado = db.insert(Utils.TABLA_USUARIO,null,valores);
        Toast.makeText(getContext(),"ID de registro "+ resultado ,Toast.LENGTH_SHORT).show();
        db.close();
    }
}