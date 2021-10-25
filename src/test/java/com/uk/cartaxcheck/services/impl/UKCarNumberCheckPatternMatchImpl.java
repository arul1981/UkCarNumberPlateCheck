package com.uk.cartaxcheck.services.impl;

import com.uk.cartaxcheck.services.PatternMatch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UKCarNumberCheckPatternMatchImpl implements PatternMatch {

    @Value("${uk.numberplate.match.regexp}")
    private String ukPatternMatchRegExp;

    public Pattern getPatternCompile(String pattern){
        return Pattern.compile(pattern);
    }

    public void getMatcherGroup(List<String> values, String line){
        Matcher atMatcher = getPatternCompile(ukPatternMatchRegExp).matcher(line);
        while(atMatcher.find()){
            values.add(atMatcher.group());
        }
    }

}
