# Java Thread类

## 线程的常见结构

### 线程中的构造器

- `public Thread()` :分配一个新的线程对象。
- `public Thread(String name)` :分配一个指定名字的新的线程对象。
- `public Thread(Runnable target)` :指定创建线程的目标对象，它实现了Runnable接口中的run方法
- `public Thread(Runnable target,String name)` :分配一个带有指定目标新的线程对象并指定名字。

## Thread类的常用方法

### 常用方法

Thread类中有一些方法，可以使得方便线程的使用

* `start()` 启动线程、调用线程的`run()`。

  源码声明为`public void start()`

* `run()` 将线程要执行的操作，声明在`run()`中。

  源码声明为`public void run()`

* `currentThread` 获取当前执行代码对应的线程

  源码声明为`public static Thread currentThread()`

* `setName` 设置线程名

  源码声明为`public void setName(String name)`

* `getName` 获取线程名

  源码声明为`public String getName()`

* `sleep(long millis)` 静态方法，调用时，可以使得当前线程睡眠指定毫秒值

  源码声明为`public static void sleep(long millis)`

  ==`sleep`是静态方法，不论是对象还是类调用都没有区别，都是对当前类进行堵==

* `yield()` 静态方法，一旦执行此方法，就释放CPU的执行权

  源码声明为`public static void yield()`

* `join()` 在线程A中，通过线程B调用`join()` 就意味着线程A进入阻塞状态，直到线程B执行结束，线程A才结束阻塞状态，继续执行

  * `void join() `：等待该线程终止。 
  * `void join(long millis)` ：等待该线程终止的时间最长为 millis 毫秒。如果millis时间到，将不再等待。 
  * `void join(long millis, int nanos) `：等待该线程终止的时间最长为 millis 毫秒 + nanos 纳秒。 

* `isAlive()` 判断当前线程是否还存活。返回`boolean`类型数据

  源码声明为`public final boolean isAlive()`

### 过时方法

Thread类中还有一些过时的方法:

* `stop()`

  `public final void stop()`

* `suspend()\resume()`

  `void suspend() / void resume()`

  `suspend()`调用会导致线程暂停，但不会释放任何锁资源，导致其他线程都无法访问被它占用的锁，直到调用`resume()`

  表示暂停和恢复。两个方法必须成对的出现，否则非常容易发生死锁现象

### 优先级方法

还有一些方法涉及到线程的优先级

* `getPriority()` : 获取线程的优先级

  `public final int getPriority()`

* `setPriority()` :设置线程的优先级，范围在[1,10]之间。

  `public final void setPriority(int newPriority)`

Thread类的三个优先级常量：

- `MAX_PRIORITY（10`）：最高优先级 
- `MIN _PRIORITY （1）`：最低优先级
- `NORM_PRIORITY （5）`：普通优先级，默认情况下main线程具有普通优先级。

> **线程调度策略**
>
> * 分时调度：所有线程`"轮流使用"`CPU的使用权，并且平均分配每个线程占用CPU的时间
> * 抢占式调度：让`"优先级高"`的线程以`"较大概率"`优先使用CPU。如果线程的优先级相同，那么会随机选择一个`(线程随机性)`

## Thread类的生命周期

### JDK1.5之前：5种状态

线程的生命周期有五种状态：新建（New）、就绪（Runnable）、运行（Running）、阻塞（Blocked）、死亡（Dead）。CPU需要在多条线程之间切换，于是线程状态会多次在运行、阻塞、就绪之间切换。

![image-20240604000553851](C:\Users\27813\AppData\Roaming\Typora\typora-user-images\image-20240604000553851.png)

**1.新建**

当一个Thread类或其子类的对象被声明并创建时，新生的线程对象处于新建状态。此时它和其他Java对象一样，仅仅由JVM为其分配了内存，并初始化了实例变量的值。此时的线程对象并没有任何线程的动态特征，程序也不会执行它的线程体run()。

**2.就绪**

但是当线程对象调用了start()方法之后，就不一样了，线程就从新建状态转为就绪状态。JVM会为其创建方法调用栈和程序计数器，当然，处于这个状态中的线程并没有开始运行，只是表示已具备了运行的条件，随时可以被调度。至于什么时候被调度，取决于JVM里线程调度器的调度。

> 注意：
>
> 程序只能对新建状态的线程调用start()，并且只能调用一次，如果对非新建状态的线程，如已启动的线程或已死亡的线程调用start()都会报错IllegalThreadStateException异常。

**3.运行**

如果处于就绪状态的线程获得了CPU资源时，开始执行run()方法的线程体代码，则该线程处于运行状态。如果计算机只有一个CPU核心，在任何时刻只有一个线程处于运行状态，如果计算机有多个核心，将会有多个线程并行(Parallel)执行。

当然，美好的时光总是短暂的，而且CPU讲究雨露均沾。对于抢占式策略的系统而言，系统会给每个可执行的线程一个小时间段来处理任务，当该时间用完，系统会剥夺该线程所占用的资源，让其回到就绪状态等待下一次被调度。此时其他线程将获得执行机会，而在选择下一个线程时，系统会适当考虑线程的优先级。

**4.阻塞**

当在运行过程中的线程遇到如下情况时，会让出 CPU 并临时中止自己的执行，进入阻塞状态：

* 线程调用了sleep()方法，主动放弃所占用的CPU资源；
* 线程试图获取一个同步监视器，但该同步监视器正被其他线程持有；
* 线程执行过程中，同步监视器调用了wait()，让它等待某个通知（notify）；
* 线程执行过程中，同步监视器调用了wait(time)
* 线程执行过程中，遇到了其他线程对象的加塞（join）；
* 线程被调用suspend方法被挂起（已过时，因为容易发生死锁）；

当前正在执行的线程被阻塞后，其他线程就有机会执行了。针对如上情况，当发生如下情况时会解除阻塞，让该线程重新进入就绪状态，等待线程调度器再次调度它：

* 线程的sleep()时间到；
* 线程成功获得了同步监视器；
* 线程等到了通知(notify)；
* 线程wait的时间到了
* 加塞的线程结束了；
* 被挂起的线程又被调用了resume恢复方法（已过时，因为容易发生死锁）；

**5.死亡**

线程会以以下三种方式之一结束，结束后的线程就处于死亡状态：

* run()方法执行完成，线程正常结束
* 线程执行过程中抛出了一个未捕获的异常（Exception）或错误（Error）
* 直接调用该线程的stop()来结束该线程（已过时）

### JDK1.5之后：6种状态

> `Thread`类中存在一个内部枚举类`enum State`表示着线程的六种状态
>
> ````java
> public enum State {
>         NEW,	//新建
>         RUNNABLE,	//可运行
>         BLOCKED,	//堵塞
>         WAITING,	//等待
>         TIMED_WAITING,	//时间等待
>         TERMINATED;	//死亡
>     }
> ````

- `NEW（新建）`：线程刚被创建，但是并未启动。还没调用start方法。

- `RUNNABLE（可运行）`：这里没有区分就绪和运行状态。因为对于Java对象来说，只能标记为可运行，至于什么时候运行，不是JVM来控制的了，是OS来进行调度的，而且时间非常短暂，因此对于Java对象的状态来说，无法区分。

- `Teminated（被终止）`：表明此线程已经结束生命周期，终止运行。

- 重点说明，根据Thread.State的定义，**阻塞状态分为三种**：`BLOCKED`、`WAITING`、`TIMED_WAITING`。
  - `BLOCKED（锁阻塞）`：在API中的介绍为：一个正在阻塞、等待一个监视器锁（锁对象）的线程处于这一状态。只有获得锁对象的线程才能有执行机会。
    - 比如，线程A与线程B代码中使用同一锁，如果线程A获取到锁，线程A进入到Runnable状态，那么线程B就进入到Blocked锁阻塞状态。
  - `TIMED_WAITING（计时等待）`：在API中的介绍为：一个正在限时等待另一个线程执行一个（唤醒）动作的线程处于这一状态。
    - 当前线程执行过程中遇到Thread类的`sleep`或`join`，Object类的`wait`，LockSupport类的`park`方法，并且在调用这些方法时，`设置了时间`，那么当前线程会进入TIMED_WAITING，直到时间到，或被中断。
  - `WAITING（无限等待）`：在API中介绍为：一个正在无限期等待另一个线程执行一个特别的（唤醒）动作的线程处于这一状态。
    - 当前线程执行过程中遇到遇到Object类的`wait`，Thread类的`join`，LockSupport类的`park`方法，并且在调用这些方法时，`没有指定时间`，那么当前线程会进入WAITING状态，直到被唤醒。
      - 通过Object类的wait进入WAITING状态的要有Object的notify/notifyAll唤醒；
      - 通过Condition的await进入WAITING状态的要有Condition的signal方法唤醒；
      - 通过LockSupport类的park方法进入WAITING状态的要有LockSupport类的unpark方法唤醒
      - 通过Thread类的join进入WAITING状态，只有调用join方法的线程对象结束才能让当前线程恢复；



