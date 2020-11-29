package com.spesce;

/**
 * Created By Steve P. on 11/29/2020
 * original package: com.spesce
 * ------------------------------------------------------------------------------------------
 */
public class BigCalc {



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

    public static String minus(BigInt x){
        int thisGThanX = this.compareTo(x);
        if (thisGThanX == 0)//numbers are equal, diff is zero
            this.setValue("0");
        else if(thisGThanX < 0)//diff would be negative
            this.setValue("ERROR, negative not implemented");
        else
        {
            String[] xThis = equalLengths(x, this);
            char[] xChars = xThis[0].toCharArray();
            char[] thisChars = xThis[1].toCharArray();

            int overflow = 0;
            StringBuilder difference = new StringBuilder();
            for(int i = xChars.length - 1 ; i >= 0 ; i--)//subtraction happens in reverse order
            {
                int xInt = Character.getNumericValue(xChars[i]);
                int thisInt = Character.getNumericValue(thisChars[i]) + overflow;
                overflow = 0;//either 0 or -1, -1 when you need to take a 1 from the next 10s place up

                if(thisInt < xInt)
                {
                    overflow = -1;
                    thisInt += 10;
                }

                difference.append(thisInt - xInt);
            }
            //trim zeroes
            //note the string builder is in reverse order, so leading zeroes are now at the end
            while(difference.charAt(difference.length() - 1) == '0')
                difference.deleteCharAt(difference.length() - 1);

            this.setValue(difference.reverse().toString());
        }
    }

    //:::::::::::::::::: Helpers ::::::::::::::::::::::::::::>
    public static int compareNumerically(String y, String x){
        if(y.length() > x.length())
            return 1;
        if(y.length() < x.length())
            return -1;

        char[] yChars = y.toCharArray();
        char[] xChars = x.toCharArray();

        for(int i = 0; i < y.length() ; i++)
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
