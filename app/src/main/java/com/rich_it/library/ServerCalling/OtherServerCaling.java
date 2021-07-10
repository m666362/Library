package com.rich_it.library.ServerCalling;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.rich_it.library.Others.Constant;

public class OtherServerCaling {

    private static String TAG = OtherServerCaling.class.getName();

    public static void getCategories (JSONArrayRequestListener listener) {
        AndroidNetworking.get(Constant.baseUrl + Constant.categories)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray((JSONArrayRequestListener) listener);

    }


}
