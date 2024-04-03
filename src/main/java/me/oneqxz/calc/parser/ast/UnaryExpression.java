package me.oneqxz.calc.parser.ast;

import lombok.AllArgsConstructor;
import me.oneqxz.calc.lexer.TokenType;

import java.util.Objects;

/**
 * @author .1qxz
 * @since 2024.04.02
 */

@AllArgsConstructor
public class UnaryExpression implements Expression {

    private final TokenType operation;
    private final Expression exp;

    @Override
    public double eval() {
        if (Objects.requireNonNull(operation) == TokenType.MINUS) {
            return -exp.eval();
        }
        return exp.eval();
    }
}
