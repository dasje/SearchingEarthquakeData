
/**
 * QuakeSortInPlace provides class methods that will provide the largest depth of an ArrayList of QuakeEntry's
 * and sort in place a list of QuakeEntry's in order of depth with the largest first.
 * 
 * @author Ben Sweeney/dasje
 * @version 13/07/2021
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public static boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        /*Iterates through an ArrayList of QuakeEntry's to check if the list is sorted
        from smallest magnitude to largest. Returns false if not sorted.*/
        for (int i = 0; i < quakes.size(); i++) {
            if (i == quakes.size() - 1) {
                return true;
            } else if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    public static void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        int passCount = 0;
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, passCount);
            if (checkInSortedOrder(in)) {
                break;
            }
            passCount += 1;
        }
        System.out.println("Total passes with BubbleSort: " + (passCount + 1));
    }

    public static void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int passCount = 0;
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            //if (! qi.equals(qmin)) {
                passCount += 1;
            //}
            in.set(i,qmin);
            in.set(minIdx,qi);
            if (checkInSortedOrder(in)) {
                break;
            }
        }
        System.out.println("Sorted in " + (passCount) + " passes.");
    }

    public static void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        /*A single pass of Bubble Sort over quakeData to sort by magnitude.
        Takes an ArrayList of QuakeEntry's and int number of passes with BubbleSort
        already made.*/
        for (int i = 0; i < quakeData.size() - numSorted - 1    ; i++) {
            QuakeEntry first = quakeData.get(i);
            QuakeEntry second = quakeData.get(i + 1);
            if (first.getMagnitude() > second.getMagnitude()) {
                quakeData.set(i, second);
                quakeData.set(i + 1, first);
            } else {
                ;
            }
        }
    }

    public static void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        /*A full in place sort by magnitude of an array of QuakeEntry's, using Bubble Sort*/
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in, i);
        }
    }

    public static int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        /*Returns integer index of the QuakeEntry with the largest depth*/
        int retInt = from;
        QuakeEntry deepest = quakeData.get(from);
        for (int i = from; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > deepest.getDepth()) {
                deepest = quakeData.get(i);
                retInt = i;
            }
        }
        return retInt;
    }

    public static void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        /*Sorts the give ArrayList by the depths of the quakes, with largest first*/
        for (int i = 0; i < 50 /*in.size()*/; i++) {
            int largest = getLargestDepth(in, i);
            QuakeEntry current = in.get(i);
            QuakeEntry currentLargest = in.get(largest);
            in.set(i, currentLargest);
            in.set(largest, current);
            }
        }
   
    public static int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }

    public static void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthquakeDataSampleSix1.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        //String source = "data/earthQuakeDataWeekDec6sample1.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*for (QuakeEntry qe: list) {
            System.out.println(qe);
        }*/
       
        System.out.println("read data for "+list.size()+" quakes");
        //sortByMagnitudeWithCheck(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByLargestDepth(list);
        //sortByMagnitude(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
