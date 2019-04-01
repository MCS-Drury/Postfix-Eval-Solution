import java.util.Scanner;
import java.util.ArrayDeque;

public class PostFixEval {
    public static void main(String [] args) {

        // Declare a scanner for input
        Scanner in = new Scanner(System.in);

        // Print some directions
        System.out.printf("****************************************\n");
        System.out.printf("* The Postfix Expression Evaluation    *\n");
        System.out.printf("* app allows simple postfix            *\n");
        System.out.printf("* to be evaluated.  Expressions must   *\n");
        System.out.printf("* take the following form:             *\n");
        System.out.printf("*    a b operator                      *\n");
        System.out.printf("* where a and b are single digit       *\n");
        System.out.printf("* integers and operator is one of +,   *\n");
        System.out.printf("* -, *, or /. an example is:           *\n");
        System.out.printf("*     3 4 + 5 6 +*                     *\n");
        System.out.printf("****************************************\n");

        // Declare a stack for evaluation
        ArrayDeque<Integer> evalStack = new ArrayDeque<>();

        // Setup loop to evaluate one or more expressions
        boolean quit = false;

        while (!quit) {
            // Prompt for and read an expression
            System.out.printf("\nExpression to evaluate => " );
            String expIn = in.nextLine();

            // Split the line into String tokens around the blank
            String [] token = expIn.split(" ");

            // Process each token from the string
            for (int curToken=0; curToken < token.length; curToken++) {
                if (Character.isDigit(token[curToken].charAt(0))) {
                    // Current token is a digit - save as Integer on stack
                    evalStack.push(Integer.parseInt(token[curToken]));
                }
                else {
                    // Evaluate the operator and push result on stack
                    // 1. Get the two operators
                    int rValue = evalStack.pop();
                    int lValue = evalStack.pop();
                    int ans = 0;

                    // 2. Perform the operation
                    if (token[curToken].equals("+")) {
                        ans = lValue + rValue;
                    }
                    else if (token[curToken].equals("-")) {
                        ans = lValue - rValue;
                    }
                    else if (token[curToken].equals("*")) {
                        ans = lValue * rValue;
                    }
                    else if (token[curToken].equals("/")) {
                        ans = lValue / rValue;
                    }

                    // 3. Save the result on the stack
                    evalStack.push(ans);
                }
            }

            // Print the result
            System.out.printf("Value: %d\n", evalStack.pop());

            // Prompt to continue
            System.out.printf("\nEvaluate another expression? (Y/N) ");
            String cont = (in.nextLine()).toLowerCase();

            // Continue or quit
            if (!cont.equals("y")) {
                quit = true;
            }
        }

    }
}
