package org.umg.compilerjava.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.umg.compilerjava.auth.AuthResult;
import org.umg.compilerjava.auth.AuthService;

/**
 * Pantalla de inicio de sesión del compilador SQL.
 *
 * T-01 LoginView - UI:
 * - campo de correo
 * - campo de contraseña
 * - botón de ingreso
 */
public final class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private final AuthService authService;
    private final ResultFrame resultFrame;

    private final JTextField emailField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton loginButton = new JButton("Ingresar");

    public LoginFrame(AuthService authService, ResultFrame resultFrame) {
        this.authService = authService;
        this.resultFrame = resultFrame;

        setTitle("Compilador SQL - Login");
        setMinimumSize(new Dimension(520, 360));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildUi();
    }

    private void buildUi() {
        JPanel rootPanel = new JPanel(new BorderLayout(0, 18));
        rootPanel.setBorder(BorderFactory.createEmptyBorder(28, 40, 28, 40));
        rootPanel.setBackground(Color.WHITE);

        rootPanel.add(buildHeaderPanel(), BorderLayout.NORTH);
        rootPanel.add(buildFormPanel(), BorderLayout.CENTER);

        add(rootPanel, BorderLayout.CENTER);
        getRootPane().setDefaultButton(loginButton);
    }

    private JPanel buildHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout(0, 6));
        headerPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Compilador SQL", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        JLabel subtitleLabel = new JLabel("Acceso al entorno de análisis", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(90, 90, 90));

        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);
        return headerPanel;
    }

    private JPanel buildFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        emailField.setToolTipText("Ingrese su correo de usuario");
        passwordField.setToolTipText("Ingrese su contraseña");

        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                authenticate();
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(6, 0, 6, 0);
        constraints.gridx = 0;
        constraints.weightx = 1.0;

        addFormRow(formPanel, constraints, 0, "Correo", emailField);
        addFormRow(formPanel, constraints, 2, "Contraseña", passwordField);

        constraints.gridy = 4;
        constraints.insets = new Insets(18, 0, 0, 0);
        formPanel.add(loginButton, constraints);

        return formPanel;
    }

    private void addFormRow(JPanel panel, GridBagConstraints constraints, int row, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));

        constraints.gridy = row;
        constraints.insets = new Insets(6, 0, 4, 0);
        panel.add(label, constraints);

        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(320, 34));

        constraints.gridy = row + 1;
        constraints.insets = new Insets(0, 0, 8, 0);
        panel.add(field, constraints);
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