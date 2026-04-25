package org.umg.compilerjava;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.SwingUtilities;
import org.umg.compilerjava.compiler.CompilerFacade;
import org.umg.compilerjava.compiler.CompilerReport;

/**
 * Punto de entrada de la aplicación Java.
 *
 * <p>Si recibe una ruta de archivo SQL, ejecuta el compilador en modo CLI. Si no recibe
 * argumentos y el entorno soporta interfaz gráfica, abre la ventana de login.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            runCli(args[0]);
            return;
        }

        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Entorno sin interfaz gráfica detectado.");
            System.out.println("Usá: java -cp build/classes org.umg.compilerjava.Main examples/query1.sql");
            return;
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                openGui();
            }
        });
    }

    private static void runCli(String path) {
        try {
            String sql = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            CompilerReport report = new CompilerFacade().compile(sql);
            System.out.println(report.toMultilineString());
        } catch (IOException ex) {
            System.err.println("No fue posible leer el archivo: " + path);
            System.err.println(ex.getMessage());
        }
    }

    private static void openGui() {
        new MainController().start();
    }
}
