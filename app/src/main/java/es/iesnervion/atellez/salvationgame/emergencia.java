package es.iesnervion.atellez.salvationgame;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class emergencia extends AppCompatActivity implements View.OnClickListener {
    Button btnLlamar;
    String numIntroducido, nomUsuario;
    Switch loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);

        //Obtenemos el nombre del usuario
        Bundle bundle = getIntent().getExtras();
        nomUsuario = bundle.getString("nomUsuario");

        //Obtenemos el numero de telefono del usuario
        Bundle bundle1 = getIntent().getExtras();
        numIntroducido = bundle1.getString("numTelefono");

        //Localizamos el btn en el layout
        btnLlamar = (Button) findViewById(R.id.btnAyuda);
        btnLlamar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        //COMPROBAR SI TIENE LOS PERMISOS PARA PODER LLAMAR
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para realizar llamadas telefónicas.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 225);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:"+numIntroducido)); //
            startActivity(callIntent);
        }


        /*
        //Problemas:
        //No lo envia directamente
        //Si el usuario tiene una aplicacion que gestione sms nos va a dar a elegir entre esas aplicaciones

        PackageManager pm = this.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY) && !pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY_CDMA)) {
            Toast.makeText(this, "Lo sentimos, su dispositivo probablemente no pueda enviar SMS...", Toast.LENGTH_SHORT).show();
        }else {
            Uri sms_uri = Uri.parse("smsto:+" + numIntroducido);
            Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
            sms_intent.putExtra("sms_body", "El usuario "+nomUsuario+" ha sufrido un accidente en breves nos pondremos en contacto con usted");
            startActivity(sms_intent);
        }*/

        Toast.makeText(this, "Realizando la llamada al "+numIntroducido, Toast.LENGTH_SHORT).show();
    }


}
