package com.spesce;

import java.util.Hashtable;

public class Main {

    public static void main(String[] args) throws Exception {
        testCalc("x");
    }
    private static void testDivide() {
        final int dividend = 21;
        final int divisor = 7;
        BigInt bi = new BigInt(dividend);
        bi.divideBy(divisor);
        System.out.println(dividend + " / " + divisor + " == " + bi.getValue());
    }
    private static void testFact(){
        for(int i = 0; i < 50; i++)
        {
            System.out.println("Fac(" + i + ") := " + BigInt.Factorial(i).getValue());
        }
    }

    private static void testAddSubtractCalc(boolean add){
        String[][] nums = setup();

        for(String[] pair : nums)
        {
            String a = pair[0]; String b = pair[1];
            String result = a + (add ? " + " : " - ") + b +
                    " = " + (add ? BigCalc.add(a,b) : BigCalc.subtract(a,b));
            System.out.println();
        }
    }
    private static String doOperation(String type,String x, String y) {
        Hashtable<String,String> op = new Hashtable<String,String>();
        op.put("+",BigCalc.add(x,y));
        op.put("-",BigCalc.subtract(x,y));
        op.put("x",BigCalc.multiply(x,y));
        //op.put("/",4);

        return x + " " + type + " " + y + " = " + op.get(type);
    }

    private static String[][] setup(){
        String[][] nums =
                {
                        {"50","20"},
                        {"50001","3001"},
                        {BigInt.Factorial(10).getValue(), "30105"},
                        {"1","0"},
                        {"30000315015011051959195","30000315015011051959195"},
                        {BigInt.Factorial(30).getValue(),BigInt.Factorial(5).getValue()}
                };
        return nums;
    }

    private static void testCalc(String operation) {
        String[][] nums = setup();
        for(String[] pair : nums)
            System.out.println(doOperation(operation,pair[0],pair[1]));
    }


}
