package proyecto02.practica02;

import proyecto.ExcepcionTransaccion;
import proyecto.ExcepcionTransaccion;

import javax.swing.*;
import java.util.ArrayList;

public class GestionTransacciones {
    private ArrayList<Transaccion> transacciones;
    private double capital;

    public GestionTransacciones() {
        transacciones = new ArrayList<>();
        capital = 1500; //capital inicial de la compañia
    }

    public void agregarTransaccion() throws ExcepcionTransaccion {
        String[] opciones = {"Ingreso", "Egreso"};
        String tipo = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de transacción", "Gestion de Transacciones", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (tipo == null) return; //si el usuario no quiere hacer la transaccion, se sale

        try{
            double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto:"));
            String descripcion = JOptionPane.showInputDialog("Ingrese la descripción:");
            String fecha = JOptionPane.showInputDialog("Ingrese la fecha:");

            Transaccion nuevaTransaccion;
            if (tipo.equals("Ingreso")) {
                nuevaTransaccion = new Ingreso(monto, descripcion, fecha);
                capital += monto; //representa la suma al capital
            } else{
                nuevaTransaccion = new Egreso(monto, descripcion, fecha);
                capital -= monto; //representa la resta al capital
            }
            transacciones.add(nuevaTransaccion);

            JOptionPane.showMessageDialog(null, "Transacción agregada con éxito.");
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el monto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void eliminarTransaccion() {
        if(transacciones.isEmpty()) {
            JOptionPane.showMessageDialog(null,"No hay transacciones existentes.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        for(int i = 0; i < transacciones.size(); i++) {
            Transaccion t = transacciones.get(i);
            lista.append(i + 1).append(". ")
                    .append("Monto: $").append(t.getMonto())
                    .append("Fecha: ").append(t.getFecha())
                    .append("Descripción: ").append(t.getDescripcion())
                    .append("\n");
        }

        try{
            String input = JOptionPane.showInputDialog(lista.toString());
            if (input == null) return; // Si el usuario cancela, salir.

            int index = Integer.parseInt(input) - 1;

            if (index >= 0 && index < transacciones.size()) {
                Transaccion t = transacciones.remove(index);

                if (t instanceof Ingreso) {
                    capital -= t.getMonto(); // Restar el ingreso eliminado
                } else {
                    capital += t.getMonto(); // Reintegrar el egreso eliminado
                }

                JOptionPane.showMessageDialog(null, "Transacción eliminada con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "Selección inválida.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void mostrarTransacciones() {
        if (transacciones.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay transacciones registradas.");
            return;
        }

        StringBuilder lista = new StringBuilder("Lista de Transacciones \n");
        for (Transaccion t : transacciones){
            lista.append("Monto: $").append(t.getMonto())
                    .append("Fecha: ").append(t.getFecha())
                    .append("Descripción: ").append(t.getDescripcion())
                    .append("\n");

        }
        JOptionPane.showMessageDialog(null, lista.toString(), "Transacciones", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarMenu() throws ExcepcionTransaccion {
        String[] opciones = {"Agregar Transacción", "Eliminar Transacción","Mostrar Transacciones", "Salir"};
        while (true) {
            String seleccion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Menu", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (seleccion == null || seleccion.equals("Salir")) break;

            switch (seleccion) {
                case "Agregar Transacción":
                    agregarTransaccion();
                    break;
                case "Eliminar Transacción":
                    eliminarTransaccion();
                    break;
                case "Mostrar Transacciones":
                    mostrarTransacciones();
                    break;
            }
        }
    }

}