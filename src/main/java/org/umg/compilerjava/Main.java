package org.umg.compilerjava;

import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.SwingUtilities;
import org.umg.compilerjava.auth.InMemoryAuthService;
import org.umg.compilerjava.compiler.CompilerFacade;
import org.umg.compilerjava.compiler.CompilerReport;
import org.umg.compilerjava.ui.LoginFrame;
import org.umg.compilerjava.ui.ResultFrame;

/**
 * Punto de entrada de la aplicación Java.
 *
 * <p>Permite ejecutar el compilador en modo CLI cuando recibe una ruta de archivo SQL.
 * Si no recibe argumentos y el entorno soporta interfaz gráfica, abre la ventana de login.
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
            System.err.println("Error: entorno sin interfaz gráfica y sin archivo de entrada.");
            System.err.println("Uso: java -cp build/classes org.umg.compilerjava.Main examples/query1.sql");
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
        if (path == null || path.isBlank()) {
            System.err.println("Error: ruta de archivo inválida.");
            return;
        }

        try {
            System.out.println("Ejecutando compilador en modo CLI...");
            String sql = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
            CompilerReport report = new CompilerFacade().compile(sql);
            System.out.println(report.toMultilineString());
        } catch (IOException ex) {
            System.err.println("No fue posible leer el archivo: " + path);
            System.err.println(ex.getMessage());
        } catch (RuntimeException ex) {
            System.err.println("Error durante la ejecución del compilador.");
            System.err.println(ex.getMessage());
        }
    }

    private static void openGui() {
        final InMemoryAuthService authService = new InMemoryAuthService();
        final CompilerFacade compilerFacade = new CompilerFacade();
        final ResultFrame resultFrame = new ResultFrame(compilerFacade);
        LoginFrame loginFrame = new LoginFrame(authService, resultFrame);
        loginFrame.setVisible(true);
    }
}