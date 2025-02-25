package proyecto02.practica02;

import proyecto.ExcepcionTransaccion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    GestionTransacciones gestion = new GestionTransacciones();
    private JPanel MainPanel;
    private JTextField UserTxt;
    private JPasswordField PasswordTxt;
    private JLabel UserLbl;
    private JLabel PasswordLbl;
    private JButton LoginBtn;
    private JButton CleanBtn;

    public Login() {
        //**Mostrar la ventana**
        setContentPane(MainPanel);
        setTitle("Iniciar Sesión");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200, 200);
        setVisible(true);

        //**Eventos de la interfaz**

        //**Evento de boton Iniciar Sesion**
        LoginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = UserTxt.getText();
                String password = PasswordTxt.getText();
                if (user.equals("Practica02") && password.equals("Secreto123")) {
                    setVisible(false);
                    GestionTransacciones ventanaGestion = new GestionTransacciones();
                    ventanaGestion.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Confirme sus credenciales");
                }
                
            }
        });

        //**Evento de boton Limpiar**
        CleanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserTxt.setText("");
                PasswordTxt.setText("");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
