package com.itax.billbuddies.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itax.billbuddies.listener.ResponseListener;
import com.itax.billbuddies.utils.Constants;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RequestApi {
    Context context;
    String response;
    ResponseListener listener;
    RequestQueue queue;
    HashMap<String, String> param,header_param;
    String baseUrl = "";
    int MY_SOCKET_TIMEOUT_MS = 15000;
    String requestBody = "";
    JSONObject jsonObject;
    int requestCode = 0,requestMethod = Request.Method.POST;
    String TAG = "bill_";

    public RequestApi(Context context, ResponseListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setMethod(int method){
        this.requestMethod = method;
    }

    public void setRequestBody(String body){
        this.requestBody = body;
    }

    public void setHeaders(HashMap headerMap){
        header_param = headerMap;
    }

    public String request(String url,HashMap params,int requestCode){
        this.param = params;
        this.requestCode = requestCode;
        queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(requestMethod,url, response -> {
            listener.onResponse(requestCode,response);
        }, error -> {
            Log.d("error",error.toString());
            listener.onError(requestCode,error.toString());
        }){
            @Override
            protected Map<String, String> getParams() {
                return param;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String>headersParam = new HashMap<>();
                //headersParam.put("Authorization", "Bearer "+ "");
                return headersParam;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
        return response;
    }


    //===============call json request============ //
    public void requestJson(String url, JSONObject jsonObject, int requestCode){
        this.requestCode = requestCode;
        requestBody = jsonObject.toString();
        queue = Volley.newRequestQueue(context);
        Log.d(TAG, "api: "+ url);
        Log.d(TAG, "body: "+ requestBody);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url,jsonObject,
                response -> {
                    Log.d(TAG, "response: "+ response.toString());
                    listener.onResponse(requestCode,response.toString());
                },
                error -> {
                    Log.d(TAG,error.toString());
                    listener.onError(requestCode,error.toString());
                }){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String,String>param = new HashMap<>();
                return param;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                {
                    HashMap<String, String>headersParam = new HashMap<>();
                    headersParam.put("Content-Type","application/json");
                    headersParam.put("Authorization", Constants.UAT_ACCESS_TOKEN);
                    if (header_param != null){
                        return header_param;
                    }
                    else {
                        return headersParam;
                    }
            }}
            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }
        };

        queue.getCache().clear();       // add it to clear cache
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsObjRequest);
    }

    public void requestJsonGet(String url, JSONObject jsonObject, int requestCode){
        this.requestCode = requestCode;
        requestBody = jsonObject.toString();
        queue = Volley.newRequestQueue(context);
        Log.d(TAG, "api: "+ url);
        Log.d(TAG, "body: "+ requestBody);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,jsonObject,
                response -> {
                    Log.d(TAG, "response: "+ response.toString());
                    listener.onResponse(requestCode,response.toString());
                },
                error -> {
                    Log.d(TAG,error.toString());
                    listener.onError(requestCode,error.toString());
                }){
            @Override
            protected Map<String, String> getParams() {
                HashMap<String,String>param = new HashMap<>();
                return param;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                {
                    HashMap<String, String> headerParam = new HashMap<>();
                    // headerParam.put("Accept","application/json");
                    headerParam.put("Content-Type", "application/json; charset=UTF-8");
                    headerParam.put("Authorization", Constants.UAT_ACCESS_TOKEN);
                    return headerParam;
                }}
            @Override
            public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }
        };

        queue.getCache().clear();       // add it to clear cache
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsObjRequest);
    }


    //===============call json request============ //
    public void requestJson(String url,HashMap params,int requestCode){
        this.param = params;
        this.requestCode = requestCode;
        Log.d(TAG, "requestJson: "+ url);
        Log.d(TAG, "body: "+ requestBody);
        queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(requestMethod, url,jsonObject,
                response -> {
                    Log.d(TAG, "onResponse: "+ response);
                    listener.onResponse(requestCode,response.toString());
                },
                error -> {
                    Log.d(TAG, "onErrorResponse: "+ error);
                    listener.onError(requestCode,error.toString());
                }){
            @Override
            protected Map<String, String> getParams() {
                return param;
            }

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String>headersParam = new HashMap<>();
                headersParam.put("Content-Type","application/json");
                headersParam.put("Authorization", Constants.UAT_ACCESS_TOKEN);
                if (header_param != null){
                    return header_param;
                }
                else {
                    return headersParam;
                }
            }
            @Override    public byte[] getBody() {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            requestBody, "utf-8");
                    return null;
                }
            }
        };
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsObjRequest);
    }
}
