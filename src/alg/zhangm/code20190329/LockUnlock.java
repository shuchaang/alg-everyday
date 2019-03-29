package alg.zhangm.code20190329;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangm003
 * @date 2019/3/27 11:01 PM
 * @description
 */
public class LockUnlock {
    public static void main(String[] args){
        Product product = new Product();
        Consumer consumer = new Consumer(product);
        Producer producer = new Producer(product);
        consumer.start();
        producer.start();
    }

    static class Product {

        private int products;
        private Lock lock = new ReentrantLock(true);
        private Condition consumer = lock.newCondition();
        private Condition producer = lock.newCondition();

        public void produce(){
            try {
                lock.lock();
                if(products == 9){
                    System.out.println(Thread.currentThread().getName() + "当前生产者线程已满，等待消费者消费，当前商品数量" + products );
                    producer.await();
                }else {
                    products++;
                    System.out.println(Thread.currentThread().getName() + "开始生产，生产商品数量" + products);
                    consumer.signalAll();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void consume(){
            try {
                lock.lock();
                if(products == 0){
                    System.out.println(Thread.currentThread().getName() + "当前商品已空，等待生产者生产，当前商品数量" + products );
                    consumer.await();
                }else {
                    products--;
                    System.out.println(Thread.currentThread().getName() + "开始消费，生产商品数量" + products);
                    producer.signalAll();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    static class Consumer extends Thread{
        Product product;
        Consumer(Product product){
            this.product = product;
        }
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
                product.consume();
            }
        }
    }


    static class Producer extends Thread{
        Product product;
        Producer(Product product){
            this.product = product;
        }
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){

                }
                product.produce();
            }

        }
    }

}