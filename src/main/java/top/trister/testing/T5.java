package top.trister.testing;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 锁争抢时升级为重量级锁
 */
public class T5 {
    static final Lock lock=new Lock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("无锁状态");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        CountDownLatch downLatch=new CountDownLatch(1);
        Thread thread=new Thread(()->{
            synchronized (lock){
                downLatch.countDown();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        downLatch.await();
        synchronized (lock){
            System.out.println("锁争抢时升级为重量级锁");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            lock.notify();
        }
        thread.join();
    }
}
