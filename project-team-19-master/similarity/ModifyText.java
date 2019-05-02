/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import opennlp.tools.stemmer.PorterStemmer;

/**
 *
 * @author tuck
 */
public class ModifyText{
    
    public Map<String, Integer> mapSen = new HashMap<>();        
    private final Map<String, ArrayList<String>> uniqueMap = new HashMap<>();
    public ArrayList<ArrayList<String>> sentenceResults = new ArrayList<>(); 
    
    public ArrayList<String>Text2Sentences(String filename) throws FileNotFoundException{
        ArrayList<String> sentencesWithStopWords = new ArrayList<>(); 
        Scanner sc = new Scanner(new File(filename)).useDelimiter("[.?!]");
    
        while(sc.hasNext()){
            String input = sc.next();
            sentencesWithStopWords.add(input.toLowerCase().replaceAll("[,:;\"']", "").trim());
        }
        sc.close();
        return sentencesWithStopWords;
    }
    
    public ArrayList<ArrayList<String>> Sentences2Words(ArrayList<String> sentences) throws FileNotFoundException{
        for (String sentence: sentences){
            
            ArrayList<String> wordsInSentence = new ArrayList<>();
            String[] thisSentence = sentence.split("\\s+");
            ArrayList<String> wordsList = new ArrayList<>();
            
            for (String word : thisSentence) {
                if (!word.equals("") && !getStopWords().contains(word)) {
                    wordsList.add(stemWord(word));
                    if (!uniqueMap.containsKey(stemWord(word))) {
                        wordsInSentence.add(stemWord(word));
                        uniqueMap.put(stemWord(word), wordsInSentence);
                    }
                }
            }// end inner for
            
            mapSen.put(wordsList.toString(), 1);
            
            if (!wordsInSentence.isEmpty()){
                sentenceResults.add(wordsInSentence);
            }
        } // end outer for
        return sentenceResults;
    }
    public Map<String, Integer> getMapSen(){
        return this.mapSen;
    }
  
    public Set<String> getUniqueWords(){
        return this.uniqueMap.keySet();
    }
    
    public ArrayList<String> getStopWords() throws FileNotFoundException{
        
        ArrayList<String> stopWords = new ArrayList<>();
        String filename = "../stopwords.txt";
        Scanner stopWordsSc = new Scanner(new File(filename));
        
        while(stopWordsSc.hasNext()){
            String input = stopWordsSc.next();
            stopWords.add(input.replaceAll("[,:;\"']", ""));
        }
        return stopWords;
    }
    
    public String stemWord(String word){
        PorterStemmer stemmer = new PorterStemmer();
        return stemmer.stem(word);
    }
} 