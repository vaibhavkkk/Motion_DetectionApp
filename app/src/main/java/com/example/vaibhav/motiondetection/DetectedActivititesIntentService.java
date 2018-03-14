package com.example.vaibhav.motiondetection;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

/**
 * Created by Vaibhav on 2/23/2018.
 */

public class DetectedActivititesIntentService extends IntentService {

    protected static final String TAG=DetectedActivititesIntentService.class.getSimpleName();

    public DetectedActivititesIntentService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        ActivityRecognitionResult result=ActivityRecognitionResult.extractResult(intent);

        ArrayList<DetectedActivity> detectedActivities=(ArrayList)result.getProbableActivities();

        for (DetectedActivity activity: detectedActivities){
            Log.e(TAG,"Detected Activity: "+activity.getType()+", "+ activity.getConfidence());
            broadcastActivity(activity);
        }
    }

    private void broadcastActivity(DetectedActivity activity) {
    Intent intent=new Intent(Constants.BROADCAST_DETECTED_ACTIVITY);
    intent.putExtra("type",activity.getType());
    intent.putExtra("confidence",activity.getConfidence());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
