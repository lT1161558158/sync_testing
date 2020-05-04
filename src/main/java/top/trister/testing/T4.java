package top.trister.testing;

import org.openjdk.jol.info.ClassLayout;

/**
 * 偏向锁以及偏向锁在获取hash时直接升级
 */
public class T4 {
    static final Lock lock=new Lock();


    public static void main(String[] args) {
        System.out.println("无锁状态");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock){
            //-XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0
            //设置偏向锁的启动延迟
            System.out.println("偏向锁");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            //获取hash
            System.out.println(lock.hashCode());
            //偏向锁升级为重量锁
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        }
    }
}
