package ru.rehtang.first;

import java.util.Scanner;

public class Main {

  public static void reverseString(String stringinput) {

    char[] resultarray = stringinput.toCharArray();

    System.out.println(stringinput);
    for (int i = resultarray.length - 1; i >= 0; i--) System.out.print(resultarray[i]);
    System.out.println("\n");
  }

  public static void main(String[] arg) {
    Scanner in = new Scanner(System.in);
    String phrase = in.nextLine();

    long m = System.currentTimeMillis();

    for (int i = 0; i < 1000; i++) {
      reverseString(phrase);
    }

    long a = System.currentTimeMillis() - m;
    m = System.currentTimeMillis();

    for (int i = 0; i < 10000; i++) {
      reverseString(phrase);
    }

    long b = System.currentTimeMillis() - m;
    m = System.currentTimeMillis();

    for (int i = 0; i < 100000; i++) {
      reverseString(phrase);
    }

    long c = System.currentTimeMillis() - m;

    reverseString(phrase);
    System.out.println((double) a + " " + b + " " + c);
  }
}
