//
// Created by usuario on 10/7/2018.
//

#include <jni.h>
#include <cmath>
#include "Complejo.h"

Complejo::Complejo(jdouble real, jdouble img) {
    re=real;
    im=img;
}
jdouble Complejo::getReal() {
    return re;
}
jdouble Complejo::getImg() {
    return im;
}
void Complejo::setReal(jdouble real) {
    re=real;
}
void Complejo::setImg(jdouble imaginario) {
    im=imaginario;
}
Complejo Complejo::suma(Complejo a, Complejo b) {
    Complejo c;
    jdouble cr=a.getReal()+b.getReal();
    jdouble ci=a.getImg()+b.getImg();
    c.setReal(cr);
    c.setImg(ci);
    return c;

}
Complejo Complejo::resta(Complejo a, Complejo b) {
    Complejo c;
    jdouble cr = a.getReal() - b.getReal();
    jdouble ci = a.getImg() - b.getImg();
    c.setReal(cr);
    c.setImg(ci);
    return c;
}
Complejo Complejo::multiplicacion(Complejo a, Complejo b) {
    Complejo c;
    jdouble cr=a.getReal()*b.getReal() - a.getImg()*b.getImg();
    jdouble ci=a.getReal()*b.getImg() + a.getImg()*b.getReal();
    c.setReal(cr);
    c.setImg(ci);
    return c;
}
Complejo Complejo::division(Complejo a, Complejo b) {
    Complejo c;
    jdouble cr=(a.getReal()*b.getReal()+a.getImg()*b.getImg())/(pow(b.getReal(),2.0) + pow(b.getImg(),2.0));
    jdouble ci=(a.getImg()*b.getReal()-a.getReal()*b.getImg())/(pow(b.getReal(),2.0) + pow(b.getImg(),2.0));
    c.setReal(cr);
    c.setImg(ci);
    return c;
}

