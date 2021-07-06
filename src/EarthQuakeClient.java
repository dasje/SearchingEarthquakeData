import java.util.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public static ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
                                                          double magMin) {
        /*Returns ArrayList of type QuakeEntry of all earthquakes with magnitude
        * greater than magMin*/
        System.out.println("Quakes with a magnitude of " + magMin);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe: quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public static ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
                                                             double distMax,
                                                             Location from) {
        /*Returns ArrayList of type QuakeEntry of all earthquakes filtered by distance*/
        System.out.println("Quakes within " + distMax + " from " + from);
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe: quakeData) {
            if (from.distanceTo(qe.getLocation()) / 1000 <= distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public static ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,
                                                      double minDepth,
                                                      double maxDepth){
        /*Returns ArrayList<QuakeEntry> with quakes between a minimum and maximum depth*/
        System.out.println("Quakes between the depths of " + minDepth + " and " + maxDepth);
        ArrayList<QuakeEntry> answer = new ArrayList<>();
        for (QuakeEntry qe: quakeData) {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public static ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,
                                                       String where,
                                                       String phrase){
        /*Returns an ArrayList<QuakeEntry> with the quakes whose titles contain
        the phrase found at position determined by where (start, end, any)*/
         System.out.println("Quakes whose titles contain the phrase " + phrase + " at position " + where);
         ArrayList<QuakeEntry> answer = new ArrayList<>();
         for (QuakeEntry qe: quakeData) {
             if (where == "start") {
                if (qe.getInfo().startsWith(phrase)) {
                    answer.add(qe);
                }
             } else if (where == "end") {
                 if (qe.getInfo().endsWith(phrase)) {
                     answer.add(qe);
                 }
             } else if (where == "any") {
                 if (qe.getInfo().contains(phrase)) {
                     answer.add(qe);
                 }
             }
         }
         return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public static void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> greaterThan5 = filterByMagnitude(list, 5.0);
        System.out.println("read data for "+list.size()+" quakes");
        for (QuakeEntry qe: greaterThan5) {
            System.out.println(qe);
        }
        System.out.println("Found " + greaterThan5.size() + " quakes that match the size criteria");

    }

    public static void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        ArrayList<Location> cityList = new ArrayList<>();
        Location city1 = new Location(35.988, -78.907);
        Location city2 =  new Location(38.17, -118.82);
        cityList.add(city1);
        cityList.add(city2);

        for (Location city: cityList) {
            ArrayList<QuakeEntry> toCity = filterByDistanceFrom(list,1000.00, city);
            for (QuakeEntry qe: toCity) {
                System.out.println("Distance to " + qe.getInfo() + ": " + qe.getLocation().distanceTo(city));
            }
            System.out.println("Found " + toCity.size() + " quakes that match the criteria");
        }
    }

    public static void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> toDepth = filterByDepth(list, -10000.0, -5000.0);
        for (QuakeEntry qe: toDepth) {
            System.out.println(qe);
        }
        System.out.println("Found " + toDepth.size() + " that match the criteria");
    }

    public static void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        HashMap<String, String> hm = new HashMap<>();
        hm.put("California", "end");
        hm.put("Can", "any");
        hm.put("Explosion", "start");

        for (String key: hm.keySet()) {
            ArrayList<QuakeEntry> withPhrase = filterByPhrase(list, hm.get(key), key);
            for (QuakeEntry qe: withPhrase) {
                System.out.println(qe);
            }
            System.out.println("Found " + withPhrase.size() + " that match the criteria");
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
