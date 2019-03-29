package alg.shuc.code20190329;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author shuchang
 * Created on  2019-03-29
 */
public class WaitNotify {
    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<Integer>();

        new Producer(queue).start();
        new Producer(queue).start();
        new Consumer(queue).start();
        new Consumer(queue).start();
        new Consumer(queue).start();
    }


    static class Producer extends Thread{

        Queue<Integer> queue;
        public static final Integer MAX=10;

        public Producer(Queue<Integer> queue) {
            this.queue=queue;
        }

        @Override
        public void run() {
            while(true){
                synchronized (queue){
                    if(queue.size()==MAX){
                        try {
                            queue.wait();
                            System.out.println("满了 等待一下");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        int i = new Random().nextInt();
                        queue.offer(i);
                        System.out.println("添加元素"+i);
                        queue.notifyAll();
                    }
                }
            }
        }
    }


    static class Consumer extends Thread{
        Queue<Integer> queue;

        public Consumer(Queue<Integer> queue) {
            this.queue=queue;
        }

        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    if(queue.size()==0){
                        try {
                            queue.wait();
                            System.out.println("没有元素了,等一会");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Integer poll = queue.poll();
                        System.out.println("消费了"+poll);
                    }
                }
            }
        }
    }
}
