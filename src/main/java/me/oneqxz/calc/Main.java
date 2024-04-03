package me.oneqxz.calc;

import me.oneqxz.calc.lexer.Lexer;
import me.oneqxz.calc.lexer.Token;
import me.oneqxz.calc.parser.Parser;
import me.oneqxz.calc.parser.ast.Expression;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Ну как бы главный класс моего шедеврокалькулятора
 *
 * @author .1qxz
 * @since 2024.04.02
 */

public class Main {

    public static void main(String[] args) {
        while (true)
        {
            final String inp;
            System.out.print(">>> ");
            Scanner sc = new Scanner(System.in);
            inp = sc.nextLine() ;

            try
            {
                Lexer lex = new Lexer(inp);
                List<Token> tokens = lex.tokenize();
                System.out.println(Arrays.toString(tokens.stream().map(Token::toString).toArray()));

                List<Expression> expressions = new Parser(tokens).parse();
                System.out.println("<<< " + expressions.get(0).eval());
            }
            catch (Exception e)
            {
                System.err.println("Internal error!");
                e.printStackTrace();
            }
        }
    }

}
