package com.testejava.testejavaws.dominio;

import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;

@Getter
public enum TipoPermitidos {
    JPEG ("JPEG"),
    GIF ("GIF"),
    BMP ("BMP"),
    PNG ("PNG");

    TipoPermitidos(final String tipo) {}

    public static Collection<TipoPermitidos> valuesAceitos() {
        return Arrays.asList(JPEG,GIF, BMP,PNG);
    }

}
