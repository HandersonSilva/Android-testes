package com.example.frankenstein.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frankenstein.DAO.BD;
import com.example.frankenstein.Entidades.Usuario;
import com.example.frankenstein.R;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.ViewHolder> {

    private Context mContext;
    private List<Usuario> list;

    public AdapterUser(Context mContext, List<Usuario> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterUser.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_users, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUser.ViewHolder holder, int position) {
                holder.text_nome.setText(list.get(position).getNome());

                holder.btn_excluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BD bd = new BD(mContext);
                        bd.delete(list.get(position));

                        Toast.makeText(mContext,"Usuario deletado com sucesso",Toast.LENGTH_LONG).show();

                    }
                });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            Button btn_excluir;
            Button btn_editar;
            TextView text_nome;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_editar = itemView.findViewById(R.id.btn_rv_editar);
            btn_excluir = itemView.findViewById(R.id.btn_rv_excluir);
            text_nome = itemView.findViewById(R.id.text_rv_nome);






        }
    }
}
