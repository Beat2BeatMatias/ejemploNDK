package com.example.windows10.androidndk;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("aplicarComplejo");
        System.loadLibrary("Complejo");
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
//        int[][] M1 = {{2, 3}, {4, 6}, {2, 4}};
//        int[][] M2 = {{4, 3}, {0, 2}, {1, 5}};
//        int[][] M3 = sumaMatrices(M1, M2);
        float[] audio = {1, 2, 3, 4, 5, 6};


        Mat audioMat = new Mat(1, audio.length, CvType.CV_32F);

        Mat padded= new Mat();
        audioMat.put(0, 0, audio);

        int r=Core.getOptimalDFTSize(audioMat.rows());
        int c=Core.getOptimalDFTSize(audioMat.cols());

        Core.copyMakeBorder(audioMat, padded, 0, r - audioMat.rows(), 0, c - audioMat.cols(), Core.BORDER_CONSTANT, Scalar.all(0));
        List<Mat> planes = new ArrayList<Mat>();
        padded.convertTo(padded, CvType.CV_32F);
        planes.add(padded);
        planes.add(Mat.zeros(padded.size(), CvType.CV_32F));
        Mat complexI = new Mat();
        Core.merge(planes, complexI);
        Core.dft(complexI,complexI);
        Core.split(complexI, planes);
        // planes.get(0) = Re(DFT(I)
        float[] audioR=new float[planes.get(0).cols()*planes.get(0).rows()];
        planes.get(0).get(0,0,audioR);
        // planes.get(1) = Im(DFT(I))
        float[] audioI=new float[planes.get(0).cols()*planes.get(0).rows()];
        planes.get(1).get(0,0,audioI);
        // planes.get(0) = magnitude

        Core.idft(complexI,complexI,Core.DFT_SCALE,0);
        Core.split(complexI, planes);

        float[] audioRi=new float[planes.get(0).cols()*planes.get(0).rows()];
        planes.get(0).get(0,0,audioRi);
        // planes.get(1) = Im(DFT(I))
        float[] audioIi=new float[planes.get(0).cols()*planes.get(0).rows()];
        planes.get(1).get(0,0,audioIi);
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

    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    private native int[][] sumaMatrices(int[][] A, int[][]B);
    private native double[] complejoFromJNI(double[] A);
}
