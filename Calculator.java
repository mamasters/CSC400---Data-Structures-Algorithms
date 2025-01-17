import java.util.*;
import java.io.*;

public class Calculator {
    public int evaluatePostfixExpression(String postfixExpression) throws IllegalArgumentException {
        if (postfixExpression == null || postfixExpression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        String[] parsedExpression = postfixExpression.split("\\s");
        Stack<Integer> stack = new Stack<>();

        try {
            for (String token : parsedExpression) {
                if (isNumber(token)) {
                    stack.push(Integer.parseInt(token));
                } else if (isOperator(token)) {
                    // Check if there are enough operands
                    if (stack.size() < 2) {
                        throw new IllegalArgumentException("Invalid expression: not enough operands for operator '" + token + "'");
                    }

                    int operand2 = stack.pop();
                    int operand1 = stack.pop();

                    switch (token) {
                        case "+":
                            stack.push(operand1 + operand2);
                            break;
                        case "-":
                            stack.push(operand1 - operand2);
                            break;
                        case "*":
                            stack.push(operand1 * operand2);
                            break;
                        case "/":
                            if (operand2 == 0) {
                                throw new ArithmeticException("Division by zero");
                            }
                            stack.push(operand1 / operand2);
                            break;
                        case "%":
                            if (operand2 == 0) {
                                throw new ArithmeticException("Modulo by zero");
                            }
                            stack.push(operand1 % operand2);
                            break;
                    }
                } else {
                    throw new IllegalArgumentException("Invalid token: '" + token + "'");
                }
            }

            // Check if there are too many operands
            if (stack.size() > 1) {
                throw new IllegalArgumentException("Invalid expression: too many operands");
            }

            return stack.pop();

        } catch (Exception e) {
            throw new IllegalArgumentException("Error evaluating expression: " + e.getMessage());
        }
    }

    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return token.matches("[+\\-*/%]");
    }
	
    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();
        
        // Test cases
        String[] testExpressions = {
            "43 3 * 1 +",           // Valid
            "4 3 * 1 + -",         // Invalid: not enough operands
            "4 3 * 1 + 2",         // Invalid: too many operands
            "4 3 * 1 $ +",         // Invalid: invalid operator
            "4 0 /",               // Invalid: division by zero
            ""                     // Invalid: empty expression
        };

        for (String expression : testExpressions) {
            System.out.println("\nTesting expression: " + expression);
            try {
                int result = calculator.evaluatePostfixExpression(expression);
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
         
        // expressionList.txt located in src folder.
        String filePath = "C:\\Users\\maste\\eclipse-workspace\\Calculator\\src\\expressionList";
        File expressions = new File(filePath);
        
        Scanner fsc = new Scanner(expressions);

        System.out.println("--------------------------------------");
        System.out.printf("Evaluating expressions from %s:\n", filePath);
        while (fsc.hasNextLine()) {
            String expression = fsc.nextLine(); // Read the current line
            System.out.printf("Evaluating expression: (%s)\n", expression);
            try {
                int result = calculator.evaluatePostfixExpression(expression); // Evaluate the expression
                System.out.printf("Result: %d\n", result);
            } catch (Exception e) {
                System.out.println("Error: Invalid expression or evaluation issue");
            }
        }

        fsc.close();
    }
}
