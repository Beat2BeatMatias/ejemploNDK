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
import org.opencv.core.MatOfDouble;
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
        System.loadLibrary("metodoLPC");
        System.loadLibrary("matrizOLA");
        System.loadLibrary("pressStack");
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
        double[] audio = {0, 1, 1, 2, 3, 4, 4, 4, 3, 2, 1, 1, 0};

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
///////////////////////////////////////////////////////////////////////////////////
//        int[] matriz={1,2,3,4};
//        int[] matriz2=new int[4];
//        Mat mat=new Mat(2,2,CvType.CV_32S);
//        mat.put(0,0,matriz);
//        mat.get(0,0,matriz2);
//
//        double src[]={0.1,-0.1,0.5,-0.5};
//        double h[]={0.1,0.5,0.7,0.1};
//        double[] senialE={1.3,1.5,-0.65,0.23};
//        double b=1;
//        double[] a={1.5,-0.5};
//        double g=5;
//
//        double[] ejemploFiltrado=filterFromJNI(b,a,g,h,src);
//
//        Log.i("mat",""+mat.get(1,0)[0]);
///////////////////////////////////////////////////////////////////////////////////

        Mat preSenialMat=new Mat(1,6*2,CvType.CV_64F);
        int iCol=0;
        for(int i=0;i<2;i++) {

            double[] prueba = {1, 2, 3, 4, 5, 6};

            Mat tempM = new Mat(1, prueba.length, CvType.CV_64F);
            tempM.put(0, 0, prueba);

            int p = 2;

            double[] A = metodoLPCFromJNI(prueba, p);

            //Calculo de a=pinv(A)*b
            Mat Amat = new Mat(p, prueba.length - 1, CvType.CV_64F);
            Amat.put(0, 0, A);

            Mat AmatPI = Mat.zeros(prueba.length - 1, p, CvType.CV_64F);

            //Calcula la inversa o pseudoinversa de la matriz
            Core.invert(Amat, AmatPI, Core.DECOMP_SVD);

            //AIT=A'
            Mat AmatPIT = new Mat(AmatPI.cols(), AmatPI.rows(), CvType.CV_64F);
            Core.transpose(AmatPI, AmatPIT);

            Mat b;
            b = tempM.submat(0, 1, 1, tempM.cols());
            //bT=b'
            Mat bT = new Mat();
            Core.transpose(b, bT);

            //Multiplicacion matricial
            Mat a = Mat.zeros(AmatPIT.rows(), b.cols(), CvType.CV_64F);
            Core.gemm(AmatPIT, bT, 1, a, 0, a);

            double[] dA = new double[a.rows() * a.cols()];
            a.get(0, 0, dA);

            //Calculo de e=b-A*a
            Mat AmatI = new Mat();
            Core.transpose(Amat, AmatI);
            Mat c = Mat.zeros(AmatI.rows(), a.cols(), CvType.CV_64F);
            Core.gemm(AmatI, a, 1, c, 0, c);
            Mat e = new Mat();
            Core.subtract(bT, c, e);

            //Calculo de g=sqrt(var(e))
            MatOfDouble gStd = new MatOfDouble();
            MatOfDouble gMean = new MatOfDouble();
            Core.meanStdDev(e, gMean, gStd);

            double[] dGstd = new double[gStd.rows() * gStd.cols()];
            gStd.get(0, 0, dGstd);


            double[] dSrc = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6};
            double[] hamming = {1, 1, 1, 1, 1, 1};

            //DecodificadoLPC
            double sFiltrada[] = filterFromJNI(1, dA, 2, hamming, dSrc);
            Log.i("aplicarLPC", "" + sFiltrada.length);//Comprobado

            preSenialMat.put(0,iCol,sFiltrada);

            iCol+=6;
        }
        double preSenial[]=new double[preSenialMat.rows()*preSenialMat.cols()];
        preSenialMat.get(0,0,preSenial);

        double[] senialLPC = pressStackFromJNI(preSenial,3,6,2);
        Mat senialLPCMAT=new Mat(1,senialLPC.length,CvType.CV_64F);
        senialLPCMAT.put(0,0,senialLPC); //comprobado
////////////////////////////////////////////////////////////////////////////
////        prueba=[1,2,3,4,5,6];
////        w=[0.2,0.5,0.2];
////        X=matrizOLA(prueba,w);
//        double prueba[]={1,2,3,4,5,6};
//        double w[]={0.2,0.5,0.2};
//        double X[]=matrizOLAFromJNI(w,prueba);
//////////////////////////////////////////////////////////////////////////////////////////////
//        double[] preSenial={1,2,3,4,5,6,7,8};
//        int step=2;
//        double[] senialLPC = pressStackFromJNI(preSenial,step,4,2);
//////////////////////////////////////////////////////////////////////////////////////////////
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
    public native double[] metodoLPCFromJNI(double[] S,int p);
    public native double[] matrizOLAFromJNI(double[] H,double[] S);
    public native double[] pressStackFromJNI(double[] preSenial,int step,int hammingLength,int count);
}
