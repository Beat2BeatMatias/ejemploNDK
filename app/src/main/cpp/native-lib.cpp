#include <jni.h>
#include <string>
#include <iomanip>
#include "Complejo.h"

using namespace std;

extern "C"
JNIEXPORT jobjectArray JNICALL Java_com_example_windows10_androidndk_MainActivity_sumaMatrices(
        JNIEnv *env,
        jobject /* this */,jobjectArray A,jobjectArray B) {

    jobjectArray arrayC;
    jsize i;
    jclass tipo_array;
    jsize n_vectores = env->GetArrayLength(A);
    if (n_vectores!=env->GetArrayLength(B))
        return NULL; // No se pueden sumar los arrays
// por ser distintos
    tipo_array = env->FindClass("[I");
    if (tipo_array==NULL)
        return NULL; // Se produjo excepcion
    arrayC = env->NewObjectArray(n_vectores,tipo_array,NULL);
    if (arrayC==NULL)
        return NULL; // Excepcion OutOfMemoryError lanzada
    for(i=0;i<n_vectores;i++)
    {
        jsize j;
        jint* bufferA;
        jint* bufferB;
        jint* bufferC;
        jintArray vectorA = (jintArray)
                env->GetObjectArrayElement(A,i);
        jintArray vectorB = (jintArray)
                env->GetObjectArrayElement(B,i);
        jsize longitud_vector =
                env->GetArrayLength(vectorA);
        jintArray vectorC =
                env->NewIntArray(longitud_vector);
        bufferA =
                env->GetIntArrayElements(vectorA,NULL);
        bufferB =
                env->GetIntArrayElements(vectorB,NULL);
        bufferC =
                env->GetIntArrayElements(vectorC,NULL);
        for (j=0;j<longitud_vector;j++)
            bufferC[j] = bufferA[j] + bufferB[j];
        env->ReleaseIntArrayElements(vectorA,bufferA,JNI_ABORT);
        env->ReleaseIntArrayElements(vectorB,bufferB,JNI_ABORT);
        env->ReleaseIntArrayElements(vectorC,bufferC,0);
        env->SetObjectArrayElement(arrayC,i,vectorC);
        env->DeleteLocalRef(vectorA);
        env->DeleteLocalRef(vectorB);
        env->DeleteLocalRef(vectorC);
    }
    return arrayC;
}