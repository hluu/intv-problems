package my.cci.linked_list;

import junit.framework.Assert;
import org.common.LinkedListUtil;
import org.common.SLNode;
import org.testng.annotations.Test;

/**
 * Created by hluu on 1/7/16.
 */
public class PalindromeTest {
    @Test
    public void oneLetterString() {
        SLNode<Character> l = LinkedListUtil.stringToLinkedList("a");

        Assert.assertFalse(Palindrome.isPalindrome(l));
    }

    @Test
    public void twoLettersStringPalindrome() {
        SLNode<Character> l = LinkedListUtil.stringToLinkedList("aa");

        Assert.assertTrue(Palindrome.isPalindrome(l));
    }

    @Test
    public void twoLettersStringNoPalindrome() {
        SLNode<Character> l = LinkedListUtil.stringToLinkedList("ab");

        Assert.assertFalse(Palindrome.isPalindrome(l));
    }

    @Test
    public void threeLettersStringPalindrome() {
        SLNode<Character> l = LinkedListUtil.stringToLinkedList("aaa");

        Assert.assertTrue(Palindrome.isPalindrome(l));

        l = LinkedListUtil.stringToLinkedList("aba");

        Assert.assertTrue(Palindrome.isPalindrome(l));
    }

    @Test
    public void threeLettersStringNoPalindrome() {
        SLNode<Character> l = LinkedListUtil.stringToLinkedList("abc");

        Assert.assertFalse(Palindrome.isPalindrome(l));
    }

    @Test
    public void fourLettersStringPalindrome() {
        SLNode<Character> l = LinkedListUtil.stringToLinkedList("aaaa");

        Assert.assertTrue(Palindrome.isPalindrome(l));

        l = LinkedListUtil.stringToLinkedList("abba");

        Assert.assertTrue(Palindrome.isPalindrome(l));
    }

    @Test
    public void fourLettersStringNoPalindrome() {
        SLNode<Character> l = LinkedListUtil.stringToLinkedList("abca");

        Assert.assertFalse(Palindrome.isPalindrome(l));
    }
}
