package calсulatorstring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

   /*
   Для себя.
   Можно добавить/изменить:
   - операции умножения/деления и сложения/вычитания объединить в одном методе
   - графический интерфейс
    */



    public static String calculator(String s) {
        if (!validInput(s))
            return null;

        String result = null;

        // Убираем пробелы
        s = s.replace(" ", "");

        // Поиск и расчёт выражений в скобках, включающих знаки -+*/
        Pattern patternParenthesis = Pattern.compile("[(][^()]+[-+*/]+[^()]+[)]");
        Matcher matcherParenthesis = patternParenthesis.matcher(s);

        if (matcherParenthesis.find()) {
            int start = matcherParenthesis.start();
            int end = matcherParenthesis.end();
            String operation = s.substring(start + 1, end - 1);
            String resultParenthesis = calculator(operation);
            result = calculator(s.replace(s.substring(start + 1, end - 1), resultParenthesis));
            return result;
        }

        /*
        Поиск и расчёт выражений с умножением или делением типа:
        a*b
        a/(-b)
        (a)*b
        и т.д.
        */
        Pattern patternMultiplicationAndDivision = Pattern.compile
                ("(([(]-?([0-9]+[.])?[0-9]+[)])|(([0-9]+[.])?[0-9]+))[*/](([(]-?([0-9]+[.])?[0-9]+[)])|(([0-9]+[.])?[0-9]+))");
        Matcher matcherMultiplicationAndDivision = patternMultiplicationAndDivision.matcher(s);

        Pattern patternNumber = Pattern.compile("[-]?([0-9]+[.])?[0-9]+");

        if (matcherMultiplicationAndDivision.find()) {
            int start = matcherMultiplicationAndDivision.start();
            int end = matcherMultiplicationAndDivision.end();
            String multiplicationAndDivision = s.substring(start, end);
            Matcher matcherNumber = patternNumber.matcher(multiplicationAndDivision);
            matcherNumber.find();
            int startNumber = matcherNumber.start();
            int endNumber = matcherNumber.end();
            double left = Double.parseDouble(multiplicationAndDivision.substring(startNumber, endNumber));

            matcherNumber.find();
            startNumber = matcherNumber.start();
            endNumber = matcherNumber.end();
            double right = Double.parseDouble(multiplicationAndDivision.substring(startNumber, endNumber));
            double answer = (multiplicationAndDivision.contains("*")) ? left * right : left / right;
            String resultMultiplicationAndDivision = "(" + doubleToInt(answer) + ")";

            result = calculator(matcherMultiplicationAndDivision.replaceFirst(resultMultiplicationAndDivision));
            return result;
        }

        /*
        Поиск и расчёт выражений с вычитанием и сложением типа:
        a+b
        -a+(-b)
        (-a)-b
        и т.д.
        Нужно объединить с */
        */
        Pattern patternAdditionAndSubtraction = Pattern.compile
                ("(([(]-?([0-9]+[.])?[0-9]+[)])|([-]?([0-9]+[.])?[0-9]+))[-+](([(]-?([0-9]+[.])?[0-9]+[)])|(([0-9]+[.])?[0-9]+))");
        Matcher matcherAdditionAndSubtraction = patternAdditionAndSubtraction.matcher(s);

        if (matcherAdditionAndSubtraction.find()) {
            int start = matcherAdditionAndSubtraction.start();
            int end = matcherAdditionAndSubtraction.end();
            String additionAndSubtraction = s.substring(start, end);
            Matcher matcherNumber = patternNumber.matcher(additionAndSubtraction);
            matcherNumber.find();
            int startNumber = matcherNumber.start();
            int endNumber = matcherNumber.end();
            double left = Double.parseDouble(additionAndSubtraction.substring(startNumber, endNumber));
            matcherNumber.find();
            startNumber = matcherNumber.start();
            endNumber = matcherNumber.end();
            double right = Double.parseDouble(additionAndSubtraction.substring(startNumber, endNumber));
            double answer = (additionAndSubtraction.contains("-(-")) ? left - right : left + right;
            String resultAdditionAndSubtraction = "(" + doubleToInt(answer) + ")";
            result = calculator(matcherAdditionAndSubtraction.replaceFirst(resultAdditionAndSubtraction));
            return result;
        }

        /*
        Поиск чисел в скобках
        Удаление скобок
        */
        Pattern patternParenthesisNumber = Pattern.compile("[(]{1}[-]*[^-+=*()]+?[)]");
        Matcher matcherParenthesisNumber = patternParenthesisNumber.matcher(s);
        if (matcherParenthesisNumber.find()) {
            int start = matcherParenthesisNumber.start();
            int end = matcherParenthesisNumber.end();
            String parenthesisNumber = s.substring(start, end);
            String number = s.substring(start + 1, end - 1);
            result = s.replace(parenthesisNumber, number);

            matcherParenthesisNumber = patternParenthesisNumber.matcher(result);
            if (matcherParenthesisNumber.find()) {
                result = calculator(result);
                return result;
            }
            return checkResult(result) ? result : null;
        }

        return result;
    }

    private static boolean validInput(String s) {
        boolean result = true;
        if (s == null ||
                s.equals("") ||
                s.matches(".*[-+*/.]{2}.*") ||
                s.matches(".*[^0-9.+*/() -].*") ||
                s.replaceAll("[^(]", "").length() != s.replaceAll("[^)]", "").length() ||
                s.indexOf(')') == 0 ||
                s.lastIndexOf('(') == s.length()-1
        )
            result = false;
        return result;
    }

    private static String doubleToInt(Double d) {
        if (d % 1 == 0 && Math.abs(d) >= 1 && d >= Integer.MIN_VALUE && d <= Integer.MAX_VALUE) {
            Integer i = d.intValue();
            return i.toString();
        }
        return d.toString();
    }


    // Проверка, является ли итоговый ответ числом

    private static boolean checkResult(String result) {
        Pattern patternResult = Pattern.compile("[-]?([0-9]+[.])?[0-9]+");
        Matcher matcherResult = patternResult.matcher(result);
        if (matcherResult.find()) {
            int start = matcherResult.start();
            int end = matcherResult.end();
            String checkResult = result.substring(start, end);
            if (result.equals(checkResult)) {
                return true;
            }
        }
        return false;
    }

}


