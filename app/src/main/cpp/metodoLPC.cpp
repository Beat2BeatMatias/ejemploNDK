//
// Created by usuario on 18/9/2018.
//
#include <jni.h>
#include <string>
#include <cmath>
#include <stddef.h>
#include <stdlib.h>
#include "Complejo.cpp"

using namespace std;
extern "C"

JNIEXPORT jdoubleArray JNICALL Java_com_example_windows10_androidndk_MainActivity_metodoLPCFromJNI(JNIEnv *env,
                                                                                                           jobject obj,jdoubleArray X,jint p) {
    jsize N = env->GetArrayLength(X);
    jdouble *pX=env->GetDoubleArrayElements(X,0);


        //Creación del vector "b"
//        jdoubleArray b=env->NewDoubleArray(N-1);
//        env->SetDoubleArrayRegion(b,1,N-1,pX);

        //Creación del vector "xz"
        jdouble pXz[N+p];


        //Creación de la matriz "A"
//        jclass tipo=env->FindClass("[D");
//        jobjectArray A=env->NewObjectArray(p,tipo,NULL);

        //Creación del vector "A"
        jdoubleArray A=env->NewDoubleArray((N-1)*p);
        jdouble pA[(N-1)*p];

        //Seteo del vector "xz"
        for(jint k=0;k<(N+p);k++){
            if(k>=N)
                pXz[k]=0;
            else
                pXz[k]=pX[k];
        }
        jint l=0;
        for(jint j=0;j<p;j++){
            //Creación del vector "temp"
            jdouble pTemp[N+p];
            //Corrimiento circular
            jint c=j;
            if(c>0) {
                for (jint m = 0; m < (N + p); m++) {
                    if (c == 0) {
                        pTemp[m] = pXz[m - p + 1];
                    } else if (c > 0) {
                        pTemp[m] = pXz[(N + p - 1) - (c - 1)];
                        c = c - 1;
                    }
                }
            }else{
                for (jint m = 0; m < (N + p); m++) {
                        pTemp[m] = pXz[m];
                }
            }

            //Creación del vector "A" cuando se crea la matriz "A"
//            jdoubleArray vectorA=env->NewDoubleArray(N-1);
//            jdouble *pVectorA=env->GetDoubleArrayElements(vectorA,0);

            for(jint m=0;m <(N-1);m++){
                     pA[l]=pTemp[m];
                     l++;
            }

            //Cuando se crea la matriz "A"
//            env->SetObjectArrayElement(A,j,vectorA);
//
//            env->ReleaseDoubleArrayElements(temp,pTemp,0);
//            env->ReleaseDoubleArrayElements(vectorA,pVectorA,0);
//            env->DeleteLocalRef(temp);
//            env->DeleteLocalRef(vectorA);

        }

    env->SetDoubleArrayRegion(A,0,(N-1)*p,pA);

    return A;
}



