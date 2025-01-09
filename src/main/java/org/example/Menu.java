package org.example;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Convertidor convertidor = new Convertidor();
        String opcion = "";

        while (!opcion.equalsIgnoreCase("S")) {
            try {
                // Mostrar el menú de moneda base
                System.out.println("""
                        === Convertidor de Monedas === 
                        Ingrese la moneda base
                        USD
                        EUR
                        CRC
                        MXN
                        Si desea salir ingrese 'S' """);
                String monedaBase = teclado.nextLine().toUpperCase();

                // Salida si el usuario ingresa 'S'
                if (monedaBase.equalsIgnoreCase("S")) {
                    opcion = "S";
                    break;
                }

                // Mostrar el menú de moneda a convertir
                System.out.println("""
                        === Convertidor de Monedas === 
                        Ingrese la moneda a convertir
                        USD
                        EUR
                        CRC
                        MXN
                        Si desea salir ingrese 'S' """);
                String monedaAConvertir = teclado.nextLine().toUpperCase();

                // Salida si el usuario ingresa 'S'
                if (monedaAConvertir.equalsIgnoreCase("S")) {
                    opcion = "S";
                    break;
                }

                // Solicitar el monto a convertir
                System.out.println("Ingrese la cantidad a convertir: ");
                double monto = teclado.nextDouble();
                teclado.nextLine();

                // Obtener la tasa de conversión
                double tasa = convertidor.obtenerTasaConversion(monedaBase, monedaAConvertir);

                // Realizar la conversión
                double montoConvertido = convertidor.convertir(monto, tasa);

                // Mostrar resultados
                System.out.printf("Tasa de cambio de %s a %s: %.2f%n", monedaBase, monedaAConvertir, tasa);
                System.out.printf("Monto convertido: %.2f %s%n", montoConvertido, monedaAConvertir);

                System.out.println("¿Desea convertir otra moneda? Para seguir marque 'R' para salir 'S'");
                String seguir= teclado.nextLine();
                if (seguir.equalsIgnoreCase("S")) {
                    opcion = "S";
                    break;
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                teclado.nextLine();
            }
        }

        // Mensaje de despedida
        System.out.println("""
                Gracias por ingresar al convertidor de monedas.
                Te esperamos nuevamente.
                """);
    }
}
