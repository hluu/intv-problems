package my.epi.tree;

import junit.framework.Assert;
import my.epi.sorting.MaxConcurrentEvents;
import org.testng.annotations.Test;



/**
 * Created by hluu on 12/12/15.
 */
public class BSTValidatorTest {

    @Test
    public void oneNodeBST() {

        BNode<Integer> root = new BNode<Integer>(2, null, null);

        Assert.assertTrue(BSTValidator.isValidBST(root));

    }

    @Test
    public void smallPositiveBST1() {

        BNode<Integer> root = new BNode<Integer>(2, BNode.create(1), BNode.create(3));

        Assert.assertTrue(BSTValidator.isValidBST(root));

    }

    @Test
    public void smallPositiveBST2() {

        BNode<Integer> root = new BNode<Integer>(2, BNode.create(1), null);

        Assert.assertTrue(BSTValidator.isValidBST(root));

    }

    @Test
    public void smallPositiveBST3() {
        BNode<Integer> root = new BNode<Integer>(2, null, BNode.create(3));

        Assert.assertTrue(BSTValidator.isValidBST(root));
    }

    @Test
    public void smallPositiveBST4() {
        BNode<Integer> root = new BNode<Integer>(2, null, new BNode(3, null, BNode.create(5)));

        Assert.assertTrue(BSTValidator.isValidBST(root));
    }

    @Test
    public void smallPositiveBST5() {
        BNode<Integer> root = new BNode<Integer>(5, new BNode(3, BNode.create(1), null), null);

        Assert.assertTrue(BSTValidator.isValidBST(root));
    }

    @Test
    public void bigPositiveBST1() {
        BNode<Integer> four = new BNode<Integer>(4);
        BNode<Integer> seven = new BNode<Integer>(7);

        BNode<Integer> one = new BNode<Integer>(1);
        BNode<Integer> six = new BNode<Integer>(6, four, seven);
        BNode<Integer> three = new BNode<Integer>(3,one, six);

        BNode<Integer> thirteen = new BNode<Integer>(13);
        BNode<Integer> fourteen = new BNode<Integer>(14, thirteen, null);

        BNode<Integer> ten = new BNode<Integer>(10,null, fourteen);
        BNode<Integer> eight = new BNode<Integer>(8, three, ten);

        Assert.assertTrue(BSTValidator.isValidBST(eight));

    }
    @Test
    public void smallNegativeBST1() {

        BNode<Integer> root = new BNode<Integer>(5, BNode.create(6), BNode.create(8));

        Assert.assertFalse(BSTValidator.isValidBST(root));

    }

    @Test
    public void smallNegativeBST2() {

        BNode<Integer> root = new BNode<Integer>(5, BNode.create(4), BNode.create(2));

        Assert.assertFalse(BSTValidator.isValidBST(root));

    }
}
