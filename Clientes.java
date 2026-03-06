import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Clientes {
  private int id;
  private String nombre;
  private String email;

  public Clientes(int id, String nombre, String email) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
  }

  public int getId() { return id; }
  public String getNombre() { return nombre; }
  public String getEmail() { return email; }

  public void setNombre(String nombre) { this.nombre = nombre; }
  public void setEmail(String email) { this.email = email; }

    public static void crearUsuario(Clientes cliente) throws IOException {
    FileWriter fw = new FileWriter("productos.csv");
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(cliente.toString());
    bw.newLine();
    bw.close();
}
  
  @Override
  public String toString() {
    return id + "," + nombre + "," + email;
  }
}