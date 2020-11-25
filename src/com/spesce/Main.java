package com.spesce;

public class Main {

    public static void main(String[] args) throws Exception {
        testDivide();
    }
    private static void testDivide() {
        final int dividend = 21;
        final int divisor = 7;
        BigInt bi = new BigInt(dividend);
        bi.divideBy(divisor);
        System.out.println(dividend + " / " + divisor + " == " + bi.getValue());
    }
}
