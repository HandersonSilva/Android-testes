package com.example.frankenstein.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.frankenstein.Adapter.AdapterUser;
import com.example.frankenstein.DAO.BD;
import com.example.frankenstein.Entidades.Usuario;
import com.example.frankenstein.R;

import java.util.List;

public class Listar_user extends AppCompatActivity {
    private RecyclerView rv_listarUser;
    private List<Usuario> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_user);


        rv_listarUser = findViewById(R.id.rv_listarUser);
        BD bd = new BD(this);
        list = bd.buscar();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_listarUser.setLayoutManager(layoutManager);
        AdapterUser adapter = new AdapterUser(this,list);
        rv_listarUser.setAdapter(adapter);

    }
}
