
public class Productos {
  private int id;
  private String nombre;
  private String categoria;
  private float precio;
  private int stock;

  public Usuario(int id, String nombre, String categoria, float precio, int stock) {
    this.id = id;
    this.nombre = nombre;
    this.categoria = categoria;
    this.precio = precio;
    this.stock = stock;
  }

  public int getId() { return id; }
  public String getNombre() { return nombre; }
  public String getCategoria() { return categoria; }
  public float getPrecio() { return precio; }
  public int getStock() { return stock; }

  public void setNombre(String nombre) { this.nombre = nombre; }
  public void setCategoria(String categoria) { this.categoria = categoria; }
  @Override
  public String toString() {
    return id + "," + nombre + "," + categoria + "," + precio + "," + stock;
  }
}