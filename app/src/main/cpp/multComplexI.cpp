//
// Created by usuario on 11/7/2018.
//
#include <jni.h>
#include <string>
#include <cmath>
#include <stddef.h>
#include <stdlib.h>
#include "Complejo.cpp"

using namespace std;
extern "C"

JNIEXPORT jdoubleArray JNICALL Java_com_example_windows10_androidndk_MainActivity_multComplexImaginarioFromJNI(JNIEnv *env, jobject obj,jdoubleArray Real,jdoubleArray Imaginario) {
    jsize len = env->GetArrayLength(Real);
    jdouble *R = env->GetDoubleArrayElements(Real,0);
    jdouble *I = env->GetDoubleArrayElements(Imaginario,0);

    jdoubleArray V=env->NewDoubleArray(len);
    jdouble MVector[len];

    Complejo i(0,1);
    Complejo auxM,c;

//    for(jint j=0;j<len;j++){
//        auxM.setReal(R[j]);
//        auxM.setImg(I[j]);
//        c=c.multiplicacion(i,auxM);
//        MVector[j]=c.getImg();
//    }

    jint nP=(jint)(floor(len/2)+len%2);
    jint p=1;
    jint n=(jint)(ceil(len/2)+1+((len%2==1)?0:1));

    MVector[0]=I[0];
    for(jint j=0;j<len;j++){
        if(p<=nP){
            MVector[p]=I[p]*2;
            p++;
        }if(j>=n){
            MVector[j]=I[j]*0;
        }
    }

    env->SetDoubleArrayRegion(V,0,len,MVector);
    env->ReleaseDoubleArrayElements(Real,R,0);
    env->ReleaseDoubleArrayElements(Imaginario,I,0);

    return V;
}