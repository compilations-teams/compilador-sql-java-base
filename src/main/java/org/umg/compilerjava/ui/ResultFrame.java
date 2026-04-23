package org.umg.compilerjava.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.umg.compilerjava.compiler.CompilerFacade;
import org.umg.compilerjava.compiler.CompilerReport;

/**
 * Ventana simple para ejecutar consultas SQL y mostrar el resultado del análisis.
 */
public final class ResultFrame extends JFrame {

    private final CompilerFacade compilerFacade;
    private final JTextArea inputArea = new JTextArea();
    private final JTextArea outputArea = new JTextArea();

    public ResultFrame(CompilerFacade compilerFacade) {
        this.compilerFacade = compilerFacade;
        setTitle("SQL Compiler Java Base");
        setSize(820, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildUi();
    }

    public void setRole(String role) {
        setTitle("SQL Compiler Java Base - " + role);
    }

    private void buildUi() {
        inputArea.setText("SELECT nombre FROM usuarios WHERE edad > 18;");
        outputArea.setEditable(false);

        JButton runButton = new JButton("Compilar SQL");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                compileQuery();
            }
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        topPanel.add(runButton, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
    }

    private void compileQuery() {
        CompilerReport report = compilerFacade.compile(inputArea.getText());
        outputArea.setText(report.toMultilineString());
    }
}
