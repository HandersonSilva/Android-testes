package com.example.frankenstein.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frankenstein.R;
import com.example.frankenstein.Util.ImageUtil;

import java.io.File;
import java.io.IOException;

public class MainCamera extends AppCompatActivity {

    private Button btn_tirarFoto,btn_escolherFoto;
    private TextView strBase64;
    ImageUtil imageUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_camera);
       imageUtil = new ImageUtil();
        btn_tirarFoto = findViewById(R.id.btn_tirarFoto);
        btn_escolherFoto = findViewById(R.id.btn_abrirFoto);

        //Abre Dialog perguntando se o app da ou não permissão de acesso as pastas
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){

            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
            }
        }

        btn_tirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);

            }
        });

        btn_escolherFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //verificar o retorno do request codigo
        if(requestCode == 2){
            Toast.makeText(this,"Permissão OK..",Toast.LENGTH_LONG).show();
        }
        File file = null;
        if(data!= null && requestCode == 1 && resultCode == RESULT_OK){
            Uri img = data.getData();

          //  Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(img));
           // Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap,300,300,true);

            String[] cols = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(img, cols, null, null, null);
            cursor.moveToFirst();

            int indexCol = cursor.getColumnIndex(cols[0]);
            String imgString = cursor.getString(indexCol);
            cursor.close();

            Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(imgString));
            ImageView ivBase= findViewById(R.id.img_base64);
            ivBase.setImageBitmap(bitmap);

          //  file = new File(imgString);
           /* if(file != null){
                Bitmap bitmap = setResizedBitmap(file, 300, 300);

                ImageView ivBase= findViewById(R.id.img_base64);
                ivBase.setImageBitmap(bitmap);

            }*/


        }else{
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


    public Bitmap setResizedBitmap(File file, int width, int height) {
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeFile(file.getPath());

            int w = bitmap.getWidth();
            int h = bitmap.getHeight();

            float scaleX = (float) width / bitmap.getWidth();
            float scaleY = (float) height / bitmap.getHeight();

            Matrix matrix = new Matrix();
            matrix.setScale(scaleX, scaleY);
            Bitmap auxBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);

            auxBitmap = fixMatrix(file, auxBitmap);

            // Retira da memória o arquivo de imagem com tamanho original
            bitmap.recycle();
            bitmap = auxBitmap;
            return  bitmap;
        }
        catch (OutOfMemoryError e) { e.printStackTrace(); }
        catch (RuntimeException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
        return  null;
    }

    private static Bitmap fixMatrix(File file, Bitmap bitmap) throws IOException {
        Matrix matrix = new Matrix();
        boolean fixed = false;
        ExifInterface exif = new ExifInterface(file.getPath()); // Classe para ler tags escritas no JPEG
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL); // Orientação que foi salva a foto

        // Rotate bitmap
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                fixed = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                fixed = true;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                fixed = true;
                break;
            default:
                fixed = false;
                break;
        }

        if(!fixed) {
            return bitmap;
        }

        // Correção da orientação da foto (passa a matrix)
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix,true);

        bitmap.recycle();
        bitmap = null;

        return newBitmap;
    }


}
