//
// Created by usuario on 29/10/2018.
//
#include <jni.h>
#include <string>
#include <cmath>
#include <stddef.h>
#include <stdlib.h>
#include "Complejo.cpp"

using namespace std;
extern "C"

JNIEXPORT jdoubleArray JNICALL
Java_com_example_windows10_androidndk_MainActivity_pressStackFromJNI(JNIEnv *env, jobject obj,
                                                                             jdoubleArray preSenial,jint step,jint hammingLength,jint count) {
    jsize N = env->GetArrayLength(preSenial);
    jdouble *sSrc = env->GetDoubleArrayElements(preSenial,0);
    jint n = (count-1)*step+hammingLength;

    jdouble senialFinal[n];
    for(jint i=0;i<n;i++)
        senialFinal[i]=0;

    jint k=0;
    for(jint i=0;i<count;i++){
        for(jint j=0;j<hammingLength;j++){
            senialFinal[j+step*i]=senialFinal[j+step*i]+sSrc[j+k];
        }
        k+=hammingLength;
    }

    jdoubleArray jsenialFinal=env->NewDoubleArray(n);
    env->SetDoubleArrayRegion(jsenialFinal,0,n,senialFinal);

    return jsenialFinal;
}