import org.junit.Assert;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given an arithmetic expression in Reverse Polish Notation, write a
 * program to evaluate it.
 *
 * The expression is given as a list of numbers and operands.
 *
 * For example: [5, 3, '+'] should return 5 + 3 = 8.
 *
 * For example,
 *  [15, 7, 1, 1, '+', '-', '/', 3, '*', 2, 1, 1, '+', '+', '-']
 *   should return 5, since it is equivalent to
 * ((15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1)) = 5.
 *
 * You can assume the given expression is always valid.
 *
 * Analysis:
 * - two operands and one operator
 * - use a stack of integers to store the operands
 * - when an operator is encountered, pop two operands and perform the computation
 * - push the result back onto the stack
 * - when there are no more character pop the stack and return it
 */
public class ReversePolishNotation {
    public static void main(String[] args) {
        System.out.println(ReversePolishNotation.class.getName());

        test(new String[] {"2","3","+"}, 5);
        test(new String[] {"3","2","-"}, 1);
        test(new String[] {"2","3","*"}, 6);
        test(new String[] {"4","2","/"}, 2);
        test(new String[] {"15","7","1","1","+","-","/","3","*","2","1","1","+","+","-"},
                5);

    }

    private static void test(String[] rpn, long expected) {
        System.out.println("\n===> test rpn: " + Arrays.toString(rpn));

        long actual = compute(rpn);

        System.out.printf("expected: %d, actual: %d\n",
                expected, actual);

        Assert.assertEquals(expected, actual);
    }

    private static long compute(String[] rpn) {
        if (rpn == null || rpn.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < rpn.length; i++) {
            String token = rpn[i];

            if (!Character.isDigit(token.charAt(0))) {
                int operand1 = stack.pop();
                int operand2 = stack.pop();
                int result = -1;
                switch (token)  {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand2 - operand1;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        result = operand2 / operand1;
                        break;
                }
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();

    }
}
