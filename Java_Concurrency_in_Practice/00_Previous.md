# 预备知识--juc视频

## 01. juc是什么

#### java api的三个包
java.util.concurrent
java.util.concurrent.atomic
java.util.concurrent.locks

#### 进程、线程的基础。
线程状态：
* new（新建）
* Runnable（准备就绪）
* Blocked（阻塞）
* Waiting（**不见不散**）
* Timed_Waiting（**过时不候**）
* Terminated（终结）

wait和sleep区别
* wait放开资源/锁（在哪睡，在哪醒），Object的方法
* sleep不放开

#### 并发和并行
并发：同一时刻，多个线程在访问同一个资源，多一个线程对一个点
并行：多项工作一起执行，之后再汇总
自测流程：功能测试、验证数据准确性、并发压力测试。
AD工具--测试并发，压力测试。


## 02. lock

#### 复习Synchronized
多线程编程模板上
1. 线程、操作、资源类
* 先写资源类，资源类里面写操作方法，最后写线程。
2. 高内聚低耦合
* 操作在资源类里面（高内聚），与线程低耦合

#### Lock
Lock接口的实现
* ReentrantLock  可重入锁，可以反复使用

#### 创建线程的方式

#### 实现Runnable的方法
* 新建类实现runnable接口
* 匿名内部类
* lambda表达式

Lock的底层实现比synchronized更好，性能更优，且灵活。尽量使用lock而不是synchronized

## 03. Java8特性1
Lambda表达式-----拷贝小括号，写死右箭头，落地大括号

#### 什么时候可以用，什么时候不能用Lambda表达式
要求接口只有一个未使用的方法（函数式接口。@FunctionalInterface）

接口里面可以写default方法，也可以写static方法。static方法可以直接用类名调用。

#### 四大函数式接口

## 04. 生产者消费者

多线程编程模板中：判断、干活、通知。

## 05. 线程间通信（上）

多线程编程模板下：注意线程间的虚假唤醒。`wait()`调用之前，需要while循环判断。严禁使用if判断。

## 06. 线程间通信（下）

Lock使用的是`await()`、`signal()`，使用condition（lock的钥匙）。

```java
private Lock lock = new ReentrantLock();
private Condition cd = lock.newCondition();

// 使用
lock.lock();

  cd.await()

  cd.signal();

lock.unlock();
```

## 07. 线程间定制化通信

lock可以有多个condition（一把钥匙，可以有好几把锁）

定制化通信的前提是，明确知道有哪几个线程，什么条件启用新线程。


## 08.线程不安全上 && 09.线程不安全下

`Collections.synchronizedList()` 

`Collections.synchronizedMap()`

……

// Collections虽然可以用，但是效率低

解决方案：写时复制

`Class CopyOnWriteArrayList<E>`   JUC

`List<String> list = new CopyOnWriteArrayList();` // 效率较高

Map的线程安全类也在JUC中。`ConcurrentHashMap()`











