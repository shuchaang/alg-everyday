package alg.zhangm.code20190329;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author zhangm003
 * @date 2019/3/28 5:23 PM
 * @description
 */

public class BlockingQueue{
    public static void main(String[] args){
        Product productQueue = new Product();
        Consumer consumerQueue = new Consumer(productQueue);
        Consumer consumerQueue1 = new Consumer(productQueue);
        Produce produceQueue1 = new Produce(productQueue);
        consumerQueue.start();
        produceQueue1.start();
        consumerQueue1.start();
    }

    static public class Product {
        private LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque(8);

        public void produce(){
            int i = new Random().nextInt();
            try {
                linkedBlockingDeque.put(i);
                System.out.println(Thread.currentThread().getName() +"生产商品" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        public void consume(){

            try {
                int i = linkedBlockingDeque.take();
                System.out.println(Thread.currentThread().getName() + "消费商品" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread{
        Product product;
        Consumer(Product productQueue){
            this.product = productQueue;
        }

        @Override
        public void run(){
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                product.consume();
            }
        }
    }

    static class Produce extends Thread{
        Product product;

        Produce(Product product){
            this.product = product;
        }

        @Override
        public void run(){
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                product.produce();
            }
        }
    }

}

