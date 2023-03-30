package com.facens.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    //cria uma instância da classe Timer e declara uma variável TimerTask
    private final Timer timer = new Timer();
    TimerTask timerTask;
    //configura a interface do usuário e inicializa os componentes necessários para a atividade
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //define uma tarefa agendada quando executada redireciona o usuário para a tela principal
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        gotoMainActivity();
                    }
                });
            }
        };
        //agenda a tarefa definida em timerTask para ser executada após um atraso de 3 segundos
        timer.schedule(timerTask, 3000);
    }
    //redireciona o usuário para a tela principal da aplicação
    private void gotoMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}