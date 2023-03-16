import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DoublyLinkedStack<String> myStack = new DoublyLinkedStack<String>();
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Please provide a mathematical expression in infix notation: ");
        String infixString = myScanner.nextLine();
        ArrayList<String> postfixArrayList = new ArrayList<String>();
        String arr[] = infixString.split(" ");
        List<String> l = new ArrayList<String>();
        l = Arrays.asList(arr);

        // code block that holds the postfix algorithm ------------------------
        for (int i = 0; i < l.size(); i++) {
            String element = l.get(i);
            if (isNumeric(element)) {
                postfixArrayList.add(element);
                if (l.size() - 1 == i) {
                    while (!myStack.isEmpty()) {
                        postfixArrayList.add(myStack.peek());
                        myStack.pop();
                    }
                }
                continue;
            }
            else {
                if (element.equals("(") || element.equals("[") || element.equals("{")) {
                    myStack.push(element);
                }
                else if (element.equals(")") || element.equals("]") || element.equals("}")) {
                    if (element.equals(")")) {
                        while (!myStack.peek().equals("(")) {
                            postfixArrayList.add(myStack.peek());
                            myStack.pop();
                            if (myStack.getNumberOfElements() == 0) {
                                break;
                            }
                        }
                    }
                    else if (element.equals("]")) {
                        while (!myStack.peek().equals("[")) {
                            postfixArrayList.add(myStack.peek());
                            myStack.pop();
                            if (myStack.getNumberOfElements() == 0) {
                                break;
                            }
                        }

                    }
                    else if (element.equals("}")) {
                        while (!myStack.peek().equals("{")) {
                            postfixArrayList.add(myStack.peek());
                            myStack.pop();
                            if (myStack.getNumberOfElements() == 0) {
                                break;
                            }
                        }
                    }
                    String x = myStack.pop();
                    if (l.size() - 1 == i) {
                        while (!myStack.isEmpty()) {
                            postfixArrayList.add(myStack.peek());
                            myStack.pop();
                        }
                    }
                }
                else {
                    if (myStack.isEmpty() || myStack.peek().equals("(") || myStack.peek().equals("[") || myStack.peek().equals("{")) {
                        myStack.push(element);
                    }
                    else {
                        switch (element) {
                            case "^":
                                while (myStack.peek().equals("^")) {
                                    postfixArrayList.add(myStack.peek());
                                    myStack.pop();
                                    if (myStack.peek() == null) {
                                        break;
                                    }
                                }
                                if (myStack.peek().equals("*") || myStack.peek().equals("/") || myStack.peek().equals("+") || myStack.peek().equals("-")) {
                                }
                                myStack.push(element);
                                continue;

                            case "*":
                                while (myStack.peek().equals("*") || myStack.peek().equals("^") || myStack.peek().equals("/")) {
                                    postfixArrayList.add(myStack.peek());
                                    myStack.pop();
                                    if (myStack.peek() == null) {
                                        break;
                                    }
                                }
                                if (myStack.peek() == null || myStack.peek().equals("+") || myStack.peek().equals("-")) {
                                }
                                myStack.push(element);
                                continue;

                            case "/":
                                while (myStack.peek().equals("/") || myStack.peek().equals("*") || myStack.peek().equals("^")) {
                                    postfixArrayList.add(myStack.peek());
                                    myStack.pop();
                                    if (myStack.peek() == null) {
                                        break;
                                    }
                                }
                                if (myStack.peek() == null || myStack.peek().equals("+") || myStack.peek().equals("-")) {
                                }
                                myStack.push(element);
                                continue;

                            case "+":
                                while (myStack.peek().equals("/") || myStack.peek().equals("*") || myStack.peek().equals("^") || myStack.peek().equals("+")) {
                                    postfixArrayList.add(myStack.peek());
                                    myStack.pop();
                                    if (myStack.peek() == null) {
                                        break;
                                    }
                                }
                                if (myStack.peek() == null || myStack.peek().equals("-")) {
                                }
                                myStack.push(element);
                                continue;

                            case "-":
                                while (myStack.peek().equals("/") || myStack.peek().equals("*") || myStack.peek().equals("^") || myStack.peek().equals("+") || myStack.peek().equals("-")) {
                                    postfixArrayList.add(myStack.peek());
                                    myStack.pop();
                                    if (myStack.peek() == null) {
                                        break;
                                    }
                                }
                                myStack.push(element);
                                continue;

                            default:
                                System.out.println("Operator not valid.");
                        }
                    }
                }
            }
        }
        System.out.print("Postfix expression: ");
        for (int i = 0; i < postfixArrayList.size(); i++) {
            System.out.print(postfixArrayList.get(i) + " ");
        }

        for (int i = 0; i < postfixArrayList.size(); i++) {
            String element = postfixArrayList.get(i);
            if (isNumeric(element)) {
                myStack.push(element);
            }
            else {
                String secondOperand = myStack.peek();
                myStack.pop();
                String firstOperand = myStack.peek();
                myStack.pop();
                double secondOperandDouble = Double.parseDouble(secondOperand);
                double firstOperandDouble = Double.parseDouble(firstOperand);

                if (element.equals("^")) {
                    double result = Math.pow(firstOperandDouble, secondOperandDouble);
                    String stringResult = Double.toString(result);
                    myStack.push(stringResult);
                }
                else if (element.equals("*")) {
                    double result = firstOperandDouble * secondOperandDouble;
                    String stringResult = Double.toString(result);
                    myStack.push(stringResult);
                }
                else if (element.equals("/")) {
                    double result = firstOperandDouble / secondOperandDouble;
                    String stringResult = Double.toString(result);
                    myStack.push(stringResult);
                }
                else if (element.equals("+")) {
                    double result = firstOperandDouble + secondOperandDouble;
                    String stringResult = Double.toString(result);
                    myStack.push(stringResult);
                }
                else if (element.equals("-")) {
                    double result = firstOperandDouble - secondOperandDouble;
                    String stringResult = Double.toString(result);
                    myStack.push(stringResult);
                }
            }
        }
        String finalResult = myStack.pop();
        System.out.println();
        System.out.println("The solved postfix expression is: " + finalResult);
    }

    public static boolean isBalanced(String array[]) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("(") || array[i].equals("[") || array[i].equals("{")) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}

// Tests ---------------------------------------------------------------------
// ( 4 - 5 * 6 ) / ( 7 * 8 ^ 9 * 10 + 11 )      Answer: -2.7673585041888118E-9
// 1 + 2 * 3 ^ 4 / 5 ^ 6                        Answer: 1.01
// 2 * 3 + 4 * 5                                Answer: 26
// ( 4 / 5 ) * ( 4 / 7 ) * 100                  Answer: 45.714285714285715
// [ 4 + 8 * 3 ] ^ ( 5 - 3 / 6 )                Answer: 3252453.835707434
// 4 - ( 7 / ( 8 - 2 ) * 7 + 1 ) ^ 3            Answer: -766.2546296296299