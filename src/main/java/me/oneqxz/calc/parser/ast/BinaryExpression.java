package me.oneqxz.calc.parser.ast;

import lombok.AllArgsConstructor;
import lombok.ToString;
import me.oneqxz.calc.lexer.TokenType;

/**
 * @author .1qxz
 * @since 2024.04.02
 */

@AllArgsConstructor
public class BinaryExpression implements Expression{

    private final TokenType operation;
    private final Expression expr1, expr2;

    public double eval() {
        switch (operation) {
            case PLUS: return expr1.eval() + expr2.eval();
            case MINUS: return expr1.eval() - expr2.eval();
            case TIMES: return expr1.eval() * expr2.eval();
            case DIVIDED: return expr1.eval() / expr2.eval();
        };

        throw new RuntimeException("Invalid TokenType: " + operation);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s = %s", expr1.eval(), operation.name(), expr2.eval(), eval());
    }

}
