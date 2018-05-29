package com.example.mhamada.session15;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by M.Hamada on 04/05/2018.
 */

public class Mysingleton {
    static Context context;
    static Mysingleton mysingleton;
    RequestQueue requestQueue;
    Mysingleton(Context context){
        this.context=context;
        requestQueue=getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if (requestQueue == null) {
            requestQueue= Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public synchronized static Mysingleton getMysingleton(Context context) {
        if(mysingleton==null){
            mysingleton=new Mysingleton(context);
        }
        return mysingleton;

    }
    public<T> void addtorequestQueue(Request<T> request){
        requestQueue.add(request);
    }
}
