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


## 08.线程不安全上 & 09.线程不安全下

`Collections.synchronizedList()` 

`Collections.synchronizedMap()`

……

// Collections虽然可以用，但是效率低

解决方案：写时复制

`Class CopyOnWriteArrayList<E>`   JUC

`List<String> list = new CopyOnWriteArrayList();` // 效率较高

Map的线程安全类也在JUC中。`ConcurrentHashMap()`

## 10.8锁问题上&下

注意锁的范围，是否是同一把锁

对于普通同步方法，锁是当前实例对象。

对于静态同步方法，锁是当前类的Class对象。

对于同步方法块，锁是synchronized括号里配置的对象。括号外的锁不住。

## 12.Callable接口上&下

##### 获得多线程的方法有几种：

继承Thread类，Runnable接口、Callable接口、java线程池。

##### Runnable接口和Callable接口的区别。

Callable接口带有返回值，可以抛异常，实现方法为`call()`;
Runnable接口无返回值，不可以抛异常，实现方法为`run()`;

两者都可以使用Lambda表达式。

#### Callable接口使用示例
```java
public class CallableTest {
    public static void main(String[] args) {


        // 创建输入和输出List
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> res = new  CopyOnWriteArrayList<>();

        // 对于需要处理的list，遍历list开启线程处理
        for (Integer num : list) {
            // 使用futureTask的方法
            // 实例化任务，传递参数
            MyTask myTask = new MyTask(num);
            // 把任务放进FutureTask中
            FutureTask<Integer> futureTask = new FutureTask<>(myTask);
            // 开启线程处理
            new Thread(futureTask).start();
            // 获取处理结果
            Integer result = 0;
            try {
                result = futureTask.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            // 处理结果
            res.add(result);
        }
        System.out.println("result " + res);

    }
}

class MyTask implements Callable<Integer> {

    private Integer num;

    // 构造函数，用来向task中传递任务的参数
    public MyTask(Integer num) {
        this.num = num;
    }

    // 任务处理方法，需要做什么
    @Override
    public Integer call() throws Exception {
        System.out.println(num);
        return num * 10;
    }
}
```

#### 对比CompletableFuture
```java
public class CallableTest {

    // 任务处理方法
    public static Integer MyTask2(Integer num) {
        return num * 10;
    }

    public static void main(String[] args) {

        // 创建输入和输出List
        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> res = new  CopyOnWriteArrayList<>();

        // 使用CompletableFuture，完整版
        List<CompletableFuture<Void>> futures = list.stream().map(num ->
                CompletableFuture.supplyAsync(() -> MyTask2(num))
                .thenAccept(result -> res.add(result)))
                .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

        System.out.println(res);

        // 使用CompletableFuture，简化版，可以用idea的提示自动简化
        CompletableFuture.allOf(list.stream().map(num ->
                CompletableFuture.supplyAsync(() -> MyTask2(num))
                        .thenAccept(res::add)).toArray(CompletableFuture[]::new)).join();

        System.out.println(res);

        // 使用CompletableFuture，完整版，带异常处理
        CompletableFuture.allOf(list.stream().map(num ->
                CompletableFuture.supplyAsync(() -> MyTask2(num))
                        .exceptionally(e -> {
                            e.printStackTrace();
                            return 0;
                        })
                        .thenAccept(res::add)).toArray(CompletableFuture[]::new)).join();
        System.out.println(res);

    }

}
```

**对比之下，CompletableFuture代码更简洁。但是FutureTask的思路更为清晰。**


## 14.工具类CountDownLatch 上 && 下

减少计数————秦灭六国一统天下

```java
CountDownLatch cd = new CountDownLatch(5);

cd.countDown();

cd.await;
```

## 16.工具类CyclicBarrier

循环栅栏————集齐七颗龙珠召唤神龙

## 17.工具类 信号灯Semaphone

三个停车位，停六辆汽车。
资源争抢。

## 18.读写锁

#### MySQL锁

* 一行数据可以加多个读锁。

* 一行数据不可以加多个写锁。写锁是独占锁。

* 读写锁都会导致死锁。解决方法：说话算话。锁哪行，读（写）哪行，不要执行其他操作。

* **严禁**使用间隙锁。（范围锁）

#### ReentrantReadWriteLock 读写锁

## 19.阻塞队列

BlockingQueue 

在多线程领域：所谓阻塞，在某些情况下回**挂起**线程（即阻塞），一旦条件满足，被挂起的线程又会自动**被唤起**。

ArrayBlockingQueue：数组组成的有界阻塞队列

LinkedBlockingQueue：链表结构组成的有界（大小值默认为Integer.MAX_VALUE）阻塞队列。

SynchronousQueue：不存储元素的阻塞队列，即单个元素的队列。

## 20.线程池简介

ThreadPoolExecutor

## 21.线程池底层原理

ThreadPoolExecutor 一共七个参数

**如果队列满了，且正在运行的线程数量还小于maximumPoolSize，那么创建的非核心线程立刻执行这个任务！！而不是处理阻塞队列里面的任务**。

**线程池绝对不允许使用Excutors去创建，而是通过ThreadPoolExecutor的方式，这样可以更加明确线程池的运行规则，避免资源耗尽的风险**。

## 22.线程池拒绝策略、手写线程池

4个拒绝策略。

## 23.java8特性2

### 函数式接口

java.util.function.*

四大函数式接口

消费型
```java
// void accept(T t); 消费型 有参数 无返回值
Consumer<String> consumer = t -> {
    System.out.println(t);
};
consumer.accept("hello");
```

供给型
```java
// Supplier; T get(); 供给型 无参数 有返回值
Supplier<String> supplier = () -> {
    return "hello";
};
System.out.println(supplier.get());
```

函数型
```java
// Function; R apply(T t); 函数型 有参数 有返回值
Function<Integer, String> function = t -> {
    return t + " toString";
};
System.out.println(function.apply(1));
```

断定型
```java
// Predicate; boolean test(T t); 断定型 有参数 返回值是Boolean类型
Predicate<String> predicate = t -> {
    return "hello".equalsIgnoreCase(t);
};
System.out.println(predicate.test("HELLO"));
```

 ## 24.分支合并框架
 
 Fork Join
 
 ForkJoinPool
 
 ForkJoinTask
 
 RecursiveTask   // 递归任务，继承后可以实现递归调用的任务
 
 ```java
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MyTask extends RecursiveTask<Integer>{
    public static final int ADJAST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if((end-begin)<=ADJAST_VALUE){
            for (int i = begin; i <=end ; i++) {
                result = result+i;
            }
        }else{
            int mid =(begin+end)/2;
            MyTask myTask1 = new MyTask(begin,mid);
            MyTask myTask2 = new MyTask(mid+1,end);
            myTask1.fork();
            myTask2.fork();

            result = myTask1.join()+myTask2.join();

        }

        return result;
    }
}


/**
 * 分支合并例子
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveTask
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0,100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask forkJoinTask = forkJoinPool.submit(myTask);
        System.out.println(forkJoinTask.get());
    }
}

 ```
 
 ForkJoinTask与一般任务的主要区别在于它需要实现compute方法，在这个方法里，首先需要判断任务是否足够小，如果足够小就直接执行任务。如果不足够小，就必须分割成两个子任务，每个子任务在调用fork方法时，又会进入compute方法，看看当前子任务是否需要继续分割成子任务，如果不需要继续分割，则执行当前子任务并返回结果。使用join方法会等待子任务执行完并得到其结果。
 
 
 ## 25.异步回调
 
 CompletableFuture
 
 
 



