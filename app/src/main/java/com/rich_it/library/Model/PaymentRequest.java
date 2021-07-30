package com.rich_it.library.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

public class PaymentRequest implements Parcelable {
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

    protected PaymentRequest(Parcel in) {
        amount = in.readString();
        intent = in.readString();
    }

    public static final Creator<PaymentRequest> CREATOR = new Creator<PaymentRequest>() {
        @Override
        public PaymentRequest createFromParcel(Parcel in) {
            return new PaymentRequest(in);
        }

        @Override
        public PaymentRequest[] newArray(int size) {
            return new PaymentRequest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(amount);
        dest.writeString(intent);
    }
}
