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
public class EucNormJTop extends JTop{
    public EucNormJTop(int j, String word, Map<String, Map<String, Integer>> uniqueVectors){
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
                similarities.put((String)mEntry.getKey(), this.compare.EuclideanNormalized(wordVector, v));
            }
        }
        return similarities;
    }
    
    public Map<String, Double> getEucNormJTop(int j, String word, Map<String, Map<String, Integer>> uniqueVectors){
        return super.getTopJ();
    }
}
