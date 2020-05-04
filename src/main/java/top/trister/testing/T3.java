package top.trister.testing;

import org.openjdk.jol.info.ClassLayout;


/**
 * 偏向锁以及偏向锁一旦出现其他线程争用时升级为轻量锁
 */
public class T3 {
    static final Lock lock=new Lock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("无锁状态");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock){
            //-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
            //设置偏向锁的启动延迟
            System.out.println("偏向锁");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
        System.out.println("释放偏向锁");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        Thread thread = new Thread(() -> {
            synchronized (lock){
                System.out.println("其他线程偏向锁");
                //偏向模式无法继续使用,升级为轻量锁
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        });
        thread.start();
        thread.join();
    }
}
