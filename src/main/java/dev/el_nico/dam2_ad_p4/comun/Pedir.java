package dev.el_nico.dam2_ad_p4.comun;

import org.apache.commons.lang3.StringUtils;

/** cosas input */
public class Pedir {
    private Pedir() {}

    /** 
     * Devuelve un entero entre 0 y max (incluido)
     */
    public static int orden(int max) {
        if (max < 1) {
            throw new IllegalArgumentException("Max no puede ser menor que 1.");
        }
        boolean input_ok = false;
        int orden = 0;
        do {
            try {
                orden = Pedir.entero("Orden", false);
                if (orden < 0 || orden > max) {
                    System.out.println("Orden no válida.");
                } else {
                    input_ok = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un número del 0 al " + max + ".");
            }
        } while (!input_ok);

        return orden;
    }

    /**
     * Devuelve un string que puede ser null o no según el parámetro booleano.
     */
    public static String cadena(String msj, boolean puedeEstarVacio) {
        String s;
        boolean error = false;
        do {
            if (error) System.out.println("El string no puede estar vacío.");
            System.out.print(msj + " (string): ");
            s = System.console().readLine();
        } while (puedeEstarVacio ? false : (error = StringUtils.isBlank(s)));
        return StringUtils.isBlank(s) ? null : s.trim();
    }

    /**
     * Pide un entero que puede ser null o no según el parámetro booleano.
     */
    public static Integer entero(String msj, boolean puedeSerNull) {
        while (true) {
            System.out.print(msj + " (entero): ");
            int entero;
            try {
                String s = System.console().readLine();
                if (puedeSerNull && StringUtils.isBlank(s)) {
                    return null;
                } else {
                    entero = Integer.parseInt(s);
                    return entero;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un entero.");
            }
        }
    }

    /** 
     * Pide un double. si el string proporcionado está en blanco,
     * devuelve null. Si no, intenta parsearlo y devolverlo.
     */
    public static Double decimal(String msj) {
        while (true) {
            System.out.print(msj + " (double): ");
            double comaflot;
            try {
                String s = System.console().readLine();
                if (StringUtils.isBlank(s)) {
                    return null;
                } else {
                    comaflot = Double.parseDouble(s);
                    return comaflot;
                }
            } catch (NumberFormatException e) {
                System.out.println("Eso no es un double.");
            }
        }
    }
}
