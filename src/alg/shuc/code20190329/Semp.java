package alg.shuc.code20190329;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author shuchang
 * Created on  2019-03-29
 */
public class Semp {
    public static void main(String[] args) {
        Semaphore empty = new Semaphore(0);
        Semaphore full = new Semaphore(10);
        Semaphore mutex = new Semaphore(1);
        Queue<Integer> queue = new LinkedList<Integer>();
        new Consumer(queue, empty, full, mutex).start();
        new Consumer(queue, empty, full, mutex).start();
        new Producer(queue, empty, full, mutex).start();
    }

    static class Consumer extends Thread {
        private Semaphore empty;
        private Semaphore full;
        private Semaphore mutex;
        private Queue<Integer> queue;


        public Consumer(Queue<Integer> queue, Semaphore empty, Semaphore full, Semaphore mutex) {
            this.empty = empty;
            this.full = full;
            this.queue = queue;
            this.mutex = mutex;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    empty.acquire();
                    mutex.acquire();
                    Integer poll = queue.poll();
                    System.out.println("poll " + poll);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    full.release();
                }
            }
        }
    }

    static class Producer extends Thread {
        private Semaphore empty;
        private Semaphore mutex;
        private Semaphore full;
        private Queue<Integer> queue;

        public Producer(Queue<Integer> queue, Semaphore empty, Semaphore full, Semaphore mutex) {
            this.empty = empty;
            this.full = full;
            this.queue = queue;
            this.mutex = mutex;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    full.acquire();
                    mutex.acquire();
                    int i = new Random().nextInt();
                    System.out.println("offer " + i);
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mutex.release();
                    empty.release();
                }
            }
        }
    }
}
