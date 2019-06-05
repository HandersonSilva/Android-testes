package com.example.frankenstein.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.frankenstein.Entidades.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BD {
    private SQLiteDatabase db;
    public  BD(Context context){
        BDCore auxBD = new BDCore(context);
        this.db = auxBD.getWritableDatabase();
    }

    public void  inserir(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome",usuario.getNome());
        values.put("funcao",usuario.getFuncao());
        values.put("senha",usuario.getSenha());

        db.insert("usuario",null,values);


    }
    public void  update(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome",usuario.getNome());
        values.put("funcao",usuario.getFuncao());

        db.update("usuario",values,"_id = ?",new String[]{""+usuario.get_id()});


    }
    public void delete(Usuario usuario){
        db.delete("usuario","_id = "+usuario.get_id(),null);
    }

    public List<Usuario> buscar(){
        List<Usuario> list = new ArrayList<Usuario>();
        String[] colunas = new String[]{"_id","nome","funcao"};
        Cursor cursor = db.query("usuario",colunas,null,null,null,null,"nome ASC","10");

        if(cursor.getCount() >0){
            cursor.moveToFirst();
            do{
                Usuario u = new Usuario();
                u.set_id(cursor.getInt(0));
                u.setNome(cursor.getString(1));
                u.setFuncao(cursor.getString(2));

                list.add(u);
            }while (cursor.moveToNext());
        }

        return list;
    }
}

