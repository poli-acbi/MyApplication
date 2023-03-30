package com.facens.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class GPStracker implements LocationListener {

    Context context;
    //define a classe
    public GPStracker(Context c){
        context = c;
    }
    //define o método
    public Location getLocation(){
        //verifica se a permissão foi concedida
        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED){
        //exibe um toast informando que o acesso não foi permitido
            Toast.makeText(context, "Não foi permitido", Toast.LENGTH_SHORT).show();
            return null;
        }
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //se o provedor de GPS está habilitado, solicita atualizações de localização e retorna a última localização
        if (isGPSEnabled){
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, this);
            Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            return l;
        }
        else {
        //exibe um toast informando que o provedor de GPS não está habilitado
            Toast.makeText(context, "Por favor, habilitar o GPS!", Toast.LENGTH_LONG).show();
        }
        //
        return null;
    }
    //quando o provedor de GPS é desabilitado pelo usuário
    @Override
    public void onProviderDisabled(@NonNull String provider) {    }
    //quando ocorre uma mudança na localização do dispositivo
    @Override
    public void onLocationChanged(@NonNull Location location) {    }
    //quando há  mudança no status do provedor de GPS
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {    }

}
