package me.oneqxz.calc.parser.ast;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author .1qxz
 * @since 2024.04.02
 */

@AllArgsConstructor
@ToString
public class NumberExpression implements Expression {

    private double value;

    @Override
    public double eval() {
        return value;
    }
}
