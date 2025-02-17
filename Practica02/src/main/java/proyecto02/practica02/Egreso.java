/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto02.practica02;

/**
 *
 * @author adryhd
 */
public class Egreso extends Transaccion {

    public Egreso(double monto, String descripcion, String fecha) {
        super(monto, descripcion, fecha);
    }

    @Override
    public double calcularImpuesto() {
        double deduccionGastos = 0.05;
        return monto * deduccionGastos;

    }

    @Override
    public String procesarTransaccion() {
        // Retorna el resultado como un String
        return "Procesando egreso:\n" +
               "Monto: " + monto + "\n" +
               "Descripción: " + descripcion + "\n" +
               "Fecha: " + fecha + "\n" +
               "Gastos adicionales aplicados: " + calcularImpuesto();
    }

}

