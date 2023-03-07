package com.itax.billbuddies.zxing;

import static android.Manifest.permission.CAMERA;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.zxing.Result;
import com.itax.billbuddies.utils.Constants;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView mScannerView;
    String result="";
    String mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                // Toast.makeText(getApplicationContext(), "Permission already granted", Toast.LENGTH_LONG).show();
            } else {
                requestPermission();
            }
        }
        mode=getIntent().getStringExtra("mode");
    }
    private boolean checkPermission() {
        return ( ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA ) == PackageManager.PERMISSION_GRANTED);
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void handleResult(Result rawResult) {
        result = rawResult.getText();
        Log.d("QRCodeScanner", rawResult.getText());
        Log.d("QRCodeScanner", rawResult.getBarcodeFormat().toString());

        if (mScannerView != null){
            mScannerView.stopCameraPreview();
            mScannerView.stopCamera();
        }
        //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
        Constants.scanned_result = result;
        Intent intent = new Intent();
        intent.putExtra("result",result);
        setResult(RESULT_OK,intent);
        finish();
    }


    @Override
    public void onResume() {
        super.onResume();
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentApiVersion >= android.os.Build.VERSION_CODES.M) {
            if (checkPermission()) {
                if(mScannerView == null) {
                    mScannerView = new ZXingScannerView(this);
                    setContentView(mScannerView);
                }
                mScannerView.setResultHandler(this);
                mScannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }
    public void onBackPressed(){
        finish();
    }
}


