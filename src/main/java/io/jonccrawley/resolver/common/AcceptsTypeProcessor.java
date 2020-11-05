package io.jonccrawley.resolver.common;

import com.google.common.base.Splitter;
import java.util.*;

public class AcceptsTypeProcessor {

    AcceptsTypeProcessor () {}

    public static Map<Double, List<String>> process(String accepts) {

        Map<Double, List<String>> results = new HashMap<>();

        List<String> mimeTypes = Splitter.on(',')
                .omitEmptyStrings()
                .splitToList(accepts);

        for (String mimeType : mimeTypes) {
            if( mimeType.contains("q=")) {
                List<String> rankedType = Splitter.on(';')
                        .omitEmptyStrings()
                        .splitToList(mimeType);
                if(rankedType.size()==2){
                    processValues(rankedType,results);
                }else{
                    System.out.println("Shit Went Poorly, poopity scoop");
                }


            } else {
                addToResults(results,1.0,mimeType);
            }
        }

        return results;
    }

    private static void processValues(List<String> rankedType,Map<Double, List<String>> results){
        Double ranking = null;
        String rankedMimeType = null;
        for(String currentValue : rankedType){
            if( currentValue.contains("q=")) {
                ranking = Double.parseDouble(currentValue.replace("q=",""));
            } else {
                rankedMimeType = currentValue;
            }
        }
        addToResults(results,ranking,rankedMimeType);
    }

    private static void addToResults(Map<Double, List<String>> currentResults, Double ranking, String type){
        if(currentResults.containsKey(ranking)){
            currentResults.get(ranking).add(type);
        }else{
            List<String> newMimeList = new ArrayList<>();
            newMimeList.add(type);
            currentResults.put(ranking,newMimeList);
        }
    }

}
