package org.learning.others;

import java.util.*;

/**
 * Given a list of users and their favorite cities
 *
 * U1,Amsterdam,Barcelona,London,Prague
 * U2,Shanghai,Hong Kong,Moscow,Sydney,Melbourne
 * U3,London,Boston,Amsterdam,Madrid
 * U4,Barcelona,Prague,London,Sydney,Moscow
 *
 */
public class HostMatching {
    public static void main(String[] args) {

        List<String> input = new ArrayList<>();
        input.add("U1,Amsterdam,Barcelona,London,Prague");
        input.add("U2,Shanghai,Hong Kong,Moscow,Sydney,Melbourne");
        input.add("U3,London,Boston,Amsterdam,Madrid");
        input.add("U4,Barcelona,Prague,London,Sydney,Boston");

        HostMatching hostMatching = new HostMatching(input);

        String traveler = "U1,Amsterdam,Barcelona,London,Prague";
        List<String> matchingBuddyList = hostMatching.findBuddies(traveler);

        System.out.println(matchingBuddyList);

        Set<String> recommendations = hostMatching.recommendCities(traveler, 3);

        System.out.println("recommendations ==> ");
        System.out.println(recommendations);

    }

    private Map<String, List<String>> database = new HashMap<>();
    private Map<String, List<String>> invertedIndex = new HashMap<>();

    public HostMatching(List<String> input) {
        // build the inverted index

        for (String user : input) {
            String[] tokens = user.split(",");

            String userId = tokens[0];

            List<String> cityList = new ArrayList<>();
            if (tokens.length > 1) {
                for (int i = 1; i < tokens.length; i++) {
                    String city = tokens[i];
                    cityList.add(tokens[i]);

                    // inverted index
                    invertedIndex.putIfAbsent(city, new ArrayList<>());
                    invertedIndex.get(city).add(userId);
                }
            }

            database.put(userId, cityList);
        }
    }

    public List<String> findBuddies(String input) {
        String[] tokens = input.split(",");

        //
        Map<String, Integer> buddyMap = new HashMap<>();

        for (int i = 1; i < tokens.length; i++) {
            String city = tokens[i];

            List<String> userList = invertedIndex.get(city);

            for(String user : userList) {
                if (buddyMap.putIfAbsent(user, 1) != null) {
                    // increment count
                    buddyMap.put(user, buddyMap.get(user) + 1);
                }
            }
        }

        // filtering for buddies with >= 50% matchi
        float cityNumFromInput = ((float)(tokens.length - 1)) / 2;

        List<String> travelBuddies = new ArrayList<>();

        // sort the buddyMap by value
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : buddyMap.entrySet()) {
            entryList.add(entry);
        }

        Collections.sort(entryList, new EntryComparator());


        for (Map.Entry<String, Integer> entry : entryList) {
            if (entry.getValue() >= cityNumFromInput) {
                travelBuddies.add(entry.getKey());
            } else {
                break;
            }
        }

        return travelBuddies;

    }

    public Set<String> recommendCities(String newTraveler, int n) {


        String[] tokens = newTraveler.split(",");
        Set<String> cityList = new HashSet<>();
        if (tokens.length > 1) {
            for (int i = 1; i < tokens.length; i++) {
                String city = tokens[i];
                cityList.add(tokens[i]);
            }
        }

        List<String> bestBuddies = findBuddies(newTraveler);

        Set<String> recommendations = new HashSet<>();

        for (String buddyId : bestBuddies) {
          List<String> buddyCities =  database.get(buddyId);
          if (buddyCities != null) {
              for (String city : buddyCities) {
                  if (!cityList.contains(city)) {
                      recommendations.add(city);
                      if (recommendations.size() >= n) {
                          return recommendations;
                      }
                  }
              }
          }
        }

        return recommendations;
    }

    /**
     * Descending order comparator
     */
    private static class EntryComparator implements  Comparator<Map.Entry<String, Integer>> {
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            return o2.getValue() - o1.getValue();
        }
    }
}
