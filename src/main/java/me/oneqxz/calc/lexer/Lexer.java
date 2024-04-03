package me.oneqxz.calc.lexer;

import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import me.oneqxz.calc.CalcUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * лексический <strong>анал</strong>из
 *
 * @author .1qxz
 * @since 2024.04.02
 */

@RequiredArgsConstructor
public class Lexer {

    private static final String OPERATOR_CHARS = "+-*/()";
    private static final TokenType[] OPERATOR_TOKENS = {
            TokenType.PLUS,
            TokenType.MINUS,
            TokenType.TIMES,
            TokenType.DIVIDED,
            TokenType.LPAREN,
            TokenType.RPAREN
    };

    private final String input;
    private final int length;

    private final List<Token> tokens = new LinkedList<>();
    private int pos;

    public Lexer(String input) {
        this.input = input;
        this.length = input.length();
    }

    public List<Token> tokenize()
    {
        while (pos < length)
        {
            final char curr = peek(0);

            if (Character.isDigit(curr)) tokenizeNumber(curr);
            if (Character.isLetter(curr)) tokenizeWord(curr);
            else if (curr == '#') tokenizeHexNumber(next());
            else if (OPERATOR_CHARS.indexOf(curr) != -1) tokenizeOperator(curr);
            else {
                next();
            }
        }

        return tokens;
    }

    private void tokenizeNumber(char current)
    {
        final StringBuilder buffer = new StringBuilder();
        boolean flag = false;

        while (Character.isDigit(current) || current == '.')
        {
            if(current == '.')
                if(buffer.indexOf(".") != -1) throw new RuntimeException("Invalid float number!");

            buffer.append(current);
            current = next();
        }
        if(current == '!')
        {
            flag = true;
            next();
        }

        addToken(new Token(flag ? String.valueOf(CalcUtils.factorial(Integer.parseInt(buffer.toString()))) : buffer.toString(), TokenType.NUMBER));
    }

    private void tokenizeWord(char current)
    {
        final StringBuilder buffer = new StringBuilder();

        while (Character.isLetterOrDigit(current) && Character.isAlphabetic(current))
        {
            buffer.append(current);
            current = next();
        }

        addToken(new Token(buffer.toString(), TokenType.WORD));
    }

    private void tokenizeHexNumber(char current)
    {
        final StringBuilder buffer = new StringBuilder();

        while (Character.isDigit(current) || isHexHumber(current))
        {
            buffer.append(current);
            current = next();
        }
        addToken(new Token(buffer.toString(), TokenType.HEX_NUMBER));
    }

    private static boolean isHexHumber(char current) {
        return "abcdef".indexOf(Character.toLowerCase(current)) != -1;
    }

    private void tokenizeOperator(char current)
    {
        final int pos = OPERATOR_CHARS.indexOf(current);
        addToken(OPERATOR_TOKENS[pos]);
        next();
    }

    private char peek(int relPosition)
    {
        final int finalPos = pos + relPosition;
        if(finalPos >= length) return '\0';
        return input.charAt(finalPos);
    }

    private char next()
    {
        pos++;
        return peek(0);
    }

    private void addToken(Token t)
    {
        this.tokens.add(t);
    }

    private void addToken(TokenType t)
    {
        this.tokens.add(new Token(null, t));
    }
}
