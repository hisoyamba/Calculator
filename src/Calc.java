import java.util.*;
public class Calc {
    private static boolean isArabic = false;
    private static boolean isRoman = false;
    private static LinkedHashMap<Integer, String> map = new LinkedHashMap<>();
    static {
        map.put(10, "X");
        map.put(9, "IX");
        map.put(8, "VIII");
        map.put(7, "VII");
        map.put(6, "VI");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(3, "III");
        map.put(2, "II");
        map.put(1, "I");
    }

    private static List<Integer> convertRomanToArabic(List<String> n) {
        List<Integer> intNumbers = new ArrayList<>();
        for (String number : n) {
            for (Map.Entry<Integer, String> a : map.entrySet()) {
                if (number.equals(a.getValue())) {
                    intNumbers.add(a.getKey());
                }
            }
        }
        return intNumbers;
    }


    public static String calc(String input) {
        try {
            input = input.trim();
            String[] inputStringArray = input.split(" ");
            if (inputStringArray.length < 3)
                throw new IllegalArgumentException("cтрока не является математической операцией");
            if (inputStringArray.length > 3)
                throw new IllegalArgumentException("формат математической операции не удовлетворяет заданию");
            List<String> inputStringList = Arrays.asList(inputStringArray);
            List<String> inputStringListCopy = new ArrayList<>(inputStringList);
            String operator = inputStringListCopy.get(1);
            inputStringListCopy.remove(1);
            List<String> romanNumbers = new ArrayList<>();
            List<Integer> arabicNumbers = new ArrayList<>();
            boolean invalidNumber = false;

            for (String i : inputStringListCopy) { // проверка числового формата
                if (map.containsValue(i)) {
                    romanNumbers.add(i);
                } else {
                    try {
                        int arabic = Integer.parseInt(i);

                        if (map.containsKey(arabic)) {
                            arabicNumbers.add(arabic);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("задан неверный числовой формат");
                    }
                }
            }
            if (romanNumbers.size() != 2 && arabicNumbers.size() != 2)
                throw new IllegalArgumentException("используются одновременно разные системы счисления");
            isRoman = romanNumbers.size() == 2;
            isArabic = arabicNumbers.size() == 2;
            List<Integer> integers = null;
            if (isRoman) {
                integers = convertRomanToArabic(romanNumbers);
            } else if (isArabic) {
                integers = arabicNumbers;
            }

            int result = 0;
            switch (operator) {
                case "+":
                    result = integers.get(0) + integers.get(1);
                    break;
                case "-":
                    result = integers.get(0) - integers.get(1);
                    break;
                case "/":
                    result = integers.get(0) / integers.get(1);
                    break;
                case "*":
                    result = integers.get(0) * integers.get(1);
                    break;
                default:
                    throw new IllegalArgumentException("неверный оператор");
            }
            if (isRoman && result < 1) throw new IllegalArgumentException("в римской системе нет отрицательных чисел");

            String resultFinal = ""; // возврат результата
            if (isArabic) {
                resultFinal = String.valueOf(result);
            } else if (isRoman) {
                resultFinal = convertArabicToRoman(result);
            }
            return resultFinal;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (Exception e)  {
            return "Произошла какая-то хуйня: " + e.getMessage();
        }

    }
    private static String convertArabicToRoman(int result) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> a : map.entrySet()) {
            while (result >= a.getKey()) {
                sb.append(a.getValue());
                result -= a.getKey();
            }
        }
        String strRoman = sb.toString();
        return strRoman;
    }

}
