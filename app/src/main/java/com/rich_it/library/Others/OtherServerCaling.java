package com.rich_it.library.Others;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.rich_it.library.Activity.GoogleMapActivity;
import com.rich_it.library.Model.User;

import org.json.JSONArray;

public class OtherServerCaling {

    private static String TAG = OtherServerCaling.class.getName();
    private static String categories = "/categories";

    public static void getCategories (JSONArrayRequestListener listener) {
        AndroidNetworking.get(Constant.baseUrl + categories)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray((JSONArrayRequestListener) listener);

    }

    
}
