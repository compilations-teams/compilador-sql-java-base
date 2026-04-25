package org.umg.compilerjava.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.umg.compilerjava.auth.AuthResult;
import org.umg.compilerjava.auth.AuthService;

/**
 * Login base en Swing para orientar la futura modernización visual.
 */
public final class LoginFrame extends JFrame {

    private final AuthService authService;
    private final ResultFrame resultFrame;
    private final JTextField emailField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();

    public LoginFrame(AuthService authService, ResultFrame resultFrame) {
        this.authService = authService;
        this.resultFrame = resultFrame;
        setTitle("Migration Hub Login");
        setSize(520, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildUi();
    }

    private void buildUi() {
        JPanel formPanel = new JPanel(new GridLayout(5, 1, 8, 8));
        formPanel.add(new JLabel("Work Email"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Password"));
        formPanel.add(passwordField);

        JButton loginButton = new JButton("Sign In");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                authenticate();
            }
        });
        formPanel.add(loginButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private void authenticate() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El correo es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!email.contains("@")) {
            JOptionPane.showMessageDialog(this, "Correo inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La contraseña es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.length() < 4) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 4 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AuthResult result = authService.authenticate(email, password);

        if (!result.isSuccess()) {
            JOptionPane.showMessageDialog(this, result.getMessage(), "Login inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        resultFrame.setRole(result.getRole());
        resultFrame.setVisible(true);
        setVisible(false);
        dispose();
    }
}
