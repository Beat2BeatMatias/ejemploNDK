//
// Created by usuario on 10/7/2018.
//

#include <jni.h>
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
