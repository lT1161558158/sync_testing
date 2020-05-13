package top.trister.testing;

import org.openjdk.jol.info.ClassLayout;

/**
 * 轻量级锁的获取和释放
 * cas实现轻量级锁
 * 轻量级锁时获取hashcode升级为重量级锁
 *
 */
public class T2 {
    static final Lock lock=new Lock();
    public static void main(String[] args) {
        System.out.println("无锁状态");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock){
            System.out.println("轻量级锁");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        }
        System.out.println("释放轻量级锁");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock){
            System.out.println("再次获取轻量级锁");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            System.out.println("获取对象hash");
            System.out.println(lock.hashCode());
            System.out.println("重量锁");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        }

    }
}
