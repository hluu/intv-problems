package org.learning.linked_list;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hluu on 9/25/17.
 *
 * Imagine there a service that takes a lot of concurrent traffic and it uses
 * a logger that logs out one line for each request when it is completed in the below format.
 *
 * requestId started at <start ts>, finished at <finish ts>
 *
 * One constraint is the log statements should appear in the order of start ts.
 *
 * r1: t1           t3
 * r2:    t2    t4
 *
 * Output:
 *   r1 => t1,t3
 *   r2 => t2,t4
 *
 *  log statement r2 will be printed out until t1 is finished
 *
 * Approach:
 *  1) Use double linked list to maintain the request in the order of calling start
 *  2) Use HashMap to quickly lookup the node using the request id and then determine
 *     what to do based on the following conditions:
 *     * If it doesn't have prev. node, then print it out
 *     * If it has prev. node, walk backward until
 *       * Found a request that has not finished yet
 *         * No nothing
 *       * Got to head
 *         * Start from the head and walk forward
 *           * Keep printing until a node is not finished or get to the end
 *
 *
 *
 */
public class LoggingInOrderCompletionTime {
    private Logging logger;

    public static void main(String[] args) {
        System.out.println(LoggingInOrderCompletionTime.class.getName());
        Logging logger = new LoggingImpl();

        testOneAfterTheOther(logger);
        testConsecutiveRequests(logger);

        testOverlappings(logger);

        testTwoConsecutivePairs(logger);
    }

    private static void testOneAfterTheOther(Logging logger) {
        System.out.printf("====== testConsecutiveRequests =====\n");
        logger.start(1, 1);
        logger.stop(1, 3);

        logger.start(2, 4);
        logger.stop(2, 6);

        logger.start(3, 7);
        logger.stop(3, 10);
    }

    private static void testConsecutiveRequests(Logging logger) {
        System.out.printf("====== testConsecutiveRequests =====\n");
        logger.start(1, 1);
        logger.stop(1, 5);

        logger.start(2, 3);
        logger.stop(2, 7);

        logger.start(3, 4);
        logger.stop(3, 10);
    }

    private static void testOverlappings(Logging logger) {
        System.out.printf("====== testOverlappings =====\n");
        logger.start(1, 1);

        logger.start(2, 2);
        logger.stop(2, 8);

        logger.start(3, 3);
        logger.stop(3, 5);

        logger.stop(1, 10);


        logger.start(4, 11);
        logger.stop(4, 15);
    }

    private static void testTwoConsecutivePairs(Logging logger) {
        System.out.printf("====== testTwoConsecutivePairs =====\n");
        logger.start(1, 1);

        logger.start(2, 2);
        logger.stop(2, 8);
        logger.stop(1, 10);

        logger.start(3, 3);

        logger.start(4, 11);
        logger.stop(4, 15);

        logger.stop(3, 16);


    }


    private static class LoggingImpl implements  Logging {

        private Map<Long, Node> requestIdToNodeMap = new HashMap<>();
        private Node head = null;
        private Node tail = null;

        public LoggingImpl() {

        }

        public void start(long requestId, long ts) {
            LogInfo logInfo = new LogInfo(requestId, ts);
            Node node = new Node(logInfo);

            if (head == null) {
                head = node;
                tail = node;
            } else {
                // append to tail;
                tail.next = node;
                node.prev = tail;
                tail = node;
            }

            requestIdToNodeMap.put(requestId, node);

        }

        private void updateHead() {
            Node tmpNode = head.next;
            if (tmpNode != null) {
                head.next = null;
                tmpNode.prev = null;
                head = tmpNode;
            } else {
                head = null;
            }
        }

        private void removeFromMap(Node node) {
            if (node != null) {
                requestIdToNodeMap.remove(node.value.requestId);
            }
        }

        private void printRequestChainLength() {
            if (head == null) {
                System.out.println("0");
            } else {
                Node tmp = head;
                int size = 0;
                while (tmp != null) {
                    size++;
                    tmp = tmp.next;
                }
                System.out.println(size);
            }
        }

        public void stop(long requestId, long ts) {
            Node node = requestIdToNodeMap.get(requestId);
            if (node != null) {
                node.value.finishedTs = ts;

                if (node.prev == null) {
                    // walking forward until either at the end or not completed request
                    while (node != null && node.value.finishedTs != 0) {
                        printOutLogInfo(node.value);
                        node = node.next;
                        updateHead();
                        removeFromMap(node);

                    }
                    //printRequestChainLength();

                } else {
                    // walking backward until first node or not completed request
                    Node tmpNode = node.prev;
                    while (tmpNode != null && tmpNode.value.finishedTs != 0) {
                        tmpNode = tmpNode.prev;
                    }

                    if ((tmpNode != null) && (tmpNode != node) && tmpNode.value.finishedTs != 0) {
                        // now print them out
                        while (tmpNode != null && tmpNode.value.finishedTs != 0) {
                            printOutLogInfo(node.value);
                            updateHead();
                            removeFromMap(node);
                            tmpNode = tmpNode.next;
                        }
                    }
                }
            }

        }
    }

    private static void printOutLogInfo(LogInfo logInfo) {
        System.out.printf("request id: %d, started: %d, completed: %d\n",
                logInfo.requestId, logInfo.startTS, logInfo.finishedTs);
    }

    private static class Node {
        public Node(LogInfo logInfo) {
            this.value = logInfo;
        }
        public Node next;
        public Node prev;
        public LogInfo value;

        public String toString() {
            if (value != null) {
                return String.format("rId: %d, start: %d, end: %d",
                        value.requestId, value.startTS, value.finishedTs);
            } else {
                return "";
            }
        }
    }

    interface Logging {
        void start(long requestId, long ts);
        void stop(long requestId, long ts);
    }

    public static class LogInfo {
        public LogInfo(long requestId, long ts) {
            this.requestId =requestId;
            this.startTS = ts;
        }
        public long requestId;
        public long startTS;
        public long finishedTs;
    }

}