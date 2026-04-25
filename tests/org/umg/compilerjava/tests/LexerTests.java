package org.umg.compilerjava.tests;

import java.util.List;
import org.umg.compilerjava.compiler.Lexer;
import org.umg.compilerjava.compiler.Token;
import org.umg.compilerjava.compiler.TokenType;

public final class LexerTests {

    public void run() {
        tokenizesBasicQuery();
        supportsCommentsAndStrings();
        recognizesKeywordsIdentifiersAndIntegers();
    }

    private void tokenizesBasicQuery() {
        List<Token> tokens = new Lexer("SELECT nombre FROM usuarios;").tokenize();
        Assert.equals(TokenType.SELECT, tokens.get(0).getType(), "Debe reconocer SELECT");
        Assert.equals(TokenType.IDENTIFIER, tokens.get(1).getType(), "Debe reconocer columna");
        Assert.equals(TokenType.FROM, tokens.get(2).getType(), "Debe reconocer FROM");
        Assert.equals(TokenType.IDENTIFIER, tokens.get(3).getType(), "Debe reconocer tabla");
        Assert.equals(TokenType.SEMICOLON, tokens.get(4).getType(), "Debe reconocer ;");
    }

    private void supportsCommentsAndStrings() {
        List<Token> tokens = new Lexer("-- comentario\nSELECT nombre FROM usuarios WHERE ciudad = 'Roma';").tokenize();
        Assert.equals(TokenType.SELECT, tokens.get(0).getType(), "Debe ignorar comentarios");
        Assert.equals(TokenType.STRING, tokens.get(7).getType(), "Debe reconocer strings");
    }

    private void recognizesKeywordsIdentifiersAndIntegers() {
        List<Token> tokens = new Lexer("select user_name FROM usuarios WHERE edad = 18;").tokenize();
        Assert.equals(TokenType.SELECT, tokens.get(0).getType(), "Debe reconocer SELECT sin importar mayusculas");
        Assert.equals("select", tokens.get(0).getValue(), "Debe preservar el lexema original del keyword");
        Assert.equals(TokenType.IDENTIFIER, tokens.get(1).getType(), "Debe reconocer identificadores con guion bajo");
        Assert.equals("user_name", tokens.get(1).getValue(), "Debe conservar el identificador completo");
        Assert.equals(TokenType.FROM, tokens.get(2).getType(), "Debe reconocer FROM");
        Assert.equals(TokenType.IDENTIFIER, tokens.get(3).getType(), "Debe reconocer nombres de tabla");
        Assert.equals(TokenType.WHERE, tokens.get(4).getType(), "Debe reconocer WHERE");
        Assert.equals(TokenType.IDENTIFIER, tokens.get(5).getType(), "Debe reconocer nombres de columna");
        Assert.equals(TokenType.EQUAL, tokens.get(6).getType(), "Debe reconocer =");
        Assert.equals(TokenType.NUMBER, tokens.get(7).getType(), "Debe reconocer enteros");
        Assert.equals("18", tokens.get(7).getValue(), "Debe conservar el valor numerico");
    }
}
