package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class PaymentRequest{
    String amount;
    String intent;

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return "PaymentRequest{"+
                "amount='"+ amount + "\'"+
                ", intent='"+ intent + "\'"+
                "}";
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }
}
