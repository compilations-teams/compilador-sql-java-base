/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josyd
 */
import javax.swing.*;
import java.awt.*;

public class LoginSwing extends JFrame {

    public LoginSwing() {
        // Configuración de la ventana principal
        setTitle("Sistema - Login");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con layout flexible
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiquetas y campos
        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField(15);

        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField(15);

        JButton btnLogin = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");

        // Estilos profesionales
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));

        btnLogin.setBackground(new Color(0, 123, 255));
        btnLogin.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(220, 53, 69));
        btnCancelar.setForeground(Color.WHITE);

        // Posicionamiento en el panel
        gbc.gridx = 0; gbc.gridy = 0; panel.add(lblUsuario, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(txtUsuario, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panel.add(lblPassword, gbc);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(txtPassword, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panel.add(btnLogin, gbc);
        gbc.gridx = 1; gbc.gridy = 2; panel.add(btnCancelar, gbc);

        // Acciones de botones
        btnLogin.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String password = new String(txtPassword.getPassword());

            if ("admin".equals(usuario) && "1234".equals(password)) {
                JOptionPane.showMessageDialog(this, "Login exitoso. Bienvenido " + usuario + "!");
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales inválidas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> System.exit(0));

        // Agregar panel a la ventana
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginSwing().setVisible(true));
    }
}

