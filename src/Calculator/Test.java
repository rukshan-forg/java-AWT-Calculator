

import java.util.List;
import java.util.Stack;

public class Test {

    public static double calculate(List<Object> vector) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();

        for (Object element : vector) {
            if (element instanceof Integer) {
                numbers.push(((Integer) element).doubleValue());
            } else if (element instanceof Character) {
                char op = (char) element;
                if (op == '=') {
                    break;
                } else if (op == '×' || op == '÷') {
                    double num1 = numbers.pop();
                    double num2 = ((Integer) vector.get(vector.indexOf(op) + 1)).doubleValue();
                    if (op == '×') {
                        numbers.push(num1 * num2);
                    } else {
                        numbers.push(num1 / num2);
                    }
                } else {
                    operations.push(op);
                }
            }
        }

        // Reprocess the remaining addition and subtraction operations
        while (!operations.isEmpty()) {
            char op = operations.remove(0);
            double num1 = numbers.remove(0);
            double num2 = numbers.remove(0);
            if (op == '+') {
                numbers.add(0, num1 + num2);
            } else {
                numbers.add(0, num1 - num2);
            }
        }

        return numbers.pop();
    }

    public static void main(String[] args) {
        List<Object> vector = List.of(100, '-', 5, '+', 11, '×', 6, '÷', 10, '=');
        double result = calculate(vector);
        System.out.println("Result: " + result);  // Expected output: 98.6
    }
}
