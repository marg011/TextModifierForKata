import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println(textModifier());
    }

    public static String textModifier() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return sumOfNumbers(swapPlusToExclamation(swapSymbolsAtMinus(removeExtraSpaces(input))));
    }

    private static String removeExtraSpaces(String input) {
        StringBuilder output = new StringBuilder();
        boolean spaceFlag = false;

        for (int index = 0; index < input.length(); ++index) {
            if (input.charAt(index) != ' ') {
                output.append(input.charAt(index));
                spaceFlag = false;
            } else {
                if (!spaceFlag) {
                    output.append(' ');
                }
                spaceFlag = true;
            }
        }
        return output.toString();
    }

    private static String swapSymbolsAtMinus(String input){
        ArrayList<Integer> indsOfMinus = new ArrayList<>();
        int i = input.indexOf('-');
        while(i >= 0) {
            indsOfMinus.add(i);
            i = input.indexOf('-', i+1);
        }
        if (!indsOfMinus.isEmpty()) {
            char[] charactersInput = input.toCharArray();
            for (Integer ind : indsOfMinus) {
                char temp = charactersInput[ind - 1];
                charactersInput[ind - 1] = charactersInput[ind + 1];
                charactersInput[ind + 1] = temp;
            }
            StringBuilder builder = new StringBuilder();
            for (char value : charactersInput) {
                builder.append(value);
            }
            input = builder.toString();
            input = input.replace("-", "");
        }
        return input;
    }

    private static String swapPlusToExclamation(String input){
        return input.replaceAll("\\+", "!");
    }

    private static String sumOfNumbers(String input){
        Pattern integerPattern = Pattern.compile("[0-9+]");
        Matcher matcher = integerPattern.matcher(input);

        List<String> integerList = new ArrayList<>();
        while (matcher.find()) {
            String match = matcher.group();
            integerList.add(match);
            input = input.replace(match, "");
        }
        if (!integerList.isEmpty()) {
            int sum = integerList.stream().mapToInt(Integer::valueOf).sum();
            input = input + " " + sum;
        }
        return input;
    }
}