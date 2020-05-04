package top.trister.testing;

import org.openjdk.jol.info.ClassLayout;

/**
 * 重写了hash方法后,轻量锁不会在获取hash时进行升级
 * 偏向级锁也一样的
 */
public class T6 {
    static final Lock2 lock=new Lock2();

    public static void main(String[] args) {
        System.out.println("无锁状态");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        synchronized (lock){
            System.out.println("轻量锁");
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            //获取hash
            System.out.println(lock.hashCode());
            //还是轻量锁
            System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        }
    }
}
