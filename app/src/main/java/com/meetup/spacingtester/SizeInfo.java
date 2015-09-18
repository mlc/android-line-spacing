package com.meetup.spacingtester;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.meetup.spacingtester.BR;

public class SizeInfo extends BaseObservable implements Parcelable {
    private float textSize;
    private float lineSpacingMultiplier;
    private float lineSpacingExtra;

    public SizeInfo(Context context) {
        this.textSize = context.getResources().getDimension(R.dimen.default_text_size);
        this.lineSpacingMultiplier = 1.0f;
        this.lineSpacingExtra = 0.0f;
    }

    protected SizeInfo(Parcel in) {
        textSize = in.readFloat();
        lineSpacingMultiplier = in.readFloat();
        lineSpacingExtra = in.readFloat();
    }

    public static final Creator<SizeInfo> CREATOR = new Creator<SizeInfo>() {
        @Override
        public SizeInfo createFromParcel(Parcel in) {
            return new SizeInfo(in);
        }

        @Override
        public SizeInfo[] newArray(int size) {
            return new SizeInfo[size];
        }
    };

    @Bindable
    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        notifyPropertyChanged(BR.textSize);
    }

    @Bindable
    public float getLineSpacingMultiplier() {
        return lineSpacingMultiplier;
    }

    public void setLineSpacingMultiplier(float lineSpacingMultiplier) {
        this.lineSpacingMultiplier = lineSpacingMultiplier;
        notifyPropertyChanged(BR.lineSpacingMultiplier);
    }

    @Bindable
    public float getLineSpacingExtra() {
        return lineSpacingExtra;
    }

    public void setLineSpacingExtra(float lineSpacingExtra) {
        this.lineSpacingExtra = lineSpacingExtra;
        notifyPropertyChanged(BR.lineSpacingExtra);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(textSize);
        dest.writeFloat(lineSpacingMultiplier);
        dest.writeFloat(lineSpacingExtra);
    }
}
