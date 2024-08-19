package application.lib;

import java.util.ArrayList;
import java.util.Random;

public class StringSettings {
	
	public static String removeLastFromText(String text) {
        char[] arr = text.toCharArray();
        ArrayList<Character> arrayList = new ArrayList<Character>();
        for (int i = 0; i < arr.length; i++) {
            arrayList.add(arr[i]);
        }
        if (arrayList.size() > 0) {
        	arrayList.remove(arrayList.size() - 1);
            text = "";
            for (int i = 0; i < arrayList.size(); i++) {
                text += arrayList.get(i);
            }
        }
        return text;
    }
	
	public static String[] generate_equation(int originFirstNum, int boundFirstNum, int originSecondNum, int boundSecondNum, String typeOfOperation, int lastNumber1, int lastNumber2) {
        Random rand = new Random();
        int num1 = rand.nextInt(originFirstNum, boundFirstNum);
        int num2 = rand.nextInt(originSecondNum, boundSecondNum);
        while ((num1 == lastNumber1) || (num2 == lastNumber2)) {
            if (num1 == lastNumber1) {
                num1 = rand.nextInt(originFirstNum, boundFirstNum);
            }
            if (num2 == lastNumber2) {
                num2 = rand.nextInt(originSecondNum, boundSecondNum);
            }
        }
        double result = 0;
        switch (typeOfOperation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = (double) num1 / (double) num2;
        }

        String[] arr = {String.valueOf(num1) + " " + typeOfOperation + " " +  String.valueOf(num2), String.valueOf(result)};
        return arr;
    }
	
}
