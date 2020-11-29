package com.spesce;

import java.util.Hashtable;

public class Main {

    public static void main(String[] args) throws Exception {
        Character[] operations = {'x','+','-','/'};
        for(char op : operations)
            testCalc(op);
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

    private static String doOperation(Character type,String x, String y) {
        Hashtable<Character,String> op = new Hashtable<Character,String>();
        op.put('+',BigInt.add(x,y));
        op.put('-',BigInt.subtract(x,y));
        op.put('x',BigInt.multiply(x,y));
        op.put('/',BigInt.divide(x,y));

        return x + " " + type + " " + y + " = " + op.get(type);
    }

    private static String[][] setup(){
        String[][] nums =
                {
                        {"50","20"},
                        {"50001","3001"},
                        {"21","3"},
                        {BigInt.Factorial(20).getValue(),"2000"},
                        {"1","0"},
                        {"20000000","20000"},
                        {"50000","50000"}
                };
        return nums;
    }

    private static void testCalc(Character operation) {
        String[][] nums = setup();
        System.out.println("-----------  Testing operation '" + operation + "'  -------------");
        for(String[] pair : nums)
            System.out.println(doOperation(operation,pair[0],pair[1]));
        System.out.println("====================================================");
    }

}
