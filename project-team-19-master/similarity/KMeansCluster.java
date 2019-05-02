/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author connoreschrich & tuck
 */
/*


*/
public class KMeansCluster {
   
    
    public static String[] getCluster(int k, int iter, Map<String, Map<String, Integer>> uniqueWords){
        Map<String,Integer> centroid =null;
        //first maps key is means //// second maps key is words and values semantic vector
        Map<String, Map<String, Integer>> clusters = new HashMap<>();
        Map<String, Map<String, Integer>> meansCentroid = new HashMap<>();
        String means[] = null;
        Set<String> wordSet = uniqueWords.keySet();
        //System.out.println(wordSet);
        String[] a = new String[wordSet.size()];
        wordSet.toArray(a);
        String[] meansStr = new String[k];
        //generate k random points
        for (int i = 0; i < k; i++){ 
            int randomNum = 0 + (int)(Math.random() * (wordSet.size()-1)); 
            meansStr[i] = a[randomNum]; 
            meansCentroid.put(meansStr[i],uniqueWords.get(meansStr[i]));
                                //issa semantic vector
            centroid =  meansCentroid.get(meansStr[i]); //issa key
        }
        for(int i=0; i <= iter; i++){
            CompareVectors compareTheseYo = new CompareVectors();
            //for each unique word find the correct centroid to put 
            for(Map.Entry mEntry : uniqueWords.entrySet()){
                Map<String, Integer> vector = (Map<String, Integer>)mEntry.getValue();
                
                
                //for each point that is a semantic vector of a word in a colection of points that maps a word to a specific semantic vector
                    //find the correct mean in means such that the distance from specific cluster is the smallest possible
                    //add the semantic vector to the specific cluster
                //calculate new means 
                //for i in 0 to k 
                    //means[i] = the centroid of the points in clusters[i]
                    double currentVal = 0;
                    String key = meansStr[1];
                for(String mean: meansCentroid.keySet()){
                    
                    if(currentVal >= compareTheseYo.EuclideanNormalized(vector, meansCentroid.get(mean))){
                        currentVal = compareTheseYo.EuclideanNormalized(vector, meansCentroid.get(mean));
                        key = mean;
                    }
                    
                }
                clusters.put(key, vector);
                for(int z=0; z< k; z++){
                  
                    
                }
            }    
     
        } //end for iter loop
        System.out.println(clusters);
         return null;   
    } // end getCLuster
} //end Class



/*



 while(minCentroid.size()<= meansStr.length){
                    System.out.println("this is minCen size: "+ minCentroid.size());
                    System.out.println("This is measnStr length: " +meansStr.length);
                    Map<String, Integer> currentCentroid = meansCentroid.get(meansStr[j]);
                    
                    Double minDistance = compareTheseYo.EuclideanNormalized(ogVec, currentCentroid);
                    Double nextDistance = compareTheseYo.EuclideanNormalized(ogVec, meansCentroid.get(meansStr[j++]));
                     if(minDistance <= nextDistance){
                        minCentroid.put(meansStr[j], minDistance);
                         System.out.println("if " +minCentroid);
                        //clusters.put(meansStr[j++], ogVec);
                    }
                    else{
                        minCentroid.put(meansStr[j++], nextDistance);
                        System.out.println("else " + minCentroid);
                        j++;
                    }
                    
                }
*/