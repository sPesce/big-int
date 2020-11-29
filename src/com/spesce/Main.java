package com.spesce;

public class Main {

    public static void main(String[] args) throws Exception {
        testDivide();
        testAddCalc();
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

    private static void testAddCalc(){
        String[][] nums =
                {
                        {"20","50"},
                        {"3001","50001"},
                        {BigInt.Factorial(10).getValue(), "30105"},
                        {"1","0"},
                        {"2010",BigInt.Factorial(30).getValue()}
                };

        for(String[] pair : nums)
        {
            String a = pair[0]; String b = pair[1];
            System.out.println(a + " + " + b + " = " + BigCalc.add(a,b));
        }
    }
}
