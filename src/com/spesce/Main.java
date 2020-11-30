package com.spesce;

import java.util.Hashtable;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("---------- Testing Factorial --------------");
        for(int i = 80; i < 100 ; i++)
            System.out.println("fact("+i+"):= " + BigInt.factorial(i).getValue());

    }
    private static void testExpressions(){
        String[] expressions = {
                "25 - 5",
                "20 + 5",
                "30 / 10",
                "3 x 6",
                "2 ^ 5",
                BigInt.factorial(20).getValue() + " x 10",
                BigInt.factorial(19).getValue() + " + 121",
                BigInt.factorial(35).getValue() + " x 2"
        };
        for(String exp : expressions){
            System.out.print(exp + " = ");
            System.out.println(BigInt.calculate(exp));
        }
    }
}
