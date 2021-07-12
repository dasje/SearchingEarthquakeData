import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public static ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public static void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        System.out.println("Filtered with magnitude between 4.0 and 5.0 and depth between -35000 and -12000");
        Filter fmf = new MagnitudeFilter(3.5, 4.5);
        Filter fdf = new DepthFilter( -55000.0, -20000.0);
        ArrayList<QuakeEntry> m7  = filter(list, fmf);
        ArrayList<QuakeEntry> m8 = filter(m7, fdf);
        for (QuakeEntry qe: m8) {
            System.out.println(qe);
        }
        System.out.println("There are " + m8.size() + " quakes that fit the criteria");

        /*System.out.println();

        System.out.println("Filtered to within 1000000m of Denver, Colorado");
        Location loc = new Location(39.7392, -104.9903);
        Filter fdistf = new DistanceFilter(loc, 10000000);
        Filter fpf = new PhraseFilter("end", "a");
        ArrayList<QuakeEntry> m9  = filter(list, fdistf);
        ArrayList<QuakeEntry> m10 = filter(m9, fpf);
        for (QuakeEntry qe: m10) {
            System.out.println(qe);
        }
        System.out.println("There are " + m10.size() + " quakes that fit the criteria");*/
    }

    public static void matchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> x  = parser.read(source);

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        Location loc = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(loc, 3000000));
        maf.addFilter(new PhraseFilter("any", "e"));

        ArrayList<QuakeEntry> ret = filter(x, maf);
        for (QuakeEntry qe: ret) {
            System.out.println(qe);
        }
        System.out.println("There are " + ret.size() + " quakes that fit the criteria");

    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
