package com.uk.cartaxcheck.helpers;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ApplicationHelper {

    public static final String carRegistrationRegExp = "[A-Z]{2}[0-9]{2} [A-Z]{3}[a-zA-Z]*|[A-Z]{2}[0-9]{2}[A-Z]{3}[a-zA-Z]*";


    public List<String> getAllMatchesInList() throws IOException {
        String fileLocation = "\\src\\test\\resources\\input\\car_input.txt";
        String iFile = System.getProperty("user.dir")+fileLocation;
        List<String> values = new ArrayList<>();

        Pattern atPattern = Pattern.compile(carRegistrationRegExp);
        BufferedReader br = new BufferedReader(new FileReader(iFile));
        String line;
        while((line = br.readLine()) != null) {
            Matcher atMatcher = atPattern.matcher(line);
            while(atMatcher.find()){
                values.add(atMatcher.group());
            }
        }
        values.forEach(System.out::println);
        return values;
    }
}
