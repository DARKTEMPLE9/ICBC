package net.northking.iacmp.utils;

public class RandomDigit {
    private RandomDigit() {
    }

    public static String number() {
        long n = System.nanoTime();
        long k = n % 640;
        String str = String.format("%03d", k);
        return str;

    }
}
