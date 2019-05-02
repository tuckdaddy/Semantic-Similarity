/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uiowa.cs.similarity;


import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
/**
 *
 * @author connoreschrich
 */
public class CompareVectors {
    public static double CosineSim(Map<String, Integer> u, Map<String, Integer> v){
        double sumTop = 0;
        double vSquared = 0;
        double uSquared = 0;
        double bottom = 0;
        Iterator uIterator = u.entrySet().iterator();
        Iterator vIterator = v.entrySet().iterator();
        
        while(uIterator.hasNext() || vIterator.hasNext()){
            int uValue;
            int vValue;
            if(uIterator.hasNext()){
                Map.Entry mEntryU = (Map.Entry)uIterator.next();
                uValue = (Integer)mEntryU.getValue();
            }
            else{
                uValue = 0;
            }
            if(vIterator.hasNext()){
                Map.Entry mEntryV = (Map.Entry)vIterator.next();
                vValue = (Integer)mEntryV.getValue();
            }
            else{
                vValue = 0;
            }
            sumTop += uValue*vValue;
            vSquared += Math.pow(vValue, 2);
            uSquared += Math.pow(uValue, 2);
        }
        

        bottom = Math.sqrt(vSquared*uSquared);
        double maths = sumTop/bottom;
        return maths;
    }
    
     public static double Euclidean(Map<String, Integer> u, Map<String, Integer> v){
        double sum = 0;
        Iterator uIterator = u.entrySet().iterator();
        Iterator vIterator = v.entrySet().iterator();
        
        while(uIterator.hasNext() && vIterator.hasNext()){
            int uValue;
            int vValue;
            if(uIterator.hasNext()){
                Map.Entry mEntryU = (Map.Entry)uIterator.next();
                uValue = (Integer)mEntryU.getValue();
            }
            else{
                uValue = 0;
            }
            if(vIterator.hasNext()){
                Map.Entry mEntryV = (Map.Entry)vIterator.next();
                vValue = (Integer)mEntryV.getValue();
            }
            else{
                vValue = 0;
            }
            sum += Math.pow(uValue-vValue, 2);
        }
        return -(Math.sqrt(sum));
    }
     
    public  double EuclideanNormalized(Map<String, Integer> u, Map<String, Integer> v){
        double magnitudeU = 0;
        double magnitudeV = 0;
        Iterator uIterator = u.entrySet().iterator();
        Iterator vIterator = v.entrySet().iterator();
        
        while(uIterator.hasNext() || vIterator.hasNext()){
           
            int uValue;
            int vValue;
            if(uIterator.hasNext()){
                Map.Entry mEntryU = (Map.Entry)uIterator.next();
                uValue = (Integer)mEntryU.getValue();
            }
            else{
                uValue = 0;
            }
            if(vIterator.hasNext()){
                Map.Entry mEntryV = (Map.Entry)vIterator.next();
                vValue = (Integer)mEntryV.getValue();
            }
            else{
                vValue = 0;
            }
            magnitudeU += Math.pow(uValue, 2);
            magnitudeV += Math.pow(vValue, 2);
        }
        
        magnitudeU = Math.sqrt(magnitudeU);
        magnitudeV = Math.sqrt(magnitudeV);
        double sum = 0;
        uIterator = u.entrySet().iterator();
        vIterator = v.entrySet().iterator();
        
        while(uIterator.hasNext() || vIterator.hasNext()){
            double uValue;
            double vValue;
            if(uIterator.hasNext()){
                Map.Entry mEntryU = (Map.Entry)uIterator.next();
                uValue = (Integer)mEntryU.getValue()/magnitudeU;
            }
            else{
                uValue = 0;
            }
            if(vIterator.hasNext()){
                Map.Entry mEntryV = (Map.Entry)vIterator.next();
                vValue = (Integer)mEntryV.getValue()/magnitudeV;
            }
            else{
                vValue = 0;
            }
            sum += Math.pow(uValue-vValue, 2);
        }
        //System.out.println((Math.sqrt(sum)));
        return (Math.sqrt(sum));
   
    }
    
}
