package my.cci.stack_queue;

import org.testng.Assert;
import org.testng.annotations.Test;

import static my.cci.stack_queue.HanoiTower.moveDisks;

/**
 * Created by hluu on 1/17/16.
 */
public class HanoiTowerTest {
    @Test
    public void oneDisk() {
        HanoiTower.Tower towerOne = new HanoiTower.Tower(1);
        HanoiTower.Tower towerTwo = new HanoiTower.Tower(2);
        HanoiTower.Tower towerThree = new HanoiTower.Tower(3);

        towerOne.push(1);

        moveDisks(towerOne.size(), towerOne, towerThree, towerTwo);

        Assert.assertEquals(towerOne.size(), 0);
        Assert.assertEquals(towerTwo.size(), 0);

        Assert.assertEquals(towerThree.size(), 1);
        Assert.assertEquals(towerThree.getDisks().toString(), "[1]");
    }

    @Test
    public void twoDisks() {
        HanoiTower.Tower towerOne = new HanoiTower.Tower(1);
        HanoiTower.Tower towerTwo = new HanoiTower.Tower(2);
        HanoiTower.Tower towerThree = new HanoiTower.Tower(3);

        towerOne.push(2);
        towerOne.push(1);

        moveDisks(towerOne.size(), towerOne, towerThree, towerTwo);

        Assert.assertEquals(towerOne.size(), 0);
        Assert.assertEquals(towerTwo.size(), 0);

        Assert.assertEquals(towerThree.size(), 2);
        Assert.assertEquals(towerThree.getDisks().toString(), "[1, 2]");
    }

    @Test
    public void threeDisks() {
        HanoiTower.Tower towerOne = new HanoiTower.Tower(1);
        HanoiTower.Tower towerTwo = new HanoiTower.Tower(2);
        HanoiTower.Tower towerThree = new HanoiTower.Tower(3);

        towerOne.push(3);
        towerOne.push(2);
        towerOne.push(1);

        moveDisks(towerOne.size(), towerOne, towerThree, towerTwo);

        Assert.assertEquals(towerOne.size(), 0);
        Assert.assertEquals(towerTwo.size(), 0);

        Assert.assertEquals(towerThree.size(), 3);
        Assert.assertEquals(towerThree.getDisks().toString(), "[1, 2, 3]");
    }
}
