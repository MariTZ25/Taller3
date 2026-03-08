import java.io.*;
import java.util.*;

public class Main {
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        do {
            System.out.println("\nHola elija una funcion:");
            System.out.println("1. Ordenar Productos por Precio");
            System.out.println("2. Agregar un Nuevo Cliente");
            System.out.println("3. Calcular el Total de Ventas por Producto");
            System.out.println("4. Ver clientes que han realizado compras.");
            System.out.println("5. Salir");

            String s = sc.nextLine().trim();
            if (s.isEmpty()) continue;

            try {
                op = Integer.parseInt(s);

                switch (op) {
                    case 1:
                        ordenarProductos();
                        break;
                    case 2:
                        System.out.println("Insertar datos del cliente");
                        crear(sc);
                        break;
                    case 3:
                        calcular();
                        break;
                    case 4:
                        verClientesCompras();
                        break;
                    case 5:
                        System.out.println("Gracias por utilizarme <3");
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                op = 0;
            }
        } while (op != 5);

        sc.close();
    }

    //-----------------------------------------------------------------------------------------------

    public static void crear(Scanner sc) throws IOException {

        File files = new File("clientes.csv");

        if (!files.exists()) {
            PrintWriter pw = new PrintWriter(new FileWriter("clientes.csv"));
            pw.println("id,nombre,email");
            pw.close();
        }

        int maxId = 0;

        Scanner leerF = new Scanner(new File("clientes.csv"));

        if (leerF.hasNextLine())
            leerF.nextLine();

        while (leerF.hasNextLine()) {

            String linea = leerF.nextLine().trim();

            if (linea.isEmpty())
                continue;

            String[] datos = linea.split(",", -1);

            int id = Integer.parseInt(datos[0].trim());

            if (id > maxId)
                maxId = id;
        }

        leerF.close();

        int nuevoId = maxId + 1;

        System.out.print("Ingrese el nombre: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Ingrese el email: ");
        String email = sc.nextLine().trim();

        ArrayList<String> lineas = new ArrayList<>();

        Scanner sccsv = new Scanner(new File("clientes.csv"));

        while (sccsv.hasNextLine()) {
            lineas.add(sccsv.nextLine());
        }

        sccsv.close();

        PrintWriter pw = new PrintWriter(new FileWriter("clientes.csv"));

        for (String line : lineas)
            pw.println(line);

        pw.println(nuevoId + "," + nombre + "," + email);

        pw.close();

        System.out.println("Cliente agregado con ID: " + nuevoId);
    }

    //-----------------------------------------------------------------------------------------------

    public static void ordenarProductos() throws IOException {

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
            long precio = (long) Double.parseDouble(dato[3].trim());
            int stock = Integer.parseInt(dato[4].trim());

            lista.add(new Productos(id, nombre, categoria, precio, stock));
        }

        sc.close();

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
            pw.println(prds.getId() + "," + prds.getNombre() + "," + prds.getCategoria() + "," + prds.getPrecio() + "," + prds.getStock());
        }

        pw.close();

        System.out.println("Productos ordenados por precio (asc):");

        for (Productos prds : lista) {
            System.out.println("Producto: " + prds.getNombre() + ", precio: $" + prds.getPrecio() + " cantidad en stock: " + prds.getStock());
        }
    }

    //-----------------------------------------------------------------------------------------------

    public static void calcular() throws IOException {

        ArrayList<Productos> prod = new ArrayList<>();
        ArrayList<Pedidos> ped = new ArrayList<>();
        ArrayList<TotalVentas> total = new ArrayList<>();

        Scanner pr = new Scanner(new File("productos.csv"));

        if (pr.hasNextLine())
            pr.nextLine();

        while (pr.hasNextLine()) {

            String l = pr.nextLine().trim();

            if (l.isEmpty())
                continue;

            String[] dato = l.split(",", -1);

            prod.add(new Productos(
                    Integer.parseInt(dato[0].trim()),
                    dato[1].trim(),
                    dato[2].trim(),
                    (long) Double.parseDouble(dato[3].trim()),
                    Integer.parseInt(dato[4].trim())));
        }

        pr.close();

        Scanner pd = new Scanner(new File("pedidos.csv"));


        while (pd.hasNextLine()) {

            String l = pd.nextLine().trim();

            if (l.isEmpty())
                continue;

            String[] dato = l.split(",", -1);

            ped.add(new Pedidos(
                    Integer.parseInt(dato[0].trim()),
                    Integer.parseInt(dato[1].trim()),
                    Integer.parseInt(dato[2].trim()),
                    Integer.parseInt(dato[3].trim()),
                    dato[4].trim()));
        }

        pd.close();

        for (Productos p : prod) {

            float resultado = 0;

            for (Pedidos pds : ped) {

                if (pds.getProducto_id() == p.getId()) {

                    resultado += pds.getCantidad() * p.getPrecio();
                }
            }

            total.add(new TotalVentas(p.getId(), p.getNombre(), resultado));
        }

        for (int i = 0; i < total.size() - 1; i++) {
            for (int j = 0; j < total.size() - 1 - i; j++) {

                if (total.get(j).getTotal() < total.get(j + 1).getTotal()) {

                    TotalVentas temporal = total.get(j);
                    total.set(j, total.get(j + 1));
                    total.set(j + 1, temporal);
                }
            }
        }

        PrintWriter pw = new PrintWriter(new FileWriter("total_ventas.csv"));

        pw.println("producto_id,nombre_producto,total");

        for (TotalVentas v : total) {
            pw.println(v.getProducto_id() + "," + v.getNombre_producto() + "," + v.getTotal());
        }

        pw.close();

        System.out.println("Total ventas por producto:");

        for (TotalVentas v : total) {
            System.out.println("Producto: " + v.getNombre_producto() + " A pagar:" + v.getTotal());
        }
    }

    //Ver los clientes que ya realizaron compras------------------------------------------------------
    public static void verClientesCompras() throws IOException {

    ArrayList<Clientes> clientes = new ArrayList<>();
    ArrayList<Pedidos> pedidos = new ArrayList<>();

    Scanner cl = new Scanner(new File("clientes.csv"));

    while (cl.hasNextLine()) {

        String l = cl.nextLine().trim();

        if (l.isEmpty())
            continue;

        String[] dato = l.split(",", -1);

        clientes.add(new Clientes(
                Integer.parseInt(dato[0].trim()),
                dato[1].trim(),
                dato[2].trim()));
    }

    cl.close();

    Scanner pd = new Scanner(new File("pedidos.csv"));

    while (pd.hasNextLine()) {

        String l = pd.nextLine().trim();

        if (l.isEmpty())
            continue;

        String[] dato = l.split(",", -1);

        pedidos.add(new Pedidos(
                Integer.parseInt(dato[0].trim()),
                Integer.parseInt(dato[1].trim()),
                Integer.parseInt(dato[2].trim()),
                Integer.parseInt(dato[3].trim()),
                dato[4].trim()));
    }

    pd.close();

    System.out.println("Clientes que ya realizaron compras:");

    for (Clientes c : clientes) {

        for (Pedidos p : pedidos) {

            if (p.getCliente_id() == c.getId()) {

                System.out.println("Cliente: " + c.getNombre() + " Correo electrónico: " + c.getEmail());
                break;
            }
        }
    }

    }
}