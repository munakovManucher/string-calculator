package StringCalc;

import java.util.Scanner;

public class StringCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();//ввод данных пользователем.


        String[] massive = input.split("\\s+(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");; //массив для оператора и операндов с методом разделителя по пробелу

        String firstOperandSource = massive[0];//присваиваем первому операнду нулевой индекс массива
        String operator = massive[1];//присваиваем оператору первый элемент массива
        String secondOperandSource = massive[2]; //присваиваем второму операнду второй элемент массива

        if (massive.length != 3) {//задаём условие о том, что строка данных должна принимать три значения
            throw new RuntimeException("ввод данных должен содержать операнд1, оператор и операнд2 через " +
                    "пробел");
        }

        if (!firstOperandSource.startsWith("\"") || !firstOperandSource.endsWith("\"")) {
            throw new RuntimeException("операнд 1 должен быть в кавычках");
        }
        if (firstOperandSource.length() > 12) {
            throw new RuntimeException("длина операнда 1 не должна превышать 10 символов");
        }


        String firstOperand = firstOperandSource.substring(1, firstOperandSource.length()-1);

        String result = "";

        int secondOperandAsANumber = 0;
        boolean secondOperandIsNumber = true;

        try {
            secondOperandAsANumber = Integer.parseInt(secondOperandSource);
        } catch (RuntimeException e) {
            secondOperandIsNumber = false;
        }


        if (secondOperandIsNumber) {
            if (secondOperandAsANumber < 1 && secondOperandAsANumber > 10) {
                throw new RuntimeException("число должно быть от 1 до 10");
            }
            if (operator.equals("*")) {
                result = firstOperand.repeat(secondOperandAsANumber);
                result = "\"" + result + "\"";
                if (result.length() > 40) {
                    result = result.substring(0, 40) + "...";
                }
            } else if (operator.equals("/")) {
                int newLenghForFirstOperand = firstOperand.length();
                int needLenght = newLenghForFirstOperand / secondOperandAsANumber;
                result = firstOperand.substring(0, needLenght);
                result = "\"" + result + "\"";
            } else {
                throw new RuntimeException("неверный оператор");
            }

        } else {

            String secondOperand = secondOperandSource.substring(1, secondOperandSource.length()-1);
            //это строка
            if (operator.equals("+")) {
                result = firstOperand + secondOperand;
                result = "\"" + result + "\"";
            } else if (operator.equals("-")) {
                result = firstOperand.replace(secondOperand, "");
                result = "\"" + result + "\"";
            }
        }

        System.out.println(result);
    }
}