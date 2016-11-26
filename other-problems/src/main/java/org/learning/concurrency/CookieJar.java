package org.learning.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by hluu on 1/14/16.
 *
 * Problem:
 *  A cookie jar provides cookie consumers to enjoy cookies and cookie bakers to
 *  add cookies to the jar.
 *
 *  Develop a CookieJar to hold cookies such that cookies consumers have the ability to
 *  take cookies to enjoy without waiting on each other.
 *
 *  This example is about how to use read and write locks.
 *      Read lock protects writers and readers, but allow concurrent readers
 *      Write lock protects readers and from multiple writers
 */
public class CookieJar {
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Lock readLock = rwLock.readLock();
    private Lock writeLock = rwLock.writeLock();

    private volatile  int count;
    public CookieJar(int initialNumCookies) {

        this.count = initialNumCookies;
    }

    public Cookie takeCookie() {
        try {
            readLock.lock();
            count--;
            return new Cookie();
        } finally {
             readLock.unlock();;
        }
    }

    public void addCookie(Cookie cookie) {
        try {
            writeLock.lock();
            count++;
        } finally {
            writeLock.unlock();;
        }
    }

    private static class Cookie {
        public Cookie() {

        }
    }

    public static void main(String[] args) {

        System.out.println("CookieJar.main");

        CookieJar cookieJar = new CookieJar(5);

        cookieJar.takeCookie();
        cookieJar.takeCookie();
        cookieJar.takeCookie();

        cookieJar.addCookie(new Cookie());
    }

}



