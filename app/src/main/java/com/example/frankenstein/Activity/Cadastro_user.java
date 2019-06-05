package com.example.frankenstein.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frankenstein.DAO.BD;
import com.example.frankenstein.Entidades.Usuario;
import com.example.frankenstein.R;

public class Cadastro_user extends AppCompatActivity {

   private EditText edit_nome,edit_funcao,edit_senha;
   private Usuario usuario;
   Button btn_cad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);
        usuario = new Usuario();
        btn_cad = findViewById(R.id.btn_cad2);


        btn_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_nome = findViewById(R.id.edit_nome);
                edit_funcao = findViewById(R.id.edit_funcao);
                edit_senha = findViewById(R.id.edit_senha);

                usuario.setNome(edit_nome.getText().toString());
                usuario.setFuncao(edit_funcao.getText().toString());
                usuario.setSenha(edit_senha.getText().toString());

                BD bd = new BD(Cadastro_user.this);
                bd.inserir(usuario);

                Toast.makeText(Cadastro_user.this,"Usuario cadastrado",Toast.LENGTH_LONG).show();

            }
        });
    }
}
