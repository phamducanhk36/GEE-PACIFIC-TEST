package test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main1 {

    public static final String STRING_1_NUMBER_SEPARATOR = "&";
    public static final String STRING_1_PART_SEPARATIOR = "\\|";
    public static final String STRING_2_NUMBER_SEPARATOR = ",";
    public static final String STRING_2_PART_SEPARATOR = "-";

    public static void main(String[] args) {
        process("1&2&3|7&8", "02-17,03-38,05-37,27");
    }

    public static void process(String s1, String s2) {
        String[] s1Split = s1.split(STRING_1_PART_SEPARATIOR);
        if (s1Split.length != 2) {
            System.out.println("Invalid String 1");
            return;
        }
        String[] s11numbers = s1Split[0].split(STRING_1_NUMBER_SEPARATOR);
        String[] s12numbers = s1Split[1].split(STRING_1_NUMBER_SEPARATOR);
        List<Integer> list1 = new ArrayList<>();
        for (String i : s11numbers) {
            for (String j : s12numbers) {
                try {
                    list1.add(Integer.parseInt(i + j));
                } catch (Exception e) {
                    System.out.println("Invalid number: " + i + "; " + j);
                }
            }
        }
        System.out.println(list1);

        HashMap<Integer, Integer> map2 = new HashMap<>();
        String[] s2Split = s2.split(STRING_2_NUMBER_SEPARATOR);
        for (String s : s2Split) {
            String[] s2Numbers = s.split(STRING_2_PART_SEPARATOR);
            if (s2Numbers.length > 1)
                map2.put(Integer.parseInt(s2Numbers[1]), Integer.parseInt(s2Numbers[0]));
            else
                map2.put(Integer.parseInt(s2Numbers[0]), 1);
        }
        System.out.println(map2);

        int rs = 0;
        for (Integer n : map2.keySet()) {
            if (list1.contains(n)) {
                rs += map2.get(n);
            }
        }
        System.out.println("Result: " + rs);
    }
}
