/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import javafx.util.Pair;
import opennlp.tools.stemmer.PorterStemmer;

/**
 *
 * @author connoreschrich
 */
public abstract class JTop {
    private int j = 0;
    public String word = null;
    private Map<String, Map<String, Integer>> uniqueVectors = new HashMap();
    public CompareVectors compare;
    
    public JTop(int j, String word, Map<String, Map<String, Integer>> uniqueVectors){
        this.j = j;
        this.word = word;
        this.uniqueVectors = uniqueVectors;
        this.compare = new CompareVectors();
    }
    
    public Map<String, Double> getTopJ(){
        PorterStemmer stemmer = new PorterStemmer();
        Map<String, Integer> wordVector = null;
        Map<String, Double> similarities = new HashMap<>();
        Map<String, Double> jSimilarities = new HashMap<>();
        
        Iterator uniqueVectorIterator = uniqueVectors.entrySet().iterator();
        while (uniqueVectorIterator.hasNext()){
            Map.Entry mEntry = (Map.Entry)uniqueVectorIterator.next();
            Map<String, Integer> v = (Map<String, Integer>)mEntry.getValue();
           if (mEntry.getKey().equals(stemmer.stem(word))){
               wordVector = v;
           }
        }
        
        similarities = getSimilarities(uniqueVectors, wordVector);
        
        similarities = sortByComparator(similarities, false);
        
        Iterator similaritiesIterator = similarities.entrySet().iterator();
        int i = 0;
        while (similaritiesIterator.hasNext() && i < j){
            Entry mEntry = (Entry)similaritiesIterator.next();
            jSimilarities.put((String)mEntry.getKey(), (Double)mEntry.getValue());
            i++;
        }
        
        return jSimilarities;
    }

    private static Map<String, Double> sortByComparator(Map<String, Double> unsortMap, final boolean order)
    {

        List<Entry<String, Double>> list = new LinkedList<Entry<String, Double>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Double>>()
        {
            @Override
            public int compare(Entry<String, Double> o1,
                    Entry<String, Double> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Entry<String, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    
    public abstract Map<String, Double> getSimilarities(Map<String, Map<String, Integer>> uniqueVectors, Map<String, Integer> wordVector);
}
