package me.oneqxz.calc.lexer.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author .1qxz
 * @since 2024.04.04
 */

@AllArgsConstructor
@Getter
public enum OperatorType {
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDED("/"),
    MODULE("%");

    private String operand;

}
