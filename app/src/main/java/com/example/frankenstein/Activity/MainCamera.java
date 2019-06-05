package com.example.frankenstein.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.frankenstein.R;

public class MainCamera extends AppCompatActivity {

    private Button btn_tirarFoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);

        btn_tirarFoto = findViewById(R.id.btn_tirarFoto);

        btn_tirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //verificar o retorno do request codigo
        if(data!=null){
            Bundle bundle = data.getExtras();
            if(bundle!=null){
                Bitmap bitmap = (Bitmap) bundle.get("data");

                ImageView iv = findViewById(R.id.img_foto);
                iv.setImageBitmap(bitmap);
            }
        }
    }


}
