package me.oneqxz.calc.parser;

import me.oneqxz.calc.lexer.Token;
import me.oneqxz.calc.lexer.TokenType;
import me.oneqxz.calc.parser.ast.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author .1qxz
 * @since 2024.04.02
 */

public class Parser {

    private static final Token EOF = new Token(null, TokenType.EOF);

    private final List<Token> tokens;
    private final int size;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.size = tokens.size();
    }

    public List<Expression> parse()
    {
        final List<Expression> result = new LinkedList<>();

        while (!match(TokenType.EOF))
        {
            result.add(expression());
        }

        System.out.println(Arrays.toString(result.stream().map(Expression::toString).toArray()));

        return result;
    }

    private Expression expression()
    {
        return additive();
    }

    private Expression additive()
    {
        Expression result = multiplicative();

        while (true)
        {
            if(match(TokenType.PLUS))
            {
                result = new BinaryExpression(TokenType.PLUS, result, multiplicative());
                continue;
            }

            if(match(TokenType.MINUS))
            {
                result = new BinaryExpression(TokenType.MINUS, result, multiplicative());
                continue;
            }

            break;
        }

        return result;
    }

    private Expression multiplicative()
    {
        Expression result = unary();

        while (true)
        {
            if(match(TokenType.TIMES))
            {
                result = new BinaryExpression(TokenType.TIMES, result, unary());
                continue;
            }
            else if(match(TokenType.DIVIDED))
            {
                result = new BinaryExpression(TokenType.DIVIDED, result, unary());
                continue;
            }

            break;
        }

        return result;
    }

    private Expression unary()
    {
        if (match(TokenType.MINUS))
            return new UnaryExpression(TokenType.MINUS, primary());

        else if (match(TokenType.PLUS))
            return primary();

        return primary();
    }

    private Expression primary()
    {
        final Token current = get(0);
        if (match(current, TokenType.NUMBER))
            return new NumberExpression(Double.parseDouble(current.getValue()));

        if (match(current, TokenType.HEX_NUMBER))
            return new NumberExpression(Long.parseLong(current.getValue(), 16));

        if (match(current, TokenType.WORD))
            return new ConstantExpression(current.getValue());

        if(match(current, TokenType.LPAREN))
        {
            Expression res = expression();
            match(TokenType.RPAREN);
            return res;
        }


        throw new RuntimeException("Unknown expression " + current.getToken().name());
    }

    private boolean match(Token t, TokenType type)
    {
        if(t.getToken() != type) return false;

        pos++;
        return true;
    }

    private Token get(int relPosition)
    {
        final int finalPos = pos + relPosition;
        if(finalPos >= size) return EOF;

        return tokens.get(finalPos);
    }

    private Token next()
    {
        pos++;
        return get(0);
    }

    private boolean match(TokenType type)
    {
        return match(get(0), type);
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
