package alg.zhangm.code20190329;

/**
 * @author zhangm003
 * @date 2019/3/27 11:01 PM
 * @description 使用wait notify
 */

public class WaitNotify{
    public static void main(String[] args){
        Product product = new Product();
        Consumer consumer = new Consumer(product);
        Producer producer = new Producer(product);
        consumer.start();
        producer.start();
    }

    static class Product {

        private int products;

        public synchronized void  produce(){
            try {
                if(products == 9){
                    System.out.println(Thread.currentThread().getName() + "当前生产者线程已满，等待消费者消费，当前商品数量" + products );
                    wait();
                }else {
                    products++;
                    System.out.println(Thread.currentThread().getName() + "开始生产，剩余商品数量" + products);
                    notifyAll();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public synchronized void consume(){
            try {
                if(products == 0){
                    System.out.println(Thread.currentThread().getName() + "当前商品已空，等待生产者生产，当前商品数量" + products );
                    wait();
                }else {
                    products--;
                    System.out.println(Thread.currentThread().getName() + "开始消费，剩余商品数量" + products);
                    notifyAll();
                }
            }catch (Exception e){
                e.printStackTrace();
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
                }catch (Exception e){

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
                }catch (Exception e){

                }
                product.produce();
            }

        }
    }


}
