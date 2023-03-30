package com.facens.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //cria as variáveis
    private ImageView imageViewFoto;
    private Button btnGeo;

    //configura a activity e define a interface do usuário
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //define o botão usado para captura da localização e solicita permissão ao usuário para acessar a localização
        btnGeo = (Button) findViewById(R.id.btn_gps);
        ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        //define o ouvinte para o botão
        btnGeo.setOnClickListener(new View.OnClickListener() {
            //chama o método onClick quando clicar o botão, criando um novo objeto GPStracker e chama o seu método getLocation que retorna as informações da localização do dispositivo
            @Override
            public void onClick(View view) {
                GPStracker g = new GPStracker(getApplication());
                Location l = g.getLocation();
            //verifica se o objeto retornado não é nulo, se não for, um toast é exibido com a latitude e longitude
                if(l != null) {
                    double lat = l.getLatitude();
                    double lon = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LATITUDE: " + lat +"\n LONGITUDE: " + lon, Toast.LENGTH_LONG).show();
                }
            }
        });
        //
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CAMERA}, 0);
        }
        //solicita acesso a camera do dispositivo
        imageViewFoto = (ImageView) findViewById(R.id.imageView);
        findViewById(R.id.btn_pic).setOnClickListener(new View.OnClickListener() {
            //implementa o método onClick
            @Override
            public void onClick(View view) {
                tirarFoto();
            }
        });
      }
    //define a função tirarFoto, que é chamada quando o usuário clica no botão
    private void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }
    //define o método onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    //verifica se o código de solicitação é igual a 1 e se o resultado retornado pela atividade da câmera é RESULT_OK, indica que a operação foi concluída com sucesso
        if(requestCode == 1 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageViewFoto.setImageBitmap(imagem);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}