package util;

import java.util.Random;

public class RandomDataGenerator {
    private static final String DEFAULT_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final Random random;
    private final String alphabet;

    public RandomDataGenerator(long seed) {
        this(seed, DEFAULT_ALPHABET);
    }

    public RandomDataGenerator(long seed, String alphabet) {
        this.random = new Random(seed);
        this.alphabet = alphabet;
    }

    public String generateString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }

    public String getAlphabet() {
        return alphabet;
    }
}