package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

/**
 * @param picture The picture in which to detect to faces.
 */

public class Emojifier {
        public static final String LOG_TAG = "Emojier Probability";
        public static void detectFaces(Context context, Bitmap bitmap){
        // Create the detector object
        FaceDetector detector = new FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        // Build the frame which contain the picture
        Frame frame = new Frame.Builder().setBitmap(bitmap).build();

        // Detect the faces base on frame
        SparseArray<Face> faces = detector.detect(frame);

        if(faces.size() == 0){
            Toast.makeText(context,"No faces detected", Toast.LENGTH_LONG).show();
        }else{
            for (int i = 0; i<faces.size(); i++){
                Face face = faces.valueAt(i);
                getClassifications(face);
            }
        }
        detector.release();
    }
    public static void getClassifications(Face face){
        Log.d(LOG_TAG, "getClassification: smilingProb = "+ face.getIsSmilingProbability());
        Log.d(LOG_TAG, "getClassification: leftEyeOpenProb = " + face.getIsLeftEyeOpenProbability());
        Log.d(LOG_TAG, "getClassification: rightEyeOpenProb = " + face.getIsRightEyeOpenProbability());
    }
}
