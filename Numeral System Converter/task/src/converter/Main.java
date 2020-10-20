package converter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int sourceBase = sc.nextInt();
            String num = sc.next();
            int targetBase = sc.nextInt();
            String intNum = "";
            String decNum = "";
            
            if (sourceBase <= 0 || targetBase <= 0 || sourceBase > 36 || targetBase > 36) {
                throw new Exception();
            }
        
            StringBuilder decimalNum = new StringBuilder("");
            int dpLoc = num.indexOf(".");
            if (dpLoc >= 0) {
                intNum = num.substring(0, dpLoc);
                String deciNum = num.substring(dpLoc);
                decNum = decimalNum.append(deciNum).toString();
            } else {
                intNum = num;
            }
        
            String intResult = IntBaseConverter(sourceBase, intNum, targetBase);
            String decResult = "";
            String finalResult = "";
            if (decNum != "") {
                decResult = fractionBaseConverter(sourceBase, decNum, targetBase);
                finalResult = intResult + decResult;
            } else {
                finalResult = intResult;
                }
            System.out.println(finalResult);
        } catch (Exception e) {
            System.out.println("error");
        }
        
    }
        
    public static String IntBaseConverter(int sourceBase, String num, int targetBase) {
        if (sourceBase == 1 && targetBase != 1) {
            long numlength = num.length();
            String numLengthString = Long.toString(numlength);
            if (sourceBase == 1) {  
                long decNum = Long.parseLong(numLengthString, 10);
                return Long.toString(decNum, targetBase);
            } else {
                long numParse = Long.parseLong(num);
                    return Long.toString(numParse);
            }
        } else if (targetBase == 1) {
            StringBuilder sb = new StringBuilder();
            int decNum = Integer.parseInt(num);
            for (int i = 0; i < decNum; i++) {
                sb.append(1);
            }
            return sb.toString();
        } else {
            int decNum = Integer.parseInt(num, sourceBase);
            return Integer.toString(decNum, targetBase);
        }
    }
    
    public static String fractionBaseConverter(int sourceBase, String decNum, int targetBase) {
        double deciNumFloat = 0;
        if (sourceBase == 10) {
            deciNumFloat += Double.parseDouble(decNum);
            
        } else {
            for (int i = 1; i < decNum.length(); i++) { 
                deciNumFloat += (Character.getNumericValue(decNum.charAt(i)) / Math.pow(sourceBase, i));
            } 
            
        }
            
        StringBuilder decResult = new StringBuilder(".");
        for (int i = 0; i < 5; i++) {
            deciNumFloat *= targetBase;
            int number = (int) (deciNumFloat);
            deciNumFloat -= number;
            String number1 = Integer.toString(number, targetBase);
            decResult.append(number1);
        }
        return decResult.toString();
    }
}
