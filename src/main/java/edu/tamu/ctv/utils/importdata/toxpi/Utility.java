package edu.tamu.ctv.utils.importdata.toxpi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Utility {
    public static double roundThreeDecimals(double d) {
            return (double)Math.round(d * 1000) / 1000;
    }
    
     public static double roundOneDecimal(double d) {
            return (double)Math.round(d * 10) / 10;
	}
     
     
     public static double[] toPrimitiveSortedArray(List<Double> list){
         double[] resArr = new double[list.size()];
         Collections.sort(list);
         for(int i=0;i<list.size();i++){
             resArr[i]=list.get(i);
         }
         return resArr;
     }
     
     /**
     * 
     * @param x - value to scale
     * @param type - type of scaling (LOG10,HITCOUNT,LN,SQRT,LINEAR)
     * @return scaled value
     */
    public static double scale(Double x, double max, ToxPiConfig.SCALING type){
        try{
            if (x == null || x.equals(Double.NaN) || x.doubleValue()==0.0d || x.doubleValue()<0.0d){
                if(type.equals(ToxPiConfig.SCALING.LOG10)) x = 1000000.0;
                else x = 0d; 
            }
            double res;
            switch(type){
                case MAX: res = x.equals(0.0) ? 0 : -1 * (Math.log10(x)) + Math.log10(max);break;
                case LOG10: res = x.equals(0.0) ? 0 : -1 * (Math.log10(x)) + 6;break;
                case HITCOUNT: res = x.equals(0.0) ? 0.0 : 1.0;break;
                case LN: res = x.equals(0.0)? 0 : -1 * (Math.log(x)) + Math.log(max); break;
                case SQRT: res = Math.sqrt(x);break;
                default: res = (x.equals(Double.NaN)) ? 0.0 : x;break;
            }

            return res;
        }
        catch(Exception ex){
            return 0d;
        }
    }
    
  
    
    /**
     * Method responsible for counting number of lines in the file.
     * @param filename
     * @return number of lines in the file
     * @throws IOException 
     */
    public static int countFileLines(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        int lines = 0;
        while (reader.readLine() != null) lines++;
            reader.close();
         return lines;
    }

    /**
     * method responsible for founding maximums in slice
     * @param substances
     * @return 
     */
    public static Map<String, Double> findMaximumValuesForEachComponent(Collection<Substance> substances) {
        Map<String,Double> result = new HashMap<String, Double>();
        Substance substanceTemp = (Substance) substances.toArray()[0];
        for(Slice slice:substanceTemp.getSlices()){
            for(Component component:slice.getComponents()){
                    double max = -1;
                    for(Substance substance:substances){
                        Double value = substance.getComponents().get(component.getName());
                        if(max<value) max = value;
                    }
                    result.put(component.getName(), max);
                }
        }
        return result;        
    }

    public static List<Component> buildUniqueList(List<Component> componentsList) {
        LinkedHashSet<Component> hs = new LinkedHashSet<Component>();
        hs.addAll(componentsList);
        List<Component> res = new ArrayList<Component>();
        res.addAll(hs);
        return res;
    }
    
    public static void writeDebug(String s){
        if(ToxPiConfig.DEBUG) System.out.println("---------"+s);
    }

    public static void writeDebugInner(String s){
        if(ToxPiConfig.DEBUG) System.out.println("-------------"+s);
    }
            
}

