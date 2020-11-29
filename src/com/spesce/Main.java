package com.spesce;

import java.util.Hashtable;

public class Main {

    public static void main(String[] args) throws Exception {
        String[] expressions = {
                "25 - 5",
                "20 + 5",
                "30 / 10",
                "3 x 6",
                "2 ^ 5"
        };
        for(String exp : expressions){
            System.out.print(exp + " = ");
            System.out.println(BigInt.calculate(exp));
        }
        //System.out.println("2 ^ 3 = " + BigInt.power("2","3"));
    }
//    private static void testDivide() {
//        final int dividend = 21;
//        final int divisor = 7;
//        BigInt bi = new BigInt(dividend);
//        bi.divideBy(divisor);
//        System.out.println(dividend + " / " + divisor + " == " + bi.getValue());
//    }
//    private static void testFact(){
//        for(int i = 0; i < 50; i++)
//        {
//            System.out.println("Fac(" + i + ") := " + BigInt.factorial(i).getValue());
//        }
//    }
//
//
//    private static String[][] setup(){
//        String[][] nums =
//                {
//                        {"50","20"},
//                        {"50001","3001"},
//                        {"21","3"},
//                        {BigInt.factorial(20).getValue(),"2000"},
//                        {"1","0"},
//                        {"20000000","20000"},
//                        {"50000","50000"}
//                };
//        return nums;
//    }
//
//    private static void testCalc(Character operation) {
//        String[][] nums = setup();
//        System.out.println("-----------  Testing operation '" + operation + "'  -------------");
//        for(String[] pair : nums)
//            System.out.println(doOperation(operation,pair[0],pair[1]));
//        System.out.println("====================================================");
//    }

}
