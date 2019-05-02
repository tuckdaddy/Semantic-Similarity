/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.util.Map;

/**
 *
 * @author tuck
 */
public interface SetVector {
    
    /**
     *
     * 
     * @param mapOfSen
     * @param word
     * @return 
     */
    public Map<String, Map<String, Integer>> setVector(Map<String,Integer> mapOfSen, String word);    
}
