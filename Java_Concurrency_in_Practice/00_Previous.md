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
###### 多线程编程模板
1. 线程、操作、资源类
* 先写资源类，资源类里面写操作方法，最后写线程。
2. 高内聚低耦合
* 操作在资源类里面（高内聚），与线程低耦合

