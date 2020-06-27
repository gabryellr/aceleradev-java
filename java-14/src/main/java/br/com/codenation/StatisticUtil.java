package br.com.codenation;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;

public class StatisticUtil {

    private static final int DEFAULT_VALUE = 0;

    public static int average(int[] elements) {
        return (int) stream(elements).average().orElseThrow(RuntimeException::new);
    }

    public static int mode(int[] elements) {
        Map<Integer, Integer> numbersMap = new HashMap<>();

        stream(elements).forEach(i -> numbersMap.put(i, DEFAULT_VALUE));

        for (int element : elements) {
            if (numbersMap.containsKey(element)) {
                Integer elementQuantity = numbersMap.getOrDefault(element, DEFAULT_VALUE);
                numbersMap.put(element, elementQuantity + 1);
            }
        }

        return numbersMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public static int median(int[] elements) {
        Arrays.sort(elements);
        int elementsLength = elements.length;

        if (elementsLength % 2 != 0) {
            return elements[((elementsLength - 1) / 2)];
        }

        int firstElement = elements[elementsLength / 2];
        int secondElement = elements[(elementsLength / 2) - 1];
        return (firstElement + secondElement) / 2;
    }

}