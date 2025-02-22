package proyecto02.practica02;

import proyecto.ExcepcionTransaccion;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestionTransacciones extends JFrame {
    private ArrayList<Transaccion> transacciones;
    private double capital;

    private JLabel labCapital;
    private JTextArea txtTranscciones;
    private JTextField txtMonto, txtDescripcion, txtFecha;

    public GestionTransacciones(){
        transacciones = new ArrayList<>();
        capital = 1500; //capital inicial de la compañia

        //Ventana principal

        setTitle("Gestor de Transaccoiones");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Panel superior donde se muestra el capital

        JPanel panelSup = new JPanel();
        labCapital = new JLabel("Capital: $" +  capital);
        labCapital.setFont(new Font("Arial", Font.BOLD, 14));
        panelSup.add(labCapital);
        add(panelSup, BorderLayout.NORTH);

        //Panel central donde se muestra la lista de transaccioens

        txtTranscciones = new JTextArea();
        txtTranscciones.setEditable(false);
        add(new JScrollPane(txtTranscciones), BorderLayout.CENTER);

        //Panel inferior donde se encuentran los botones

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3));

        JButton botAgregar = new JButton("Agregar Transacción");
        JButton botEliminar = new JButton("Eliminar Transacción");
        JButton botBaibai = new JButton("Salir");

        botAgregar.addActionListener(e -> agregarTransaccion());
        botEliminar.addActionListener(e -> eliminarTransaccion());
        botBaibai.addActionListener(e -> System.exit(0));

        panelBotones.add(botAgregar);
        panelBotones.add(botEliminar);
        panelBotones.add(botBaibai);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void agregarTransaccion(){
        //ventana para añadir transacciones
        JDialog nuevITO = new JDialog(this, "Nueva Transacción", true);
        nuevITO.setSize(300,250);
        nuevITO.setLayout(new GridLayout(5,2));

        JLabel labTipoTr = new JLabel("Tipo de transacción:");
        String [] opciones = {"Ingreso", "Egreso"};
        JComboBox<String> boxTipo = new JComboBox<>(opciones);

        JLabel labMonto = new JLabel("Monto:");
        txtMonto = new JTextField();

        JLabel labDescripcion = new JLabel("Descripción:");
        txtDescripcion = new JTextField();

        JLabel labFecha = new JLabel("Fecha:");
        txtFecha = new JTextField();

        JButton botConfirmar = new JButton("Confirmar");
        botConfirmar.addActionListener(e -> {
            try {
                double monto = Double.parseDouble(txtMonto.getText());
                String descripcion = txtDescripcion.getText();
                String fecha = txtFecha.getText();
                String tipo = (String) boxTipo.getSelectedItem();

                if (tipo.equals("Egreso") && monto > capital) {
                    JOptionPane.showMessageDialog(this, "No hay suficiente capital para completar su transacción", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Transaccion newTransaccion;
                if (tipo.equals("Ingreso")) {
                    newTransaccion = new Ingreso(monto, descripcion, fecha);
                    capital += monto;
                } else{
                    newTransaccion = new Egreso(monto, descripcion, fecha);
                    capital -= monto;
                }
                transacciones.add(newTransaccion);
                actualizarInterfaz();
                nuevITO.dispose();
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        nuevITO.add(labTipoTr);
        nuevITO.add(boxTipo);
        nuevITO.add(labMonto);
        nuevITO.add(txtMonto);
        nuevITO.add(labDescripcion);
        nuevITO.add(txtDescripcion);
        nuevITO.add(labFecha);
        nuevITO.add(txtFecha);
        nuevITO.add(new JLabel());
        nuevITO.add(botConfirmar);

        nuevITO.setVisible(true);

    }

    private void eliminarTransaccion(){
        if (transacciones.isEmpty()){
            JOptionPane.showMessageDialog(this, "No existen transacciones registradas.");
            return;
        }

        String[] opciones = new String[transacciones.size()];
        for (int i = 0; i < transacciones.size(); i++) {
            opciones[i] = transacciones.get(i).getDescripcion() + "-$" + transacciones.get(i).getMonto();
        }

        String eleccion = (String) JOptionPane.showInputDialog(this, "Seleccione la transacción que desea eliminar:","Eliminar Transacción", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (eleccion == null) return;

        for (int i = 0; i < transacciones.size(); i++) {
            if (eleccion.contains(transacciones.get(i).getDescripcion())) {
                Transaccion t = transacciones.remove(i);
                if (t instanceof Ingreso){
                    capital -= t.getMonto();
                } else {
                    capital += t.getMonto();
                }
                break;
            }
        }
        actualizarInterfaz();

    }
    private void actualizarInterfaz() {
        labCapital.setText("Capital: $ " + capital);
        txtTranscciones.setText("");
        for (Transaccion t : transacciones) {
            txtTranscciones.append("Monto: $" + t.getMonto() + "\n");
            txtTranscciones.append("Fecha: " + t.getFecha() + "\n");
            txtTranscciones.append("Descripción: " + t.getDescripcion() + "\n\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            GestionTransacciones frame = new GestionTransacciones();
            frame.setVisible(true);
        });
    }

}