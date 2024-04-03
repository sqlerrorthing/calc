package me.oneqxz.calc.lexer.token;

/**
 * @author .1qxz
 * @since 2024.04.04
 */

public interface IBinaryToken<T> extends IToken<T> {
    OperatorType getOperator();
}
