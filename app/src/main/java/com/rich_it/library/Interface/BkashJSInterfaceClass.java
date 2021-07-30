package com.rich_it.library.Interface;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.rich_it.library.Activity.PaymentSuccessfullActivity;

public class BkashJSInterfaceClass {
    Context context;

    public BkashJSInterfaceClass(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void OnPaymentSuccess(String data){
        String[] paymentData = data.split("&");
        String paymentId = paymentData[0].trim().replace("PaymentID= ", "").trim();
        String transactionId = paymentData[1].trim().replace("TransactionID= ", "").trim();
        String amount = paymentData[3].trim().replace("Amount= ", "").trim();

        Intent intent = new Intent(context, PaymentSuccessfullActivity.class);
        intent.putExtra("paymentId", paymentId);
        intent.putExtra("transactionId", transactionId);
        intent.putExtra("amount", amount);
        context.startActivity(intent);
    }
}
