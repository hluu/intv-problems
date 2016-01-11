package org.learning.numbers;

/**
 *
 * Problem statement:
 *  This problem pertains to the field of bio-informatics, but it does not require any
 *  specialized biological knowledge.
 *
 *  All DNA is composed of sequences of four "letters" of nucleotides, whose
 *  abbreviations are A, C, G, and T, strung together, for example: "AAGATCCGTC".
 *
 *  A typical chromosome (a very long DNA molecule) may have several millions
 *  of these letters strung together.  When studying DNA, it is sometimes useful to
 *  know when a particular sequence of letters is repeated.
 *
 *  For this problem, we would like to identify all the 10-letter-long sequences that
 *  occur more than once in any given chromosome.
 *
 *  Write a program that prints out all such sequences to the standard output stream,
 *  sorted in alphabetical order.
 *
 * Approach:
 *  * Notice the # of possible nucleotides is 4, which means 2 bits can be used to represent one
 *    nucleotide
 *  * 10-letter long sequence will requires 20 bits
 *  * We can use an array to count the # of occurrences of each 10-letter long sequence, which
 *    is converted to 20 bits value and use that as the index into the array
 *
 *  * Steps:
 *      1) start with reading up to 10 characters,
 *      2) convert that to 20-bit number, index that into the array and bump up the count
 *      3) read next character and append it to the end and then remove first character. Now repeat
 *        step 2 and 3 until there are no more characters.
 *
 */
public class DetectRepeatedSequence {
}
