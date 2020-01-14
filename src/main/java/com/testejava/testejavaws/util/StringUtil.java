/*
 * Copyright (c) Banco Central do Brasil.
 *
 * Este software � confidencial e propriedade do Banco Central do Brasil.
 * N�o � permitida sua distribui��o ou divulga��o do seu conte�do sem
 * expressa autoriza��o do Banco Central.
 * Este arquivo cont�m informa��es propriet�rias.
 */
package com.testejava.testejavaws.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@UtilityClass
public final class StringUtil {

    public static final String SEPARADOR_LISTA = ", ";

    public static String bufferToString(StringBuffer valor) {
        String texto = valor == null ? EMPTY : valor.toString();

        return isNotBlank(texto) ? texto : null;
    }

    public static String join(Collection collection) {
        return StringUtils
                .join(collection, SEPARADOR_LISTA)
                .replaceAll("([,])\\s([^,]*)$", " e $2");
    }
}
