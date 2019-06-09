package com.example.frankenstein.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ImageUtil {

    public  Bitmap convertImgStr(String base64Str){

        byte[] decodedBytes = Base64.decode(base64Str.substring(base64Str.indexOf(",")+1),Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.length);
    }

    public  String convertImgBitmap(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);

        return Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);

    }

}
