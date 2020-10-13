package com.example.datosusuariondsmn.ui.consultaruser;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.datosusuariondsmn.R;
import com.squareup.picasso.Picasso;

import Utils.Utils;
import entidades.ConexionSqlHelper;

public class consultaruserFragment extends Fragment {
    EditText edtId,edtNom,edttel;
    Button btnbuscar;
    ConexionSqlHelper conn;
    ImageView img;
    private consultaruserViewModel consultaruserViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista =inflater.inflate(R.layout.fragment_consultaruser, container, false);
        edtId = vista.findViewById(R.id.edtCUid);
        edtNom = vista.findViewById(R.id.edtCUnombre);
        edttel = vista.findViewById(R.id.edtCUTel);
        img = vista.findViewById(R.id.imvCimagen);
        conn=new ConexionSqlHelper(getContext(), "db_usuarios",null,1);

        btnbuscar=vista.findViewById(R.id.btnconsulUsuario);

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultar();
            }
        });

        return  vista;

    }

    private void consultar() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros={edtId.getText().toString()};
        String[] campos={Utils.CAMPO_ID,Utils.CAMPO_NOMBRE,Utils.CAMPO_TELEFONO,Utils.CAMPO_URLImagen};
        try{
            Cursor cursor=db.query(Utils.TABLA_USUARIO,campos,
                    Utils.CAMPO_ID+"=?",parametros,null,
                    null,null);
            cursor.moveToFirst();
            edtNom.setText(cursor.getString(0));
            edttel.setText(cursor.getString(1));
            String ruta = cursor.getString(3);
            Picasso.get().load(ruta).into(img);
            cursor.close();
        }
        catch (Exception e){
            Toast.makeText(getContext(), "El registro no existe", Toast.LENGTH_SHORT).show();
        }
    }
}