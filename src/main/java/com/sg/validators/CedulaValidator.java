
package com.sg.validators;


public class CedulaValidator {

    public static boolean validarCedula(String cedula) {
        if (cedula == null || cedula.length() != 11) {
            return false;
        }
        
        int suma = 0;
        int digitoControl = Character.getNumericValue(cedula.charAt(10));
        
        for (int i = 0; i < 10; i++) {
            int valor = Character.getNumericValue(cedula.charAt(i));
            suma += (i % 2 == 0) ? valor * 2 : valor;
        }

        int mod = suma % 10;
        int validacion = (mod == 0) ? 0 : 10 - mod;

        return validacion == digitoControl;
    }

    // Método para validar RNC
    public static boolean validarRNC(String rnc) {
        if (rnc == null || rnc.length() < 9 || rnc.length() > 11) {
            return false;
        }
        
        // Se considera que un RNC válido comienza con "1" para personas físicas o "2" para jurídicas
        char inicio = rnc.charAt(0);
        if (inicio != '1' && inicio != '2') {
            return false;
        }

        // Valida la longitud (9 caracteres para personas físicas, 11 para jurídicas)
        if (rnc.length() == 9) {
            return rnc.matches("\\d{9}");
        } else if (rnc.length() == 11) {
            return rnc.matches("\\d{3}-\\d{7}-\\d{1}");
        }

        return false;
    }


}
