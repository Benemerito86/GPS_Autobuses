package paquetePrincipal;

import logica.GestionAutobuses;
import logica.GestionAutobuses2;

import java.util.Scanner;

public class Menu {

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int autobuses = 5;
        int tiempo = 60000;
        int ciclos = 15;
        boolean flag = false;
        int i = 1;

        do {
            System.out.println("Bienvenido a la simulacion de autobuses de la ruta " + i +
                    "\n1. Nº autobuses" +
                    "\n2. Nº de ciclos" +
                    "\n3. Duracion de cada ciclo" +
                    "\n4. Ejecutar simulacion" +
                    "\n5. Cambiar ruta" +
                    "\n6. Salir");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("Cuantos autobuses quieres simular? (por defecto 5)");
                    try {
                        autobuses = sc.nextInt();
                    } catch (Exception e) {
                        autobuses = 5;
                    }
                    System.out.println("Los autobuses seran " + autobuses);

                    break;
                case 3:
                    System.out.println("Cuanto quieres que dure 1 minuto? (por defecto 60000 milisegundos)");
                    try {
                        tiempo = sc.nextInt();
                    } catch (Exception e) {
                        tiempo = 60000;
                    }
                    System.out.println("el tiempo por minuto sera " + tiempo + " ms");
                    break;
                case 2:
                    System.out.println("Cuantos ciclos quieres simular? (por defecto 15");
                    try {
                        ciclos = sc.nextInt();
                    } catch (Exception e) {
                        ciclos = 15;
                    }
                    System.out.println("Los ciclos seran " + ciclos);
                    break;
                case 4:
                    System.out.println("4. Ejecutando Simulacion...");
                    if (i == 1) {
                        GestionAutobuses gestion = new GestionAutobuses(autobuses);
                        gestion.resetearFolder();
                        gestion.simularMovimientos(ciclos, tiempo);
                    } else {
                        GestionAutobuses2 gestion = new GestionAutobuses2(autobuses);
                        gestion.resetearFolder();
                        gestion.simularMovimientos(ciclos, tiempo);
                    }
                    flag = true;

                    break;
                case 5:
                    System.out.println("5. Cambiando ruta...");
                    if (i == 1) {
                        i = 2;
                    } else {
                        i = 1;
                    }
                    break;
                case 6:
                    System.out.println("5. Saliendo...");
                    flag = true;
                    break;
                default:
                    break;
            }
        } while (flag == false);
        sc.close();
        // VentanaPrincipal frame = new VentanaPrincipal();
        // frame.setVisible(true);
    }
}
