package com.spesce;

/**
 * Created By Steve P. on 11/29/2020
 * original package: com.spesce
 * ------------------------------------------------------------------------------------------
 */
public class BigCalc {


    //add 2 numbers
    //ex: add("5","10) :=> "15"
    public static String add(String x,String y)
    {
        String[] xy = equalLengths(x, y);
        char[] xChars = xy[0].toCharArray();
        char[] yChars = xy[1].toCharArray();

        int overflow = 0;

        StringBuilder solution = new StringBuilder();
        for(int i = xChars.length - 1; i >= 0 ; i--)
        {
            int xInt = Character.getNumericValue(xChars[i]);
            int yInt = Character.getNumericValue(yChars[i]);
            int digitSum = xInt + yInt + overflow;

            solution.append( digitSum % 10);
            overflow = digitSum / 10;
        }
        return (overflow == 0 ? "" : "1") + solution.reverse().toString();
    }
    //Subtract y FROM x
    // EX: subtract("30","20") :=> "10"
    public static String subtract(String x, String y){
        int yGThanX = compareNumerically(x,y);
        if (yGThanX == 0)//numbers are equal, diff is zero
            return "0";
        else if(yGThanX < 0)//diff would be negative
            return "ERROR, negative not implemented";

        //perform subtraction
        String[] xy = equalLengths(y,x);
        char[] xChars = xy[0].toCharArray();
        char[] yChars = xy[1].toCharArray();

        int overflow = 0;
        StringBuilder difference = new StringBuilder();
        for(int i = xChars.length - 1 ; i >= 0 ; i--)//subtraction happens in reverse order
        {
            int xInt = Character.getNumericValue(xChars[i]);
            int yInt = Character.getNumericValue(yChars[i]) + overflow;
            overflow = 0;//either 0 or -1, -1 when you need to take x 1 from the next 10s place up

            if(yInt < xInt)
            {
                overflow = -1;
                yInt += 10;
            }

            difference.append(yInt - xInt);
        }
        //trim zeroes
        //note the string builder is in reverse order, so leading zeroes are now at the end
        while(difference.charAt(difference.length() - 1) == '0')
            difference.deleteCharAt(difference.length() - 1);

        return difference.reverse().toString();
    }
    //multiply x * y
    //ex multiply("7","3") == multiply("3","7") :=> "21"
    public static String multiply(String x, String y) {
        char[] xChars = y.toCharArray();
        String sum = "";
        for(int i = 0; i < y.length(); i++)
        {
            int digit = Character.getNumericValue(xChars[xChars.length - 1 - i]);

            sum = add(y,multiplyByInt(digit, x, i));
        }
        return sum;
    }
    //divide by and set to new value
    public String divide(String dividend,int divisor) {
        StringBuilder quotient = new StringBuilder();

        char[] dividendChars = dividend.toCharArray();

        int overflow = 0;

        for(int i = 0; i < dividend.length() ; i++)
        {
            int digit = overflow * 10 + Character.getNumericValue(dividendChars[i]);
            quotient.append(digit / divisor);
            overflow = digit % divisor;
        }
        // //trim zeroes
        while(quotient.charAt(0) == '0')
            quotient.deleteCharAt(0);
        return quotient.toString();
    }

    //:::::::::::::::::: Helpers ::::::::::::::::::::::::::::>
    //overloading to avoid extra params
    private static String multiplyByInt(int x, String num, int powerOf10) {
        return multiplyByInt(x, num, powerOf10, 0 , new StringBuilder());
    }
    //pops last digit, multiplies by x, adds it to string builder, sb to string on exit condition
    private static String multiplyByInt(int x, String y,  int powerOf10, int overflow, StringBuilder sb)
    {  //recursive exit cond. will return string from sb, add overflow if nonzero
        if(y.length() == 0)
        {
            return (overflow == 0 ? "" : Integer.toString(overflow))
                    + sb.reverse().toString()
                    + "0".repeat(powerOf10);
        }

        //add last digit * x to string builder
        final char digit = y.toCharArray()[y.length() - 1];
        final int product = Character.getNumericValue(digit) * x + overflow;
        sb.append(product % 10);

        //y loses last char, overflow calculated from product
        return multiplyByInt(x, y.substring(0, y.length() - 1), powerOf10, product / 10 , sb);
    }

    /*
    * Is x > y numerically?
    * Similar to compareTo() method, but static
    * examples --------------------
    * [equal]: compareNumerically("5","5") :=> 0
    * [x > y]: compareNumerically("5","1") :=> 1
    * [x < y]: compareNumerically("1","5") :=> -1
    * */
    public static int compareNumerically(String x, String y){
        if(x.length() > y.length())
            return 1;
        if(x.length() < y.length())
            return -1;

        char[] yChars = x.toCharArray();
        char[] xChars = y.toCharArray();

        for(int i = 0; i < x.length() ; i++)
        {
            int xInt = Character.getNumericValue(xChars[i]);
            int yInt = Character.getNumericValue(yChars[i]);

            if(yInt > xInt) return 1;
            if(yInt < xInt) return -1;
        }
        return 0;
    }

    //return two numbers as Strings of the same length (adds zeroes to front if shorter than other)
    private static String[] equalLengths(String x, String y){

        if(x.length() > y.length())
            y =  "0".repeat(x.length() - y.length()) + y;
        else if(x.length() < y.length())
            x = "0".repeat(y.length() - x.length()) + x;

        return new String[] {x,y};
    }
}
