package alg.shuc.code20190329;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author shuchang
 * Created on  2019-03-29
 */
public class BlockingQ {


    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
        new Consumer(queue).start();
        new Consumer(queue).start();
        new Consumer(queue).start();
        new Producer(queue).start();
        new Producer(queue).start();
    }

    static class Consumer extends Thread {
        private BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                int i = new Random().nextInt();
                try {
                    queue.put(i);
                    System.out.println("put value " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer extends Thread {
        private BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Integer take = queue.take();
                    System.out.println("take value " + take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
