/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto02.practica02;


/**
 *
 * @author adryhd
 */
public class Ingreso extends Transaccion {

    public Ingreso(double monto, String descripcion, String fecha) {
        super(monto, descripcion, fecha);
    }

    @Override
    public double calcularImpuesto() {
        double tasaImpuesto = 0.10;
        return monto * tasaImpuesto;
    }

    @Override
    public String procesarTransaccion() {
        // Retorna el resultado como un String
        return "Procesando ingreso:\n" +
               "Monto: " + monto + "\n" +
               "Descripción: " + descripcion + "\n" +
               "Fecha: " + fecha + "\n" +
               "Impuesto aplicado: " + calcularImpuesto();
    }
   
}

