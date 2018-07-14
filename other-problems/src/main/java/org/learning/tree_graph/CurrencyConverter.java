package org.learning.tree_graph;

import org.learning.numbers.Max;

import java.util.*;

/**
 * Give a table of current exchange rates, write a function to perform
 * currency conversion given the source and target currencies.
 *
 * USD,EUR,0.8
 * USD,CAD,0.7
 * EUR,CNY,2.5
 * DKK,USD,5.3
 * CNY,HKD,1.5
 *
 * Approach:
 * Perform a DFS to search for toCurr.  The collector is used to collect the
 * rate as it traverses down the graph.
 *
 * This is a graph traversal problem, but the twist is collecting
 * the list of currency conversion rates along the way.
 *
 * Scenarios that we have to deal with:
 * 1) toCurr may not exist
 * 2) may run into circular loop
 * 3) multiple paths to fromCurr to toCurr
 *
 */
public class CurrencyConverter {
    public static void main(String[] args) {
        System.out.println(CurrencyConverter.class.getName());

        Map<String, List<ExchangeRate>> rateGraph = buildGraph(buildRates1());

        displayGraph(rateGraph);

        test("USD","EUR", 100, rateGraph, 80.0);
        test("USD","CNY", 100, rateGraph, 80.0 * 2.5);

        test("USD","INR", 100, rateGraph, 80.0 * 1.8);
        test("USD","JPY", 100, rateGraph, 80.0 * 1.8 * 0.7);

        test("USD","XXX", 100, rateGraph, 0.0);

    }

    private static void test(String fromCurr, String toCurr, double amt,
                             Map<String, List<ExchangeRate>> rateGraph, Double expectedAmt) {

        System.out.printf("\n==== test: from: %s, to: %s, amt: %3.2f\n",
                fromCurr, toCurr, amt);

        Double result = convert(fromCurr, toCurr, amt, rateGraph);

        System.out.printf("--- expected: %3.2f actual: %3.2f\n",
                expectedAmt, result);
    }

    /**
     * Main driver of the currency converter.
     *
     * @param fromCurr
     * @param toCurr
     * @param amt
     * @param rateGraph
     * @return
     */
    private static Double convert(String fromCurr, String toCurr, double amt,
                                  Map<String, List<ExchangeRate>> rateGraph) {
        List<ExchangeRate> edgeList = rateGraph.get(fromCurr);


        List<Double> rateList = null;
        if (edgeList != null) {
            for (ExchangeRate rate : edgeList) {
                rateList = new ArrayList<>();

                rateList = convertHelper(rate, toCurr, rateGraph, rateList);
                if (rateList != null) {
                    break;
                }
            }
        }

        double result = 0.0;

        if (rateList != null) {
            System.out.println("**** rateList: " + rateList);
            result = amt;
            for (Double rate : rateList) {
                result *= rate;
            }
        } else {
            System.out.println("rateList IS NULL");
        }

        return result;

    }

    /**
     * Perform a DFS to search for toCurr.  The collector is used to collect the
     * rate as it traverses down the graph.
     *
     * This is a graph traversal problem, but the twist is collecting
     * the list of currency conversion rates along the way.
     *
     * Scenarios that we have to deal with:
     * 1) toCurr may not exist
     * 2) may run into circular loop
     * 3) multiple paths to fromCurr to toCurr
     *
     * @param exchRate
     * @param toCurr
     * @param rateGraph
     * @param collector
     * @return
     */
    private static List<Double> convertHelper(ExchangeRate exchRate, String toCurr, Map<String,
            List<ExchangeRate>> rateGraph, List<Double> collector) {

        if (exchRate.currency.equals(toCurr)) {
            collector.add(exchRate.rate);
            return collector;
        }


        List<ExchangeRate> edges = rateGraph.get(exchRate.currency);
        if (edges == null) {
            return null;
        }

        collector.add(exchRate.rate);

        for (ExchangeRate rate : edges) {
            List<Double> path = convertHelper(rate, toCurr, rateGraph, collector);
            if (path != null) {
                // if a path is found, return it. stop exploring
                return path;
            }
        }

        // if we got here, we didn't find a path
        // so backtracking by removing the last rate
        collector.remove(collector.size() - 1);
        return null;

    }



    private static void displayGraph(Map<String, List<ExchangeRate>> rateGraph) {
        for (Map.Entry<String, List<ExchangeRate>> entry : rateGraph.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static List<String> buildRates1() {
        String[] rates = new String[] {
                "USD,EUR,0.8",
                "USD,CAD,0.7",
                "EUR,CNY,2.5",
                "EUR,INR,1.8",
                "DKK,USD,5.3",
                "CNY,HKD,1.5",
                "INR,GBP,0.5",
                "INR,JPY,0.7"
        };

        return Arrays.asList(rates);

    }

    private static Map<String, List<ExchangeRate>> buildGraph(List<String> inputs) {
        Map<String, List<ExchangeRate>> map = new HashMap<>();

        for (String input : inputs) {
            String[] parts = input.split(",");

            String sourceCurrency = parts[0];
            String destCurrency = parts[1];
            double rate = Double.parseDouble(parts[2]);

            List<ExchangeRate> rates = map.get(sourceCurrency);
            if (rates == null) {
                rates = new ArrayList<ExchangeRate>();
            }

            rates.add(new ExchangeRate(destCurrency, rate));

            map.put(sourceCurrency, rates);
        }

        return map;
    }

    private static class ExchangeRate {
        public String currency;
        public double rate;

        public ExchangeRate(String currency, double rate) {
            this.currency = currency;
            this.rate = rate;
        }

        @Override
        public String toString() {
            return "ExchangeRate{" +
                    "currency='" + currency + '\'' +
                    ", rate=" + rate +
                    '}';
        }
    }
}
