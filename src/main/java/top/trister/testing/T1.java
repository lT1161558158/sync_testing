package top.trister.testing;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class T1 {
    static final Lock lock=new Lock();
    public static void main(String[] args) throws InterruptedException {
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        System.out.println(lock);//生成hashcode
        System.out.println("对象hash");
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
}
