package top.trister.testing;

import org.openjdk.jol.info.ClassLayout;

/**
 * 偏向锁的重偏向
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
 * 神奇,mark word里面的线程id并没有变化
 * 感觉应该是因为jvm中并不是真的创建了一个新的线程,而是复用了原有的线程
 * 如果调整thread定义的顺序,就会出现锁升级为轻量级锁
 */
public class T7 {
    static final Lock lock=new Lock();

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("无锁");
//        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getId());
                System.out.println("偏向锁1");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        });

        t1.start();
        t1.join();
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getId());
                System.out.println("偏向锁2");
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        });
        t2.start();
        t2.join();
    }
}
