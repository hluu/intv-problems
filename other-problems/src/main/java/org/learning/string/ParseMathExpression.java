package org.learning.string;

import org.testng.Assert;

import java.util.Stack;

/**
 *
 * Write a function that removes ‘(‘ and ‘)’ from the given expression.
 * The expression consists of lowercase alphabets, ‘+’, ‘-‘ and ‘( )’.
 *
 *
 * Write a function to parse the simple math expression
 *
 * For example:
 *  -a+b                => -a+b
 *  -(a+b)              => -a-b
 *  a-(b-(c-d))         => a-b+c-d
 *  a-(b-(c-d)-e+(f+g)) => a-b+c-d+e-f-g
 *
 * Approach:
 *  * ignore out parents in output
 *  * output only +,- and operands
 *  * how to deal with multi-level deep
 *      *  - ( - ( -  ( - (  )  )  )
 *      *  - ( - ( -  ( - (  )  )  )
 *  * to know the sign of an operand
 *      * sign outside of previous level
 *      * sign inside the level
 *  * maintain stack to maintain prev. levels
 *  * maintain the sign for one outer level
 *  * maintain the sign for current level
 *  *
 *
 */
public class ParseMathExpression {
    public static void main(String[] args) {
        System.out.println(ParseMathExpression.class.getName());

        test("-a+b", "-a+b");
        test("-(a+b)", "-a-b");
        test("-(a-b)", "-a+b");
        test("-(a-(b-c)+d)", "-a+b-c-d");
        test("a-(b-(c-d))", "a-b+c-d");
        test("a-(b-(c-d)+e)", "a-b+c-d-e");
        test("a-(b-(c-d)-e+(f+g))", "a-b+c-d+e-f-g");
        test("a-(b-(c-d+(e-f-(h+i))))", "a-b+c-d+e-f-h-i");

        // all positive
        //test("a+((b+c)+d)", "a+b+c+d");
    }

    private static void test(String input, String expected) {
        String actual = removeParams(input);

        System.out.printf("input: %s, expected: %s actual: %s\n",
                input, expected, actual);

        Assert.assertEquals(actual, expected);
        System.out.println();
    }


    /**
     * Approach:
     *  1) Maintain a stack to 2 level deep
     *  2) Maintain an outer level
     *  3) Maintain an current level
     *  4) When start a new level
     *      *) save outer level in stack
     *      *) calculate the current level based current operator and outer level operator
     *      *) update outer level with current level
     *      *) reset current level to '+'
     *  5) When end a new level
     *      *) reverse the order of operation of when starting a new level
     *      *) currentLevel = prevLevel
     *      *) prevLevel = stack.pop()
     *
     * @param input
     * @return
     */
    private static String removeParams(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        Stack<Character> stack = new Stack<Character>();
        char outerLevel = '+';
        char currentLevel = '+';

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            // minus or open param

            char currChar = input.charAt(i);


            if (currChar == '-' || currChar == '+') {
                currentLevel = currChar;
            } else if (currChar == '(') {
                stack.push(outerLevel);
                if (currentLevel == '-' && outerLevel == '-') {
                    currentLevel = '+';
                } else if (currentLevel == '-' || outerLevel == '-') {
                    currentLevel = '-';
                } else {
                    currentLevel = '+';
                }

                outerLevel = currentLevel;

                currentLevel = '+';
            } else if (currChar == ')') {
               currentLevel = outerLevel;
               outerLevel = stack.pop();
            } else {
                if (i > 0)  {
                    // computer operator based on current level and prev. level
                    if (currentLevel == '-' && outerLevel == '-') {
                        output.append('+');
                    } else if (currentLevel == '-' || outerLevel == '-') {
                        output.append('-');
                    } else {
                        output.append('+');
                    }
                }
                output.append(currChar);
            }
        }
        return output.toString();

    }
}






