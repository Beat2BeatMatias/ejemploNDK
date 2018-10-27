//
// Created by usuario on 17/9/2018.
//
#include <jni.h>
#include <string>
#include <cmath>
#include <stddef.h>
#include <stdlib.h>
#include "Complejo.cpp"

using namespace std;
extern "C"

JNIEXPORT jdoubleArray JNICALL Java_com_example_windows10_androidndk_MainActivity_matrizOLAFromJNI(JNIEnv *env,
                                                                                                              jobject obj,jdoubleArray H,jdoubleArray S) {
    jsize n = env->GetArrayLength(S);
    jsize nw = env->GetArrayLength(H);

    jdouble* pS=env->GetDoubleArrayElements(S,0);
    jdouble* pH=env->GetDoubleArrayElements(H,0);

    jobjectArray  X;
    jclass tipo_array;
    tipo_array = env->FindClass("[D");
    if (tipo_array==NULL)
        return NULL; // Se produjo excepcion

    jint step = (jint) floor(nw*0.5);

    jint count = (jint)floor((n-nw)/step) + 1;

//    X=env->NewObjectArray(count,tipo_array,NULL);

    jdoubleArray Xv=env->NewDoubleArray(nw*count);

//    if (X==NULL)
//        return NULL; // Excepcion OutOfMemoryError lanzada

    jdouble mX[nw*count];
    jint k=0;
    jint indiceV=0;

    for (jint i = 0; i < count; i++) {

        //Matriz X
//        jdoubleArray vectorX = env->NewDoubleArray(nw);
//        jdouble *pVectorX = env->GetDoubleArrayElements(vectorX,NULL);

        for (jint j=0;j < nw;j++){
//            pVectorX[j]=pH[j]*pS[j+k];
            mX[j+indiceV]=pH[j]*pS[j+k];
        }
        //Matriz X
//        env->SetObjectArrayElement(X,i,vectorX);

        k=k+step;
        indiceV=indiceV+nw;

        //Matriz X
//        env->ReleaseDoubleArrayElements(vectorX,pVectorX,0);
//        env->DeleteLocalRef(vectorX);
    }
    env->SetDoubleArrayRegion(Xv,0,nw*count,mX);

    return Xv;
}


