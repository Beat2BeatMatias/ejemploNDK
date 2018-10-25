package com.example.windows10.androidndk;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("aplicarComplejo");
        System.loadLibrary("Complejo");
        System.loadLibrary("multComplexI");
        System.loadLibrary("multComplexR");
        System.loadLibrary("filter");
        if(OpenCVLoader.initDebug()){
            Log.i("OCV","ok");
        }else{
            Log.i("OCV","bad");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        int[][] M1 = {{2, 3}, {4, 6}, {2, 4}};
        int[][] M2 = {{4, 3}, {0, 2}, {1, 5}};
        int[][] M3 = sumaMatrices(M1, M2);
        double[] audio = {0 ,1 , 1, 2, 3, 4,4,4,3,2,1,1,0};

//        Mat audioMat = new Mat(1, audio.length, CvType.CV_64F);
//        audioMat.put(0, 0, audio);
//
//        List<Mat> planes = new ArrayList<Mat>();
//        planes.add(audioMat);
//        planes.add(Mat.zeros(audioMat.size(), CvType.CV_64F));
//        Mat complexI = new Mat();
//        Core.merge(planes, complexI);
//        Core.dft(complexI,complexI);
//        Core.split(complexI, planes);
//        // planes.get(0) = Re(DFT(I)
//        double[] audioR=new double[planes.get(0).cols()*planes.get(0).rows()];
//        planes.get(0).get(0,0,audioR);
//        // planes.get(1) = Im(DFT(I))
//        double[] audioI=new double[planes.get(0).cols()*planes.get(0).rows()];
//        planes.get(1).get(0,0,audioI);
//        // planes.get(0) = magnitude
//
//        double[] sRealM=multComplexRealFromJNI(audioR,audioI);
//        double[] sImgM=multComplexImaginarioFromJNI(audioR,audioI);
//
//        Core.idft(complexI,complexI,Core.DFT_SCALE,0);
//        Core.split(complexI, planes);
//        float[] audioRi=new float[planes.get(0).cols()*planes.get(0).rows()];
//        planes.get(0).get(0,0,audioRi);
//        // planes.get(1) = Im(DFT(I))
//        float[] audioIi=new float[planes.get(0).cols()*planes.get(0).rows()];
//        planes.get(1).get(0,0,audioIi);
        // planes.get(0) = magnitude
//        Core.magnitude(planes.get(0), planes.get(1), planes.get(0));
//        Mat magI = planes.get(0);
//        //        audiof.get(0,0,af);
////        tv.setText(""+af[0]+", "+ af[1] +", "+ af[2] +", "+ af[3]);
////        Toast.makeText(this, ""+af[0] + af[1] + af[2] + af[3], Toast.LENGTH_LONG).show();
//        double[] a={1,5};
//        double[] b;
//        b=complejoFromJNI(a);
//        int l = 2;

        int[] matriz={1,2,3,4};
        int[] matriz2=new int[4];
        Mat mat=new Mat(2,2,CvType.CV_32S);
        mat.put(0,0,matriz);
        mat.get(0,0,matriz2);

        double src[]={0.1,-0.1,0.5,-0.5};
        double h[]={0.1,0.5,0.7,0.1};
        double[] senialE={1.3,1.5,-0.65,0.23};
        double b=1;
        double[] a={1.5,-0.5};
        double g=5;

        double[] ejemploFiltrado=filterFromJNI(b,a,g,h,src);

        Log.i("mat",""+mat.get(1,0)[0]);

    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    private native int[][] sumaMatrices(int[][] A, int[][]B);
    private native double[] complejoFromJNI(double[] A);
    public native double[] multComplexRealFromJNI(double[] sR,double[] sI);
    public native double[] multComplexImaginarioFromJNI(double[] sR,double[] sI);
    public native double[] filterFromJNI(double b,double[] a,double g,double[] h,double[] src);
}
