/**
 * Given an unordered list of flights taken by someone, each
 * represented as (origin, destination) pairs, and a starting airport,
 * compute the person's itinerary. If no such itinerary exists,
 * return null. All flights must be used in the itinerary.
 *
 * For example, given the following list of flights:
 *
 * HNL ➔ AKL
 * YUL ➔ ORD
 * ORD ➔ SFO
 * SFO ➔ HNL
 * and starting airport YUL, you should return YUL ➔ ORD ➔ SFO ➔ HNL ➔ AKL.
 * (This also happens to be the actual itinerary for the trip I'm about to take.)
 *
 * You should take some time to try to solve it on your own! Notice that a
 * greedy solution won't work, since it's possible to have a cycle in the graph.
 *
 * Let's again describe the brute force solution to this problem, which
 * is to try every permutation of flights and verify that it's a valid itinerary.
 * That would be O(n!). Now let's ask ourselves if we can improve this with backtracking.
 *
 * Can we construct a partial solution?
 * Yes, we can build an (incomplete) itinerary and extend it by adding more
 * flights to the end.
 *
 * Can we verify if the partial solution is invalid?
 * Yes, we can check a solution is invalid if
 * 1) there are no flights leaving from our last destination and
 * 2) there are still flights remaining that can be taken.
 *    Since we must use all flights, this means we're at a dead end.
 *
 * Can we verify if the solution is complete?
 * Yes, we can check if a solution is complete if our itinerary uses all the flights.
 */
public class FlightItinerary {
    public static void main(String[] args) {
        System.out.println(FlightItinerary.class.getName());
    }
}
