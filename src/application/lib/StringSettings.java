package application.lib;

import java.util.ArrayList;

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
	
}
