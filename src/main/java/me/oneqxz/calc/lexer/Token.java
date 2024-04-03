package me.oneqxz.calc.lexer;

/**
 * токены, который вернет лексер
 *
 * @author .1qxz
 * @since 2024.04.02
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Setter
@ToString
public class Token {

    private String value;
    private TokenType token;

}
