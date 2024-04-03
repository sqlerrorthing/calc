package me.oneqxz.calc.lexer;

/**
 * тип токена
 * @author .1qxz
 * @since 2024.04.02
 */
public enum TokenType {

    NUMBER, // число

    HEX_NUMBER, // hex

    WORD, // constants (pi, ...)

    PLUS, // +
    MINUS, // -
    TIMES, // *
    DIVIDED, // /

    LPAREN, // (
    RPAREN, // )

    EOF; // end of file
}
