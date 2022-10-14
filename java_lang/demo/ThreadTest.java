import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        // Test1();
        // Test2();
        // Test3();
        // Test4();
        Test5();
    }

    static void Test1() {
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new LiftOff());
            t.start();
        }
        System.out.println("waiting for LiftOff");
    }

    static void Test2() {
        // ExecutorService exec = Executors.newCachedThreadPool();
        // ExecutorService exec = Executors.newFixedThreadPool(3);// 使用有限的线程集
        ExecutorService exec = Executors.newSingleThreadExecutor();// 使用单个线程，任务将排队执行
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        System.out.println("任务添加完毕");
        exec.shutdown();// 防止新任务被添加
    }

    static void Test3() {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 5; i++) {
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for (Future<String> fs : results) {
            try {
                System.out.println(fs.get());// 会阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                exec.shutdown();
            }
        }
    }

    static void Test4() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                System.err.println("Aborting...");
                System.exit(0);
            }
        }, 5000);

        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest ait = new AtomicIntegerTest();
        exec.execute(ait);

        while (true) {
            int val = ait.getValue();
            System.out.println(val);
            if (val % 2 != 0) {
                System.exit(0);
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    static void Test5() throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}

class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class TaskWithResult implements Callable {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() {
        System.out.println("call " + this.id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("call " + this.id + " done");
        return "result of TaskWithResult " + id;
    }
}

class AtomicIntegerTest implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);

    public int getValue() {
        return i.get();
    }

    private void evenIncrement() {
        i.addAndGet(2);
    }

    public void run() {
        while (true) {
            evenIncrement();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class ThreadLocalVarHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        private Random rand = new Random(47);

        protected synchronized Integer initialValue() {
            return rand.nextInt(10000);
        }
    };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }
}

class Accessor implements Runnable {
    private final int id;

    public Accessor(int idn) {
        id = idn;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVarHolder.increment();
            System.out.println(this);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "#" + id + ": " + ThreadLocalVarHolder.get();
    }
}
