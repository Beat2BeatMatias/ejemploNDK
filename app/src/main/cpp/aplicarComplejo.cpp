//
// Created by usuario on 10/7/2018.
//

#include <jni.h>
#include <string>
#include <iomanip>
#include "Complejo.cpp"

using namespace std;
extern "C"

JNIEXPORT jdoubleArray JNICALL Java_com_example_windows10_androidndk_MainActivity_complejoFromJNI(JNIEnv *env,
                                                                                                          jobject obj,jdoubleArray arr) {
    Complejo a=Complejo();
    Complejo c=Complejo();
    Complejo b=Complejo(2,2);

    jsize len = env->GetArrayLength(arr);
    jdouble *C = env->GetDoubleArrayElements(arr,0);

    jdoubleArray arr2 = env->NewDoubleArray(len);
    jdouble *I = env->GetDoubleArrayElements(arr2,0);

    a.setReal(C[0]);
    a.setImg(C[1]);
    c=c.suma(a,b);

    jdouble aux1=c.getReal();
    jdouble aux2=c.getImg();

    I[0]=aux1;
    I[1]=aux2;

    jdouble a1=I[0];
    jdouble a2=I[1];

    return arr2;
}