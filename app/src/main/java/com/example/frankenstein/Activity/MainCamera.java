package com.example.frankenstein.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frankenstein.R;
import com.example.frankenstein.Util.ImageUtil;

public class MainCamera extends AppCompatActivity {

    private Button btn_tirarFoto;
    private TextView strBase64;
    ImageUtil imageUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);
       imageUtil = new ImageUtil();
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

                String base = imageUtil.convertImgBitmap(bitmap);
                strBase64 = findViewById(R.id.txt_base64);
                strBase64.setText(base);


                Bitmap baseBitmap = imageUtil.convertImgStr(base);

                ImageView ivBase= findViewById(R.id.img_base64);
                ivBase.setImageBitmap(baseBitmap);


            }
        }
    }


}
