//
// Created by usuario on 10/7/2018.
//

#ifndef EJEMPLONDK_COMPLEJO_H
#define EJEMPLONDK_COMPLEJO_H

class Complejo {

private:
    jdouble re;
    jdouble im;
public:
    Complejo(jdouble = 0 , jdouble = 0);
    Complejo suma(Complejo, Complejo);
//    Complejo resta(Complejo, Complejo);
//    Complejo multiplicacion(Complejo, Complejo);
//    Complejo division(Complejo, Complejo);
//    Complejo modulo(Complejo);
    jdouble getReal();
    jdouble getImg();
    void setReal(jdouble);
    void setImg(jdouble);
};


#endif //EJEMPLONDK_COMPLEJO_H
