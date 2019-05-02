/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import opennlp.tools.stemmer.PorterStemmer;

/**
 *
 * @author connoreschrich
 */
public class EucJTop extends JTop{
    public EucJTop(int j, String word, Map<String, Map<String, Integer>> uniqueVectors){
        super(j, word, uniqueVectors);
    }
    
    @Override
    public Map<String, Double> getSimilarities(Map<String, Map<String, Integer>> uniqueVectors, Map<String, Integer> wordVector){
        PorterStemmer stemmer = new PorterStemmer();
        Iterator uniqueVectorIterator = uniqueVectors.entrySet().iterator();
        Map<String, Double> similarities = new HashMap<>();
        
        while (uniqueVectorIterator.hasNext()){
            Map.Entry mEntry = (Map.Entry)uniqueVectorIterator.next();
            Map<String, Integer> v = (Map<String, Integer>)mEntry.getValue();
            if (!mEntry.getKey().equals(stemmer.stem(word))){
                similarities.put((String)mEntry.getKey(), this.compare.Euclidean(wordVector, v));
            }
        }
        return similarities;
    }
    
    public Map<String, Double> getEucJTop(int j, String word, Map<String, Map<String, Integer>> uniqueVectors){
        return super.getTopJ();
    }
}
