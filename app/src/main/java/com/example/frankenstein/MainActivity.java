package com.example.frankenstein;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.frankenstein.Activity.Cadastro_user;
import com.example.frankenstein.Activity.Listar_user;
import com.example.frankenstein.Activity.MainCamera;

public class MainActivity extends AppCompatActivity {

    private Button btn_cadUser, btn_listarUser, btn_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_cadUser = findViewById(R.id.btn_caduser);
        btn_listarUser = findViewById(R.id.btn_listaUser);
        btn_camera = findViewById(R.id.btn_camera);

        btn_listarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Listar_user.class);
                startActivity(intent);

            }
        });

        btn_cadUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cadastro_user.class);
                startActivity(intent);

            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainCamera.class);

                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_main:
                Log.i("MENU","Teste click");

                return true;
            case R.id.menu_notifiq:
                Log.i("MENU","Teste click");
                return true;
            case R.id.menu_sair:
                Log.i("MENU","Teste click");
                return true;
            case android.R.id.home:
                Log.i("MENU","Teste click");
                return true;
                default:
                    return  super.onOptionsItemSelected(item);

        }
    }
}
