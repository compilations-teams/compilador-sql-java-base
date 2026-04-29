package org.umg.compilerjava.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
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
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        JButton runButton = new JButton("Compilar SQL");
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                compileQuery();
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout(0, 8));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Entrada SQL"));
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createTitledBorder("Salida del compilador"));
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, inputPanel, outputPanel);
        splitPane.setResizeWeight(0.42);
        splitPane.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JPanel actionsPanel = new JPanel(new BorderLayout());
        actionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));
        actionsPanel.add(new JLabel("Modo Swing sin dependencias externas (sin JavaFX)."), BorderLayout.CENTER);
        actionsPanel.add(runButton, BorderLayout.EAST);

        add(splitPane, BorderLayout.CENTER);
        add(actionsPanel, BorderLayout.SOUTH);
    }

    private void compileQuery() {
        CompilerReport report = compilerFacade.compile(inputArea.getText());
        outputArea.setText(report.toMultilineString());
    }
}
