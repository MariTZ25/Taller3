import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.*;

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
                        ordenarProductos();
                        break;
                    case 2:
                        crearCliente();

                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        System.out.println("Saliendo...");
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

    //COMIENZAN LOS MÉTODOS----------------------------------------------------------------------

//-----------------------------------------------------------------------------------------------
 public void cargarTodo() throws IOException {
  asegurarArchivo();
  data = leerUsuarios();
  construirIndices();
}

//------------------------------------------------------------------------------------------------
public boolean crear(Clientes cliente) throws IOException {
  cargarTodo();

  if (idxId.containsKey(nuevo.getId())) {
    System.out.println(" ID ya existe.");
    return false;
  }
  if (idxEmail.containsKey(norm(nuevo.getEmail()))) {
    System.out.println(" Email ya existe.");
    return false;
  }

  try (BufferedWriter bw = Files.newBufferedWriter(
      archivo, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
    bw.write(nuevo.toString());
    bw.newLine();
  }

  cargarTodo();
  return true;
}
      

    public static void ordenarProductos(){
        ArrayList<Productos> lista = new ArrayList<>();

        Scanner sc = new Scanner(new File("productos.csv"));
        if (sc.hasNextLine())
            sc.nextLine();

        while (sc.hasNextLine()) {
            String linea = sc.nextLine().trim();
            if (linea.isEmpty())
                continue;

            String[] dato = linea.split(",", -1);

            int id = Integer.parseInt(dato[0].trim());
            String nombre = dato[1].trim();
            String categoria = dato[2].trim();
            long precio = Long.parseLong(dato[3].trim());
            int stock = Integer.parseInt(dato[4].trim());

        

            lista.add(new Productos(id, nombre, categoria, precio, stock));
        }
        sc.close();

        //Ordenar de menor a mayor

        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - 1 - i; j++) {
                if (lista.get(j).getPrecio() > lista.get(j + 1).getPrecio()) {
                    Productos temp = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, temp);
                }
            }
        }

        PrintWriter pw = new PrintWriter(new FileWriter("productos.csv"));
        pw.println("id,nombre,categoria,precio,stock");
        for (Productos prds : lista) {
            pw.println(prds.id + "," + prds.nombre + "," + prds.categoria + "," + prds.precio + "," + prds.stock);
        }
        pw.close();

        System.out.println("Productos ordenados por precio (asc):");
        for (Productos prds : lista) {
            System.out.println("Producto: " + prds.nombre + ", precio: $" + prds.precio + " cantidad en stock: " + prds.stock);
        }
    }
    }
}