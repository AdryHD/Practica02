/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package proyecto02.practica02;

import proyecto.ExcepcionTransaccion;
import javax.swing.JFrame;
import proyecto.ExcepcionTransaccion;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author adryhd
 */
public class Practica02 {

 

    public static void main(String[] args) throws ExcepcionTransaccion {
        GestionTransacciones gestion = new GestionTransacciones();

        gestion.mostrarMenu();

    }
}
