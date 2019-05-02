package edu.uiowa.cs.similarity;

import org.apache.commons.cli.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Options options = new Options();
        options.addRequiredOption("f", "file", true, "input file to process");
        options.addOption("h", false, "print this help message");
        options.addOption("s", false, "sentences will be printed");
        options.addOption("v", false, "Unique Vector words to be printed");
        options.addOption("t", true, "query word,j");
        options.addOption("m", true, "cosine, euc, or eucnorm");
        options.addOption("k", true, "k,iters");
        CommandLineParser parser = new DefaultParser();

        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            new HelpFormatter().printHelp("Main", options, true);
            System.exit(1);
        }
        String filename = cmd.getOptionValue("f");
        System.out.println(filename);
        if (!new File(filename).exists()) {
            System.err.println("file does not exist "+filename);
            System.exit(1);
        }
        if (cmd.hasOption("h")) {
            HelpFormatter helpf = new HelpFormatter();
            helpf.printHelp("Main", options, true);
            System.exit(0);
        }
        
        ModifyText modifyText = new ModifyText();
        
        ArrayList<String> sentences = modifyText.Text2Sentences(filename);
        ArrayList<ArrayList<String>> wordsInSentences = modifyText.Sentences2Words(sentences);
                        ///End of Part 1///
        Set<String>uniqueWords = modifyText.getUniqueWords();
        Map<String, Integer> mapSen = modifyText.getMapSen();
        Iterator uniqueWordsIterator = uniqueWords.iterator();
        
        Vector vec = new Vector();
        
        while(uniqueWordsIterator.hasNext()){
                String uniqueWord = (String)uniqueWordsIterator.next();
                vec.setVector(mapSen, uniqueWord);
        }
        
        if (cmd.getOptionValue("t") != null){
            String[] jTops = cmd.getOptionValue("t").split(",");
            String word = jTops[0];
            int j = Integer.parseInt(jTops[1]);
            JTop jTop;
            if(cmd.getOptionValue("m") != null){
                if (cmd.getOptionValue("m").equals("euc")){
                    jTop = new EucJTop(j, word, vec.getDescriptor());
                }
                else if (cmd.getOptionValue("m").equals("eucnorm")){
                    jTop = new EucNormJTop(j, word, vec.getDescriptor());
                    
                }
                else{
                jTop = new CosineJTop(j, word, vec.getDescriptor());
                }
            }
            else{
                jTop = new CosineJTop(j, word, vec.getDescriptor());
            }
            
            Map<String, Double> similarities = jTop.getTopJ();
            System.out.println(similarities);
        }
        
        if (cmd.getOptionValue("k") != null){
            String[] jTops = cmd.getOptionValue("k").split(",");
            int k = Integer.parseInt(jTops[0]);
            int iters = Integer.parseInt(jTops[1]);
            KMeansCluster.getCluster(k, iters, vec.descriptor);
        }

        if (cmd.hasOption("s")) {
            System.out.println(wordsInSentences);
            System.out.println(String.format("Number of Sentences: %d", wordsInSentences.size()));
        }
        if (cmd.hasOption("v")) {
            uniqueWordsIterator = uniqueWords.iterator();
            while(uniqueWordsIterator.hasNext()){
                String uniqueWord = (String)uniqueWordsIterator.next();
                System.out.println("Word: " + uniqueWord + " Vector: " + vec.getVector(uniqueWord));
            } 
        }
    }   //end main
}   //end Main class

