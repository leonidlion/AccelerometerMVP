package com.boost.leonid.accelerometermvp;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by leonid on 16.01.17.
 */

public class Constants {
    public static final String BASE_URL = "https://accelerometer-firebase.firebaseio.com";
    public static final long CONNECTION_TIME_OUT = 5;
    public static final long READ_TIME_OUT = 10;
    public static final String CHILD_USERS = "users";
    public static final String CHILD_DATA   = "data_coordinates";

    public static final String PUT_MAP_TIME = "time";
    public static final String PUT_MAP_X    = "x";
    public static final String PUT_MAP_Y    = "y";
    public static final String PUT_MAP_Z    = "z";

    public static final String PUT_MAP_START_DATE       = "startDate";
    public static final String PUT_MAP_START_TIME       = "startTime";
    public static final String PUT_MAP_INTERVAL         = "interval";
    public static final String PUT_MAP_DEVICE_MODEL     = "deviceModel";
    public static final String PUT_MAP_COORDINATES      = "coordinates";

    public static DatabaseReference getUserReference(){
        return FirebaseDatabase.getInstance().getReference().child(CHILD_USERS);
    }

    public static DatabaseReference getDataCoordinatesOfUserReference(String userId){
        return FirebaseDatabase.getInstance().getReference().child(CHILD_DATA).child(userId);
    }

    public static String getPathToDataForMapInsert(String userId, String sessionId){
        return "/" + CHILD_DATA + "/" + userId + "/" + sessionId + "/";
    }
}
