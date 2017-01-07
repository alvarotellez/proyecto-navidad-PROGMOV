package es.iesnervion.atellez.salvationgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextNombre, editTextNumero;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = (Button) findViewById(R.id.btnEntrar);

        editTextNombre = (EditText) findViewById(R.id.nomUsuario);
        editTextNumero = (EditText) findViewById(R.id.numContacto);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), emergencia.class);
                //Le pasamos el nombre del usuario
                intent.putExtra("nomUsuario",editTextNombre.getText().toString());
                //Le pasamos el numero de telefono
                intent.putExtra("numTelefono",editTextNumero.getText().toString());
                startActivityForResult(intent, 0); } });
    }
}
