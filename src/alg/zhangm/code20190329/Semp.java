package alg.zhangm.code20190329;

import java.util.concurrent.Semaphore;

/**
 * @author zhangm003
 * @date 2019/3/30 5:04 PM
 * @description 使用信号量实现生产消费
 */
public class Semp {

    public static void main(String[] args){
        Product product = new Product();
        Consumer consumer = new Consumer(product);
        Produce produce = new Produce(product);
        Produce produce1 = new Produce(product);
        consumer.start();
        produce.start();
        produce1.start();

    }

    static class Product{
        Semaphore full = new Semaphore(10);
        Semaphore empty = new Semaphore(0);
        Semaphore mutex = new Semaphore(1);

        private int product;

        public void consume(){

            try {
                empty.acquire();
                mutex.acquire();
                product --;
                System.out.println("当前剩余商品数" + product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                full.release();
                mutex.release();
            }
        }

        public void produce(){
            try {
                full.acquire();
                mutex.acquire();
                product ++;
                System.out.println("当前商品个数" + product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                mutex.release();
                empty.release();
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
