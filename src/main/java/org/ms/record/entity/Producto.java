/*package org.ms.record.entity;

public class Producto {
    String nombre;
    double precio;
    int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: "+ nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Cantidad: " + cantidad);

    }

    public void aplicarDescuento(double porcentaje) {
        precio -= precio * (porcentaje / 100); // Aplica el descuento
    }

    public void vender(int cantidadVendida){
        if (cantidadVendida > cantidad) {
            System.out.println("No hay suficiente inventario para vender " + cantidadVendida + " unidades");
        } else {
            cantidad -= cantidadVendida; // Reduce la cantidad en inventario
        }
    }

    public void reponer(int cantidadReponer){
        cantidad += cantidadReponer; // Aumenta la cantidad en inventario
    }
}

public class Main {
    public static void main(String[] args) {
        Producto producto = new Producto("Laptop", 1500.00, 10);
        producto.mostrarInformacion();

        producto.aplicarDescuento(10);
        producto.mostrarInformacion();

        producto.vender(3);
        producto.mostrarInformacion();

        producto.vender(20);

        producto.reponer(5);
        producto.mostrarInformacion();
    }
}
*/