package com.spesce;

public class Main {

    public static void main(String[] args) throws Exception {
        testDivide();
        testAddSubtractCalc(false);
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
        String[][] nums =
                {
                        {"50","20"},
                        {"50001","3001"},
                        {BigInt.Factorial(10).getValue(), "30105"},
                        {"1","0"},
                        {"30000315015011051959195","30000315015011051959195"},
                        {BigInt.Factorial(30).getValue(),BigInt.Factorial(5).getValue()}
                };

        for(String[] pair : nums)
        {
            String a = pair[0]; String b = pair[1];
            String result = a + (add ? " + " : " - ") + b +
                    " = " + (add ? BigCalc.add(a,b) : BigCalc.subtract(a,b));
            System.out.println(result);
        }
    }


}
