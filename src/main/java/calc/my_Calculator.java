package calc;

import java.util.Scanner;

public class my_Calculator {
    Scanner symbol = new Scanner(System.in);
    double firstNumber;
    double secondNumber;
    String operation;

    public void cycl() {
        while (!"q".equals(operation)){
            calculation();
        }
    }

    public String calculation() {
        boolean first = true;
        boolean second = true;
        boolean oper = true;

        while (first) {
            try {
                System.out.println("Введіть перше число: ");
                firstNumber = Double.parseDouble(symbol.next());
                first = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Програма очікує число!");
            }

        }

        while (second) {
            try {
                System.out.println("Введіть друге число: ");
                secondNumber = Double.parseDouble(symbol.next());
                second = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Програма очікує число!");
            }

        }

        while (oper) {
            System.out.println("Введіть операцію (+ - * /) або 'q' для виходу з програми");
            operation = symbol.next();
            if ((!"+".equals(operation)) & (!"-".equals(operation)) & (!"/".equals(operation))
                    & (!"*".equals(operation)) & (!"q".equals(operation))) {
                System.out.println(String.format("Така операція як '%s' - не підтримується ", operation));
            } else {
                oper = false;
            }
        }
        Double results = performOperation(firstNumber, secondNumber, operation);
        String result = String.format("%.3f",results);
        System.out.println(String.format("Ваш результат - %s", result));
        return result;
    }


    public Double performOperation(double firstNumber, double secondNumber, String operation)
    {
        double result;
        switch (operation)
        {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                    break;
                }
                else {
                    System.out.println("Не можна на нуль ділить");
                }
            default:
                result = 0;
                break;
        }

        return result;
    }


}
