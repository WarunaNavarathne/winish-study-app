package com.example.madfinal2;

public class calculation {
    protected float calculategpa(Float grdpt1,Float grdpt2,Float grdpt3,Float grdpt4,Float grdpt5,Float cred1,Float cred2,Float cred3,Float cred4,Float cred5){
        float totcred, totqp, gpa;

        totcred = cred1+cred2+cred3+cred4+cred5;
        totqp = (grdpt1*cred1)+(grdpt2*cred2)+(grdpt3+cred3)+(grdpt4+cred4)+(grdpt4*cred4)+(grdpt5*cred5);
        gpa = totqp/totcred;

        return gpa;
    }
}
