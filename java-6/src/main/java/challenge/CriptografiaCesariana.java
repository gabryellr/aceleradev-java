package challenge;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Character.isAlphabetic;

public class CriptografiaCesariana implements Criptografia {

    private static final int TOTAL_ALPHABET_LETTERS = 26;
    private static final TreeMap<Integer, Character> alphabet;
    public static final int SHIFT_NUMBER_TO_ENCRYPT = -3;
    public static final int SHIFT_NUMBER_TO_DECRYPT = 3;

    static {
        TreeMap<Integer, Character> createAlphabet = new TreeMap<>();
        char letter = 'a';

        createAlphabet.put(1, letter);

        for (int i = 1; i <= TOTAL_ALPHABET_LETTERS; i++) {
            createAlphabet.put(i, letter);
            letter++;
        }
        alphabet = createAlphabet;
    }

    @Override
    public String criptografar(String texto) {
        return decryptOrEncryptMessage(texto, SHIFT_NUMBER_TO_ENCRYPT);
    }

    @Override
    public String descriptografar(String texto) {
        return decryptOrEncryptMessage(texto, SHIFT_NUMBER_TO_DECRYPT);
    }

    private void checkNullTextOrEmpty(String texto) {
        if (texto == null) {
            throw new NullPointerException();
        } else if (texto.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    private String decryptOrEncryptMessage(String texto, int shiftNumber) {
        StringBuilder message = new StringBuilder();
        checkNullTextOrEmpty(texto);
        char[] messageReceived = texto.toLowerCase().toCharArray();

        List<Integer> integers = Arrays.asList();

        for (char messageLetter : messageReceived) {
            for (Map.Entry<Integer, Character> entry : alphabet.entrySet()) {
                char alphabetChar = entry.getValue();
                Integer charAlphabetNumber = entry.getKey();
                if (!isAlphabetic(messageLetter)) {
                    message.append(messageLetter);
                    break;
                }
                checkMessageLetterInAlphabetAndAdded(shiftNumber, messageLetter, alphabetChar, charAlphabetNumber, message);
            }
        }
        return message.toString();
    }

    private void checkMessageLetterInAlphabetAndAdded(int shiftNumber, char messageLetter, char alphabetChar,
                                                      Integer charAlphabetNumber, StringBuilder message) {
        int realLetterNumber;

        if (messageLetter == alphabetChar) {
            realLetterNumber = charAlphabetNumber - shiftNumber;
            if (realLetterNumber <= 0) {
                addLetter(realLetterNumber, message);
            } else {
                message.append(getTrueLetter(realLetterNumber));
            }
        }
    }

    private void addLetter(int realLetterNumber, StringBuilder message) {
        int letterNumberDecreased = realLetterNumber + TOTAL_ALPHABET_LETTERS;
        Character trueLetter = getTrueLetter(letterNumberDecreased);
        message.append(trueLetter);
    }

    private Character getTrueLetter(int letterNumberDecreased) {
        return alphabet.get(letterNumberDecreased);
    }

}
