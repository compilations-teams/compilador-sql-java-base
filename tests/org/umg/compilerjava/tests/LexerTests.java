package org.umg.compilerjava.tests;

import java.util.List;
import org.umg.compilerjava.compiler.Lexer;
import org.umg.compilerjava.compiler.Token;
import org.umg.compilerjava.compiler.TokenType;

public final class LexerTests {

    public void run() {
        tokenizesBasicQuery();
        supportsCommentsAndStrings();
        tokenizesAndOrKeywords();
        tokenizesQueryWithExtraSpaces();
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

    private void tokenizesAndOrKeywords() {
        List<Token> tokens = new Lexer("SELECT a FROM t WHERE a = 1 AND b = 2 OR c = 3;").tokenize();
        boolean hasAnd = false;
        boolean hasOr = false;
        for (Token t : tokens) {
            if (t.getType() == TokenType.AND) hasAnd = true;
            if (t.getType() == TokenType.OR) hasOr = true;
        }
        Assert.isTrue(hasAnd, "Debe reconocer AND");
        Assert.isTrue(hasOr, "Debe reconocer OR");
    }

    private void tokenizesQueryWithExtraSpaces() {
        List<Token> tokens = new Lexer("  SELECT   edad   FROM   clientes ;  ").tokenize();
        Assert.equals(TokenType.SELECT, tokens.get(0).getType(), "Debe reconocer SELECT con espacios");
        Assert.equals(TokenType.IDENTIFIER, tokens.get(1).getType(), "Debe reconocer la columna edad");
        Assert.equals(TokenType.FROM, tokens.get(2).getType(), "Debe reconocer FROM con espacios");
        Assert.equals(TokenType.IDENTIFIER, tokens.get(3).getType(), "Debe reconocer la tabla clientes");
        Assert.equals(TokenType.SEMICOLON, tokens.get(4).getType(), "Debe reconocer ;");
    }
}
