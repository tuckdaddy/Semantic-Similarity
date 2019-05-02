/*
 * Given a text	with n unique words denoted by (w1,w2,..., wn) and a word w, let descw be the	
 * semantic descriptor vector of w computed using the text. descw is an n-dimensional vector.
 * The i-th coordinate of descw is the number of sentences in which both w and	wi occur.
 */
package edu.uiowa.cs.similarity;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
/**
 *
 * @author tuck
 */
public final class Vector implements SetVector{
    Map<String, Map<String, Integer>> descriptor = new HashMap<>();
    Map<String,Integer> innerMap = new HashMap<>();
    private Iterator<String> word;
    private final int wordOccurance = 0;
    
    public Vector(){};
   public Vector(Map<String,Integer> mapOfSen, String key){
       setVector(mapOfSen, key);
   }
    @Override
    public Map<String, Map<String, Integer>> setVector(Map<String,Integer> mapOfSen, String key){
        
        Map<String, Integer> innerVector = new HashMap<>();
        Set<String> setOfSentences = mapOfSen.keySet();

        for(String sentence: setOfSentences){ 
            if(sentence.contains(key)){
                String[] wordInSen = sentence.replaceAll("[\\[ \\]]", "").replaceAll(",", " ").trim().split(" ");
                for(String wurd: wordInSen){
                    if(!wurd.equals(key)){
                    innerVector.put(wurd, wordOccurance);
                    int value  = innerVector.get(wurd);
                    innerVector.put(wurd, ++value);
                    }
                } 
            }
        } 
        descriptor.put(key, innerVector);
        return descriptor;
    }
   
    public Map<String, Integer> getVector(String key){
        return descriptor.get(key);
    }
    public Map<String,Integer> getVector(){
        return this.innerMap;
    }
    public String getWord(){
        return this.word.next();
    }
    public Map<String, Map<String, Integer>> getDescriptor(){
        return descriptor;
    }
}