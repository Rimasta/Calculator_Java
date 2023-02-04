import java.util.Scanner;

public class Main {
    public static String calc(String input){
        String[] userdata = input.toUpperCase().split(" ");
        String result;

        if(userdata.length>3)throw new RuntimeException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if(userdata.length<2)throw new RuntimeException("Строка не является математической операцией");

        boolean isRoman = false;

        int firstNumber = 0;
        int secondNumber = 0;

        if(userdata[0].matches("[0-9]+") & userdata[2].matches("[0-9]+")){
            firstNumber = Integer.parseInt(userdata[0]);
            secondNumber = Integer.parseInt(userdata[2]);
            if((secondNumber > 10 | firstNumber > 10) | (firstNumber < 0| secondNumber < 0))
                throw new RuntimeException("Недопустимые значения");

        } else if (userdata[0].matches("[I, V, X]+") & userdata[2].matches("[I, V, X]+")) {
            isRoman = true;
            firstNumber = Converter.romanToArabic(userdata[0]);
            secondNumber = Converter.romanToArabic(userdata[2]);
            if((secondNumber > 10 | firstNumber > 10) | (firstNumber < 0| secondNumber < 0))
                throw new RuntimeException("Недопустимые значения");
            } else{
            if ((userdata[0].matches("[0-9]+") & userdata[2].matches("[I, V, X]+")) |
                    (userdata[0].matches("[I, V, X]+") & userdata[2].matches("[0-9]+"))){
                throw new RuntimeException("Используются одновременно разные системы счисления");
            }
        }

        result = switch (userdata[1]) {
            case "+" -> String.valueOf(firstNumber + secondNumber);
            case "-" -> String.valueOf(firstNumber - secondNumber);
            case "*" -> String.valueOf(firstNumber * secondNumber);
            case "/" -> String.valueOf(firstNumber / secondNumber);
            default -> throw new RuntimeException(
                    "формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        };

        if (isRoman) result = Converter.arabicToRoman(Integer.parseInt(result));
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }
}