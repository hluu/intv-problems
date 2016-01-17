package my.cci.stack_queue;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by hluu on 1/17/16.
 *
 * Problem statement:
 *  Given three towers. One of the towers has a N disks of different sizes and sorted
 *  in ascending order of size from top to bottom.  How to move them to another tower with
 *  the following constraints:
 *  1) Only one disk can be moved at a time
 *  2) A disk is slid off the top of one tower onto the next tower
 *  3) A disk can only be placed on top of a larger disk
 *
 * Approach:
 *  There are 3 towers, one of the towers can be used an intermediate tower or holding tower.
 *
 *  Let's start with something small like 1 disk
 *
 *  1) 1 disk - just move this one disk directly from source to destination
 *  2) 2 disks - move the top disk to intermediate tower, move the bottom disk
 *                 to final tower, then move the smaller disk to destination
 *  3) 3 disks - we know how to move two disks from source to destination, but in this
 *                   case they need to move to the intermediate tower so we can move the
 *                   last disk to to destination.  Now we can apply the logic for moving
 *                   two disks from intermediate tower to destination tower using first tower
 *                   as the intermediate tower
 *  4) 4 disks - move 3 disks to intermediate tower, then move last disk to destination tower
 *
 *
 *  This approach reminds us when dealing with a complex problem, we need to think about
 *  a smaller version of the complex problem and see if we can build on that smaller version.
 *
 *  From looking at the above 4 example, we can formalize an algorithm using recursion.
 *  In order to move n disk, let's move n-1 disks to intermediate tower, then move the
 *  only remaining disk to destination tower.  Finally move the n-1 disk from the intermediate
 *  tower to the destination tower.
 *
 *
 */
public class HanoiTower {
    public static void main(String[] args) {
        System.out.println("HanoiTower.main");

        Tower towerOne = new Tower(1);
        Tower towerTwo = new Tower(2);
        Tower towerThree = new Tower(3);

        towerOne.push(4);
        towerOne.push(3);
        towerOne.push(2);
        towerOne.push(1);

        moveDisks(towerOne.size(), towerOne, towerThree, towerTwo);

        System.out.println("disks from " + towerThree.toString() + " " +
                towerThree.getDisks());
    }

    public static void moveDisks(int numDisks, Tower sourceTower, Tower destTower, Tower temporaryTower) {
        if (numDisks >= 1) {
            moveDisks(numDisks - 1, sourceTower, temporaryTower, destTower);
            int disk = sourceTower.pop();
            System.out.println("moving disk: " + disk + " from: " + sourceTower +
                    " to: " + destTower);
            destTower.push(disk);
            moveDisks(numDisks - 1, temporaryTower, destTower, sourceTower);
        }

    }

    public static class Tower {
        private int id;
        private Stack<Integer> diskStack = new Stack<>();
        public Tower(int id) {
            this.id = id;
        }

        public void push(int disk) {
            if (!diskStack.isEmpty()) {
                if (diskStack.peek() < disk) {
                    throw new IllegalStateException("can't push smaller disk onto tower " + toString());
                }
            }

            diskStack.push(disk);
        }

        public int pop() {
            if (diskStack.isEmpty()) {
                throw new IllegalStateException("No more disks to pop on tower: "  + toString());
            }

            return diskStack.pop();
        }

        public String toString() {
            return String.valueOf(id);
        }

        public List<Integer> getDisks() {
            List<Integer> diskList = new LinkedList<>();
            while (!this.diskStack.isEmpty()) {
                diskList.add(diskStack.pop());
            }

            return diskList;
        }

        public int size() {
            return diskStack.size();
        }

    }
}
