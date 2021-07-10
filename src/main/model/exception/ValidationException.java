/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model.exception;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author evand
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Map<String, String> erros = new HashMap<>();

    public ValidationException(String msg) {
        super(msg);
    }

    public Map<String, String> getErros() {
        return erros;
    }

    public void addErros(String fildName, String errorMessage) {
        erros.put(fildName, errorMessage);
    }

}
