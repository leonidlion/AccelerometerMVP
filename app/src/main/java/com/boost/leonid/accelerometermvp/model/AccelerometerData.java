package com.boost.leonid.accelerometermvp.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.boost.leonid.accelerometermvp.Constants;

import java.util.HashMap;
import java.util.Map;

public class AccelerometerData implements Parcelable {
    private long unixTime;
    private float x;
    private float y;
    private float z;

    public AccelerometerData(){

    }

    protected AccelerometerData(Parcel in) {
        unixTime = in.readLong();
        x = in.readFloat();
        y = in.readFloat();
        z = in.readFloat();
    }

    public AccelerometerData(long unixTime, float x, float y, float z) {
        this.unixTime = unixTime;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static final Creator<AccelerometerData> CREATOR = new Creator<AccelerometerData>() {
        @Override
        public AccelerometerData createFromParcel(Parcel in) {
            return new AccelerometerData(in);
        }

        @Override
        public AccelerometerData[] newArray(int size) {
            return new AccelerometerData[size];
        }
    };

    public void setFloatArrayToVariables(float[] arr){
        x = arr[0];
        y = arr[1];
        z = arr[2];
    }

    public long getUnixTime() {
        return unixTime;
    }

    public void setUnixTime(long unixTime) {
        this.unixTime = unixTime;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public Map<String, Object> allToMap(){
        Map<String, Object> result = new HashMap<>();
        result.put(Constants.PUT_MAP_TIME, unixTime);
        result.put(Constants.PUT_MAP_X, x);
        result.put(Constants.PUT_MAP_Y, y);
        result.put(Constants.PUT_MAP_Z, z);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(unixTime);
        parcel.writeFloat(x);
        parcel.writeFloat(y);
        parcel.writeFloat(z);
    }
}
