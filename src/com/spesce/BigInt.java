package com.spesce;

import java.util.Hashtable;

/**
 * Created By Steve P. on 11/25/2020
 * original package: com.spesce
 * ------------------------------------------------------------------------------------------
 */
public class BigInt implements Comparable<BigInt>{

    private String value;

    //::::::::::::::: Factory Methods :::::::::::::>
    BigInt(int value) {
        if( value < 0) System.out.println("Caution: negative numbers not currently implemented");
        this.value = Integer.toString(value);
    }//------------------
    BigInt(String value){
        this.value = value;
    }//------------------
    //return a BigInt obj with value of factorial(n)
    //factorial(n) :=> n * factorial(n-1) where f(forall n <= 1) = 1
    public static BigInt factorial(int n) {
        //recursively call this method, which calls recursive multiply method
        if(n > 1) {
            //n*Factorial(n)
            BigInt bigN = new BigInt(n);
            bigN.times(factorial(n - 1));
            return bigN;
        }
        return new BigInt(1);
    }//------------------

    //all of these static methods return strings (not BigInt)
    //::::::::::: Public Static Calculator Methods ::::::::::::::::>
    public static String multiply(String x, String y) {
        BigInt result = new BigInt(x);
        result.times(new BigInt(y));
        return result.getValue();
    }//------------------
    public static String divide(String x, String y){
        BigInt result = new BigInt(x);
        result.divideBy(Integer.parseInt(y));
        return result.getValue();
    }//------------------
    public static String add(String x, String y){
        BigInt result = new BigInt(x);
        result.plus(new BigInt(y));
        return result.getValue();
    }//------------------
    public static String subtract(String x,String y){
        BigInt result = new BigInt(x);
        result.minus(new BigInt(y));
        return result.getValue();
    }//------------------
    public static String power(String x, String y) {
        return power(x,Integer.parseInt(y));
    }//------------------
    public static String calculate(String expression) {
        String[] xOperatorY = expression.split(" ");
        String x = xOperatorY[0];
        String y = xOperatorY[2];
        char operator = xOperatorY[1].charAt(0);

        String result;
        switch(operator){
            case 'x':
                result = BigInt.multiply(x,y); break;
            case '+':
                result = BigInt.add(x,y); break;
            case '-':
                result = BigInt.subtract(x,y); break;
            case '/':
                result = BigInt.divide(x,y); break;
            case '^':
                result = BigInt.power(x,y); break;
            default : result = "Error, Invalid expression";
        };
        return result;
    }//------------------

    //:::::::::::::::: Implemented ::::::::::::::::::>
    //by value strings numberical value....
    //  this > x :=> 1
    //  this == x :=> 0
    //  this < x :=? -1
    @Override
    public int compareTo(BigInt x){
        if(this.length() > x.length())
            return 1;
        if(this.length() < x.length())
            return -1;

        char[] thisChars = this.chars();
        char[] xChars = x.chars();

        for(int i = 0; i < this.length() ; i++)
        {
            int xInt = Character.getNumericValue(xChars[i]);
            int thisInt = Character.getNumericValue(thisChars[i]);

            if(thisInt > xInt) return 1;
            if(thisInt < xInt) return -1;
        }
        return 0;
    }//------------------

    //:::::::::::: getters/setters/utility :::::::::::::>
    public String getValue () {return value;}
    //can set value with a string or int
    public void setValue(String value){this.value = value;}
    public void setValue(int value){this.value = Integer.toString(value);}
    //return array of chars from string value
    public char[] chars() { return this.value.toCharArray(); }
    //return length (int) of string value
    public int length(){ return value.length();}
    //make a deep copy by value
    public BigInt clone(){return new BigInt(this.getValue());}



    //:::::::::::::: Public Instance Methods ::::::::::::::::::::::>
    //Purpose: to operate on an instance of BigInt, mutating its
    //      value to the solution
    //Example: x.plus(y) => void
    //      x now has a value of x.value + y.value
    //Note: Does not Support negative numbers
    // Named how they are named because they are instance methods
    //TODO: negative number support
    //::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::>
    //this.value += x.value
    public void plus(BigInt x)
    {
        String[] xy = equalLengths(x, this);
        char[] xChars = xy[0].toCharArray();
        char[] yChars = xy[1].toCharArray();

        int overflow = 0;//number being carried over when sum goes over 10

        StringBuilder solution = new StringBuilder();
        for(int i = xChars.length - 1; i >= 0 ; i--)
        {
            int xInt = Character.getNumericValue(xChars[i]);
            int yInt = Character.getNumericValue(yChars[i]);
            int digitSum = xInt + yInt + overflow;

            solution.append( digitSum % 10);
            overflow = digitSum / 10;
        }
        //append the number if overlow is nonzero (it can only be 1 or 0)
        //then reverse the string builder because they are added backwards
        this.value = (overflow == 0 ? "" : "1") + solution.reverse().toString();
    }//------------------

    //this.value -= x.value
    public void minus(BigInt x){
        int thisGThanX = this.compareTo(x);
        if (thisGThanX == 0)//numbers are equal, difference is zero
            this.setValue("0");
        else if(thisGThanX < 0)//diff would be negative
            this.setValue("ERROR, negative not implemented");//TODO: implement negatives
        else
        {   //x and 'this' values in an array
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

                if(thisInt < xInt)//when you cant subtract, take from next highest digit
                {
                    overflow = -1;
                    thisInt += 10;
                }
                //we know that with the overflow added, we will always be able to subtract
                difference.append(thisInt - xInt);
            }
            //trim zeroes
            //note the string builder is in reverse order, so leading zeroes are now at the end
            while(difference.charAt(difference.length() - 1) == '0')
                difference.deleteCharAt(difference.length() - 1);

            this.setValue(difference.reverse().toString());
        }
    }//------------------

    //this.value *= x.value
    public void times(BigInt x) {
        char[] xChars = x.chars();
        String num = this.getValue();
        this.setValue(0);

        for(int i = 0; i < x.length(); i++)
        {
            int digit = Character.getNumericValue(xChars[xChars.length - 1 - i]);
            this.plus(new BigInt(multiplyByInt(digit, num, i)));
        }
    }//------------------

    //this.value = this to power of exp
    //exp not to be confused with math constant e
    //does not support negative exponents, but would be easy to add because we are using natural numbers
    public void power(int exp) { this.setValue(power(this.getValue(),exp)); }

    //divide by and set to new value
    public void divideBy(int divisor) {
        if(divisor == 0) this.setValue("undefined");
        else{
            StringBuilder quotient = new StringBuilder();

            char[] dividend = this.chars();

            int overflow = 0;

            for(int i = 0; i < dividend.length ; i++)
            {
                int digit = overflow * 10 + Character.getNumericValue(dividend[i]);
                quotient.append(digit / divisor);
                overflow = digit % divisor;
            }
            // //trim zeroes
            while(quotient.charAt(0) == '0')
                quotient.deleteCharAt(0);
            this.setValue(quotient.toString());
        }
    }//------------------

    //:::::::::::: private Helpers ::::::::::::::::::>
     // ----- Static Methods -------->
    //returns an array with 2 elements
    //adds zeroes to front of either number if it is shorter than other
    //examples:
    // equalLengths(new BigInt("50"),new BigInt("9")) :=> {"50","09"}
    // equalLengths(new BigInt("100201"),new BigInt("201")) :=> {"100201","000201"}
    // equalLengths(new BigInt("50"),new BigInt("32")) :=> {"50","32"}
    private static String[] equalLengths(BigInt x, BigInt y){
        String yVal;
        String xVal;

        if(x.length() == y.length()){
            yVal = y.getValue();
            xVal = x.getValue();
        } else if(x.length() > y.length())
        {
            xVal = x.getValue();
            yVal =  "0".repeat(x.length() - y.length()) + y.getValue();
        } else
        {
            yVal = y.getValue();
            xVal = "0".repeat(y.length() - x.length()) + x.getValue();
        }
        return new String[] {xVal,yVal};
    }//------------------

    //recursive exponential function
    private static String power(String x, int n){
        return (n <= 0) ? "1" : multiply(x,power(x,n - 1));
    }//------------------

    //overloading for default arguments
    private static String multiplyByInt(int x, String num, int powerOf10) {
        return multiplyByInt(x, num, powerOf10, 0 , new StringBuilder());
    }//------------------
    //pops last digit, multiplies by x, adds it to string builder, sb to string on exit condition
    private static String multiplyByInt(int x, String num,  int powerOf10, int overflow, StringBuilder sb)
    {  //recursive exit cond. will return string from sb, add overflow if nonzero
        if(num.length() == 0)
        {
            return (overflow == 0 ? "" : Integer.toString(overflow))
                    + sb.reverse().toString()
                    + "0".repeat(powerOf10);
        }
        //add last digit * x to string builder
        final char digit = num.toCharArray()[num.length() - 1];
        final int product = Character.getNumericValue(digit) * x + overflow;
        sb.append(product % 10);

        //num loses last char, overflow calculated from product
        return multiplyByInt(x, num.substring(0, num.length() - 1), powerOf10, product / 10 , sb);
    }//------------------
}





