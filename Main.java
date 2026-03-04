
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int op = 0;

        do {
            System.out.println("\nHola elija una funcion:");
            System.out.println("1. Ordenar Productos por Precio");
            System.out.println("2. Agregar un Nuevo Cliente");
            System.out.println("3. Calcular el Total de Ventas por Producto");
            System.out.println("4. Ver clientes que han realizado compras.");
            System.out.println("5. Salir");

            String s = read.nextLine().trim();
            if (s.isEmpty()) continue;

            try {
                op = Integer.parseInt(s);

                switch (op) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                op = 0;
            }
        } while (op != 5);

        read.close();
    }
}