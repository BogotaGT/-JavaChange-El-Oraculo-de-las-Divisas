package com.alura.conversor;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Principal {
    private static final String OPCION_SALIR = "SALIR";
    private static List<String> historial = new ArrayList<>();

    public static void main(String[] args) {
        mostrarTitulo();

        Scanner scanner = new Scanner(System.in);
        ConvertidorMoneda convertidor = new ConvertidorMoneda();

        System.out.println("¡Bienvenido a JavaChange: El Oráculo de las Divisas!");

        boolean continuarPrograma = true;
        while (continuarPrograma) {
            // Solicitar moneda de origen
            String monedaOrigen = solicitarMoneda(scanner, "origen");
            if (OPCION_SALIR.equals(monedaOrigen)) {
                continuarPrograma = false;
                continue;
            }

            // Solicitar cantidad a convertir
            double cantidad = solicitarCantidad(scanner);

            // Solicitar moneda de destino
            String monedaDestino = solicitarMoneda(scanner, "destino");
            if (OPCION_SALIR.equals(monedaDestino)) {
                continuarPrograma = false;
                continue;
            }

            // Realizar conversión
            try {
                String resultado = convertidor.convertir(monedaOrigen, monedaDestino, cantidad);
                System.out.println("\n" + dibujarMarco(resultado));

                // Agregar al historial
                historial.add(resultado);

                // Menú de opciones post-conversión
                continuarPrograma = mostrarMenuPostConversion(scanner);
            } catch (IllegalArgumentException e) {
                System.out.println(dibujarMarco("Error: " + e.getMessage()));
            } catch (Exception e) {
                System.out.println(dibujarMarco("Error inesperado: " + e.getMessage()));
            }

            if (continuarPrograma) {
                System.out.println("\n" + dibujarLinea() + "\n");
            }
        }

        System.out.println("Gracias por usar JavaChange: El Oráculo de las Divisas. ¡Hasta pronto!");
        scanner.close();
    }

    private static void mostrarTitulo() {
        System.out.println("""
            ██╗ █████╗ ██╗   ██╗ █████╗  ██████╗██╗  ██╗ █████╗ ███╗   ██╗ ██████╗ ███████╗
            ██║██╔══██╗██║   ██║██╔══██╗██╔════╝██║  ██║██╔══██╗████╗  ██║██╔════╝ ██╔════╝
            ██║███████║██║   ██║███████║██║     ███████║███████║██╔██╗ ██║██║  ███╗█████╗  
        ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║██║     ██╔══██║██╔══██║██║╚██╗██║██║   ██║██╔══╝  
        ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║╚██████╗██║  ██║██║  ██║██║ ╚████║╚██████╔╝███████╗
         ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ╚══════╝
                EL ORÁCULO DE LAS DIVISAS
        """);
    }

    private static String solicitarMoneda(Scanner scanner, String tipo) {
        while (true) {
            System.out.println("\nSeleccione la moneda de " + tipo + ":");
            System.out.println("1) USD - Dólar Estadounidense   2) EUR - Euro");
            System.out.println("3) GBP - Libra Esterlina        4) JPY - Yen Japonés");
            System.out.println("5) ARS - Peso Argentino         6) BRL - Real Brasileño");
            System.out.println("7) CLP - Peso Chileno           8) COP - Peso Colombiano");
            System.out.println("9) MXN - Peso Mexicano          10) CAD - Dólar Canadiense");
            System.out.println("11) Salir");
            System.out.print("Elija una opción: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                if (opcion == 11) return OPCION_SALIR;
                if (opcion < 1 || opcion > 10) throw new IllegalArgumentException("Opción no válida");

                String[] codigosMonedas = {"USD", "EUR", "GBP", "JPY", "ARS", "BRL", "CLP", "COP", "MXN", "CAD"};
                String[] nombresMonedas = {"Dólar Estadounidense", "Euro", "Libra Esterlina", "Yen Japonés",
                        "Peso Argentino", "Real Brasileño", "Peso Chileno", "Peso Colombiano",
                        "Peso Mexicano", "Dólar Canadiense"};
                String monedaSeleccionada = codigosMonedas[opcion - 1];
                System.out.println("Ha seleccionado: " + monedaSeleccionada + " - " + nombresMonedas[opcion - 1]);
                return monedaSeleccionada;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static double solicitarCantidad(Scanner scanner) {
        while (true) {
            System.out.println("Ingrese la cantidad que desea convertir (use punto como separador decimal, sin separadores de miles):");
            System.out.println("Ejemplo: Para ochocientos mil pesos, ingrese 800000.00");
            String input = scanner.nextLine().replace(",", ".");
            try {
                NumberFormat format = NumberFormat.getInstance(Locale.US);
                Number number = format.parse(input);
                return number.doubleValue();
            } catch (ParseException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    private static boolean mostrarMenuPostConversion(Scanner scanner) {
        while (true) {
            System.out.println("\n¿Qué desea hacer ahora?");
            System.out.println("1. Convertir otra cantidad");
            System.out.println("2. Ver historial de conversiones");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");

            String opcion = scanner.nextLine().trim();
            switch (opcion) {
                case "1":
                    return true;
                case "2":
                    mostrarHistorial();
                    break;
                case "3":
                    return false;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static String dibujarMarco(String contenido) {
        String[] lineas = contenido.split("\n");
        int maxLength = 0;
        for (String linea : lineas) {
            maxLength = Math.max(maxLength, linea.length());
        }

        StringBuilder sb = new StringBuilder();
        sb.append("╔").append("═".repeat(maxLength + 2)).append("╗\n");

        for (String linea : lineas) {
            sb.append("║ ").append(linea)
                    .append(" ".repeat(maxLength - linea.length()))
                    .append(" ║\n");
        }

        sb.append("╚").append("═".repeat(maxLength + 2)).append("╝");
        return sb.toString();
    }

    private static String dibujarLinea() {
        return "═".repeat(50);
    }

    private static void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("No se han realizado conversiones.");
        } else {
            System.out.println("\nHistorial de conversiones:");
            for (int i = 0; i < historial.size(); i++) {
                System.out.println((i + 1) + ". " + historial.get(i));
            }
        }
    }
}