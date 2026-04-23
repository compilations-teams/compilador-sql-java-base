package org.umg.compilerjava.tests;

/** Ejecuta todas las pruebas mínimas del proyecto base. */
public final class RunAllTests {

    private RunAllTests() {
    }

    public static void main(String[] args) {
        int executed = 0;

        executed += run("LexerTests", new Runnable() {
            @Override
            public void run() {
                new LexerTests().run();
            }
        });

        executed += run("ParserTests", new Runnable() {
            @Override
            public void run() {
                new ParserTests().run();
            }
        });

        executed += run("SemanticAnalyzerTests", new Runnable() {
            @Override
            public void run() {
                new SemanticAnalyzerTests().run();
            }
        });

        executed += run("AuthServiceTests", new Runnable() {
            @Override
            public void run() {
                new AuthServiceTests().run();
            }
        });

        executed += run("CompilerFacadeTests", new Runnable() {
            @Override
            public void run() {
                new CompilerFacadeTests().run();
            }
        });

        System.out.println("Total suites ejecutadas: " + executed);
        System.out.println("✅ Todas las pruebas pasaron");
    }

    private static int run(String name, Runnable suite) {
        suite.run();
        System.out.println("✔ " + name);
        return 1;
    }
}
