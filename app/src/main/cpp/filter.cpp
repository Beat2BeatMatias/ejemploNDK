//
// Created by usuario on 25/10/2018.
//
#include <jni.h>
#include <string>
#include <cmath>
#include <stddef.h>
#include <stdlib.h>
#include "Complejo.cpp"

using namespace std;
extern "C"

JNIEXPORT jdoubleArray JNICALL Java_com_example_windows10_androidndk_MainActivity_filterFromJNI(JNIEnv *env, jobject obj,jdouble b,jdoubleArray a, jdouble g,jdoubleArray h,jdoubleArray src) {
    jsize N = env->GetArrayLength(src);
    jdouble *sSrc = env->GetDoubleArrayElements(src,0);
    jdouble *sH = env->GetDoubleArrayElements(h,0);
    jsize lenA=env->GetArrayLength(a);
    jdouble *sA= env->GetDoubleArrayElements(a,0);

    jint lenA2=(lenA+1);
    jdouble a2[lenA2];

    for (jint i=0;i<lenA2;i++){
        if (i==0)
            a2[i]=-1;
        else
            a2[i]=sA[i-1];
    }

    jdouble vSalida[N];
    jdoubleArray salida = env->NewDoubleArray(N);

    jdouble tmp = 0;
    jint j=0;

    for(jint i=0; i < N; i++)
    {
        tmp = 0;
//        vSalida[i] = 0;
        for(j=0; j < 1; j++){
            if(i - j < 0) continue;
            tmp += b * sSrc[i-j]*g;
        }

        for(j=1; j < lenA2; j++){
            if(i - j < 0) continue;
            tmp -= a2[j]*vSalida[i-j];
        }

        tmp /= a2[0];

        vSalida[i] = tmp;
    }

    for(jint i=0; i < N; i++){
        vSalida[i]=vSalida[i]*sH[i];
    }

    env->SetDoubleArrayRegion(salida,0,N,vSalida);

    return salida;
}
