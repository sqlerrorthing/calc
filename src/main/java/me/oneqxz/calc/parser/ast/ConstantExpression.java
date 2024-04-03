package me.oneqxz.calc.parser.ast;

import lombok.AllArgsConstructor;

/**
 * @author .1qxz
 * @since 2024.04.02
 */

@AllArgsConstructor
public class ConstantExpression implements Expression {
    private final String word;

    @Override
    public double eval() {
        switch (word)
        {
            case "PI": return Math.PI;
            case "E": return Math.E;
        }

        throw new RuntimeException("Invalid var");
    }

}
