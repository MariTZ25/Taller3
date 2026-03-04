public class Pedidos {
  private int id;
  private int cliente_id;
  private int producto_id;
  private int cantidad;
  private String fecha;

  public Pedidos(int id, int cliente_id, int producto_id, int cantidad, String fecha ) {
    this.id = id;
    this.cliente_id = cliente_id;
    this.producto_id = producto_id;
    this.cantidad = cantidad;
    this.fecha = fecha;
  }

  public int getId() { return id; }
  public int getCliente_id() { return cliente_id; }
  public int getProducto_id() { return producto_id; }
  public int getCantidad() { return cantidad; }
   public String getFecha() { return fecha; }

  public void setFecha(String fecha) { this.fecha = fecha; }

  @Override
  public String toString() {
    return id + "," + cliente_id + "," + producto_id + "," + cantidad + "," + fecha;
  }
}
      