package com.itax.billbuddies.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DecimalFormat;

import es.dmoral.toasty.Toasty;

public class Functions {
    Context context;
    ProgressDialog progressdialog;
    String msg = "Please Wait...";
    public static final DecimalFormat df = new DecimalFormat("0.00");

    public static String name;


    public Functions(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        progressdialog = new ProgressDialog(context);
    }

    public void showToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public void showSuccess(String message){
        Toasty.success(context,message,Toasty.LENGTH_SHORT,true).show();
    }

    public void showError(String message){
        Toasty.error(context,message,Toasty.LENGTH_SHORT,true).show();
    }

    public void setMessage(String msg){
        this.msg = msg;
    }

    public void showLoading(){
        progressdialog = new ProgressDialog(context);
        progressdialog.setMessage(msg);
        progressdialog.setCancelable(false);
        progressdialog.show();
    }

    public void hideLoading(){
        if (progressdialog !=null){
            progressdialog.dismiss();
        }
    }

    public void setInputError(EditText editText){
        editText.setError("Field should not be empty");
        editText.requestFocus();
    }

    public void setInputError(EditText editText,String message){
        editText.setError(message);
        editText.requestFocus();
    }

    public static long getFileDuration(Context context, Uri uri) {
        try {
            MediaMetadataRetriever mmr = new MediaMetadataRetriever();
            mmr.setDataSource(context, uri);
            String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            final int file_duration = Functions.ParseInteger(durationStr);
            return file_duration;
        } catch (Exception e) {

        }
        return 0;
    }

    public static int ParseInteger(String value){
        if(value!=null && !value.equals("")){
            return Integer.parseInt(value);
        }
        else
            return 0;
    }

    public static double ParseDouble(String value){
        if(value!=null && !value.equals("")){
            return Double.parseDouble(value);
        }
        else
            return 0;
    }

    public static boolean check_permissions(Activity context) {

        String[] PERMISSIONS = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA
        };

        if (!hasPermissions(context, PERMISSIONS)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                context.requestPermissions(PERMISSIONS, 2);
            }
        } else {

            return true;
        }

        return false;
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void deleteFile(String filePath){
        if (filePath != null){
            File file = new File(filePath);
            if (file.exists()){
                file.delete();
            }
        }
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public String convertToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        String imageString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return imageString;
    }


    public void checkInternet(ConnectivityReceiverListener receiverListener){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkRequest networkRequest = new NetworkRequest.Builder().build();
            connectivityManager.registerNetworkCallback(networkRequest, new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    super.onAvailable(network);
                    receiverListener.onNetworkConnectionChanged(true);
                    Log.i("Tag", "active connection");
                }

                @Override
                public void onLost(Network network) {
                    super.onLost(network);
                    Log.i("Tag", "losing active connection");
                    receiverListener.onNetworkConnectionChanged(false);
                }
            });
        }
    }

    public interface ConnectivityReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }
}
