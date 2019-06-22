package org.learning.system;

/**
 * Implement (code) a Key value store with transactions.
 *
 * Write a fully functional code with test cases.
 *
 * APIs:
 *
 * 1) Set(key,value)
 * 2  Get(key)
 * 3) Delete(key)
 *
 * for transactions
 * - begin
 * - commit
 * - rollback
 *
 * Resource:
 * - https://leetcode.com/discuss/interview-question/279913/Key-Value-Store-with-transaction
 *
 *
 *
 *  The idea is to use a hashmap to store the actual key-value pairs and
 *  a linked list to maintain the commit log.
 *  The commit log entries will have a transaction id, type of mutation
 *  performed (set or delete) and previous value for set operation so we can
 *  restore the state in case of a rollback.
 *
 *  Any mutations to both the commit logs and key-value pairs need to be
 *  synchronous if multiple threads access the store.
 */
public class KVStore {
}
