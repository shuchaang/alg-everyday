package alg.shuc.code20190329;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shuchang
 * Created on  2019-03-29
 */
public class LockCondition {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition empty = lock.newCondition();
        Condition full = lock.newCondition();

        Queue<Integer> queue = new LinkedList<Integer>();

        new Producer(queue, lock, empty, full).start();
        new Consumer(queue, lock, empty, full).start();
        new Consumer(queue, lock, empty, full).start();
    }

    static class Producer extends Thread {
        private Queue<Integer> queue;
        private Lock lock;
        private Condition emtpy;
        private Condition full;
        public static final int Max = 10;

        public Producer(Queue<Integer> queue, Lock lock, Condition empty, Condition full) {
            this.queue = queue;
            this.lock = lock;
            this.emtpy = empty;
            this.full = full;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    if (queue.size() == Max) {
                        full.await();
                    } else {
                        int i = new Random().nextInt();
                        queue.offer(i);
                        System.out.println("offer " + i);
                        emtpy.signalAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
        }
    }


    static class Consumer extends Thread {
        private Queue<Integer> queue;
        private Lock lock;
        private Condition emtpy;
        private Condition full;

        public Consumer(Queue<Integer> queue, Lock lock, Condition empty, Condition full) {
            this.queue = queue;
            this.lock = lock;
            this.emtpy = empty;
            this.full = full;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                if (queue.isEmpty()) {
                    try {
                        emtpy.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Integer poll = queue.poll();
                    System.out.println("poll " + poll);
                    full.signalAll();
                }
            }
        }
    }
}
