package com.example.windows10.androidndk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        int[][] M1 = {{2,3},{4,6},{2,4}};
        int[][] M2 = {{4,3},{0,2},{1,5}};
        int[][] M3 = sumaMatrices(M1,M2);

        Toast.makeText(this, ""+M3[0][1]+ " "+M3[1][0]+ " " + M3[2][1], Toast.LENGTH_SHORT).show();

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    private native int[][] sumaMatrices(int[][] A, int[][]B);


}
