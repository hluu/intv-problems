package org.learning.backtracking;

import org.common.ArrayUtils;
import org.common.StringUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://xkcd.com/287/
 *
 * Problem:
 *   Give a set of menu items, and amount, find all the possible combinations
 *   of menu items that can be bought for a given amount
 *
 *   The same menu item can be bought multiple times
 *
 *
 */
public class RestaurantMenuItem {
    public static void main(String[] args) {

        MenuItem[] menuItems = new MenuItem[] {
                new MenuItem("MF", 1.0),
                new MenuItem("FF", 2.0),
                new MenuItem("SS", 3.0),
                new MenuItem("HW", 4.0),
                new MenuItem("MS", 5.0),
        };

        test(menuItems, 4.0);


        MenuItem[] menuItems2 = new MenuItem[] {
                new MenuItem("MF", 2.15),
                new MenuItem("FF", 2.75),
                new MenuItem("SS", 3.35),
                new MenuItem("HW", 3.55),
                new MenuItem("MS", 4.20),
                new MenuItem("SP", 5.80)
        };

        double amt2 = 15.05;

        test(menuItems2, amt2);

        MenuItem[] menuItems3 = new MenuItem[] {
                new MenuItem("MF", 1.5),
                new MenuItem("FF", 2.5),
                new MenuItem("SS", 3.0),
        };

        double amt3 = 6.0;

        test(menuItems3, amt3);

    }

    private static void test(MenuItem[] menuItems, double amt) {
        System.out.println("\n===== finding menu items ======");

        System.out.printf("menu item: %s, amt: %f\n",
              Arrays.toString(menuItems), amt);


        List<List<MenuItem>> result = findItems(menuItems, amt);

        System.out.println();
        for(List<MenuItem> list : result) {
            System.out.println(list.size() + " : " + list);
        }
        System.out.println("======= *** =======");

    }

    private static List<List<MenuItem>> findItems(MenuItem[] menuItems, double amt) {
        List<List<MenuItem>> result = new ArrayList<List<MenuItem>>();

        findItemsHelper2(menuItems, 0, 0.0, amt, result, new ArrayList<>());
        return result;

    }

    /**
     * Given a set items and amount, find a combination of items that
     * add up to the amount.  There could be multiple combinations, return
     * all the combinations.
     *
     * An item can be used multiple times
     *
     * Approach:
     *  1) Backtracking
     *  2)
     *
     *
     *
     * @param menuItems
     * @param idx
     * @param amtSoFar
     * @param amt
     * @param collector
     * @param list
     * @return
     */
    private static void findItemsHelper2(MenuItem[] menuItems, int idx, double amtSoFar,
                                           double amt, List<List<MenuItem>> collector,
                                           List<MenuItem> list) {

        //StringUtility.printSpace(list.size());
       // System.out.printf("idx: %d, amtSoFar: %f, collect: %s\n", idx, amtSoFar, list);

        if (idx >= menuItems.length) {
            // we went through all the items in the menu, so stop
            return ;
        }

        if (amtSoFar > amt) {
            // not a valid combination when amtSoFar exceeds the given target amount
            return ;
        }

        // base case when amtSoFar exactly matches the given target amount
        if (amtSoFar == amt) {
            // return a list of menu items collected up until this point
            collector.add(new ArrayList<>(list));
            return;
        }

        /**
         * If we always start at 0, that will create duplicates
         *
         */
        for (int i = idx; i < menuItems.length; i++) {
            if (menuItems[i].price + amtSoFar <= amt) {
                list.add(menuItems[i]);
                // select item
                findItemsHelper2(menuItems, i, menuItems[i].price + amtSoFar,
                        amt, collector, list);
                // back track
                list.remove(list.size() - 1);
            }
        }
        return;
    }

    private static class MenuItem {
        String name;
        double price;

        public MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String toString() {
            return name + ":" + price;
        }

    }


}
