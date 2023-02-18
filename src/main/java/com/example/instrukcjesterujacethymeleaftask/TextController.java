package com.example.instrukcjesterujacethymeleaftask;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TextController {

    private final TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping("/")
    String home(){
        return "index";
    }

    @PostMapping("/stats")
    String getTextStats(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) boolean length,
            @RequestParam(required = false) boolean words,
            @RequestParam(required = false) boolean isPalindrome,
            @RequestParam(required = false) boolean mostPopularWord,
            Model model
    ){
        if(text == null || text.isEmpty()){
            return "empty-text";
        }
        TextStatsDto textStat = textService.getTextStats(text,length,words,isPalindrome,mostPopularWord);
        model.addAttribute("textStat",textStat);
        return "stats";
    }
}
