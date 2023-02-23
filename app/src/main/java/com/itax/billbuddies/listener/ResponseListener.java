package com.itax.billbuddies.listener;

public interface ResponseListener {
    void  onResponse(int requestCode, String response);
    void onError(int requestCode, String error);
}
