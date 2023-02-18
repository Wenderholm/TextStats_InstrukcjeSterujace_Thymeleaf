package com.example.instrukcjesterujacethymeleaftask;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TextService {
    TextStatsDto getTextStats(
            String text,
            boolean length,
            boolean words,
            boolean palindrome,
            boolean mostPopularWord
    ){
        TextStatsDto textStat = new TextStatsDto(text);
        if(length){
            textStat.setLength(text.length());
        }
        if(words){
            textStat.setWords(countWords(text));
        }
        if(palindrome){
            textStat.setPalindrome(checkPalindrome(text));
        }
        if(mostPopularWord){
            Map.Entry<String,Long> mostPopularWordInText = findMostPopularWord(text).get();
            textStat.setMostPopularWord(mostPopularWordInText.getKey());
            textStat.setMostPopularWordCount(mostPopularWordInText.getValue().intValue());
        }

        return textStat;
    }

    private Integer countWords(String text) {
        return text.split(" ").length;
    }

    private boolean checkPalindrome(String text) {
        String reversText = new StringBuilder(text).reverse().toString();
        return text.equals(reversText);
    }

    private Optional<Map.Entry<String,Long>> findMostPopularWord(String text) {
        String[] words = text.split(" ");
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(
                        elem -> elem,
//                        Function.identity()
                        Collectors.counting()
                ))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    }


}
