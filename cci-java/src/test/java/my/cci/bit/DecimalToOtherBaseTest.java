package my.cci.bit;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by hluu on 12/23/15.
 */
public class DecimalToOtherBaseTest {
    @Test
    public void baseTwoTest() {
        int base = 2;
        for (int i = 1; i < 100; i++) {
            Assert.assertEquals(DecimalToOtherBase.toBase(i,base),
                    Integer.toBinaryString(i));
        }
    }
}
