package com.tfb.aibank.chl.common.pdf.declarationform.model;

import com.tfb.aibank.chl.common.pdf.declarationform.model.DeclarationForm.DeclarationPageEnum;

public interface IDeclarationFormEnum {
    boolean isThisDocVersion(String docVer, DeclarationPageEnum page);

    float getX();

    float getY();

    float getX(String settlement);

    float getY(String settlement);
}
