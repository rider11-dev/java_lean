import java.util.Date;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

class Student implements Cloneable {
    String name;

    public static int count = 0;
    {
        count++;
        System.out.println("非静态代码块1 count=" + count);
    }
    {
        count++;
        System.out.println("非静态代码块2 count=" + count);
    }
    static {
        count++;
        System.out.println("静态代码块1 count=" + count);
    }
    static {
        count++;
        System.out.println("静态代码块2 count=" + count);
    }

    public Student(String name) {
        this.name = name;
    }

    public Student() {
        this("張三");
    }
}

class Counter {
    private static int _count = 0;

    public Counter() {
        System.out.println("对象创建");
        this._count++;
    }

    public int getCount() {
        return this._count;
    }

    protected void finalize() throws Exception {
        this._count--;
        System.out.println("对象销毁");
        try {
            throw new Exception("测试抛出异常");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}

interface Calculable {
    int calculateInt(int a, int b);
}

public class ClassTest {
    public static void main(String[] args) {
        // StuTest();
        // DateTest();
        // CounterTest();
        CalculateTest();
    }

    private static void StuTest() {
        try {
            Class cls = java.lang.Class.forName("Student");
            Student stu;
            try {
                stu = (Student) cls.newInstance();
                System.out.println(stu.name);
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void DateTest() {
        try {
            Class cls = java.lang.Class.forName("java.util.Date");
            Date dt;
            try {
                dt = (Date) cls.newInstance();
                System.out.println(dt);
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void CounterTest() {
        Counter ct1 = new Counter();
        Counter ct2 = new Counter();
        System.out.println("ct1:" + ct1.getCount());
        System.out.println("ct2:" + ct2.getCount());
        ct2 = null;
        try {
            System.gc();
            Thread.sleep(100);//
            System.out.println("ct1:" + ct1.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void CalculateTest() {
        int n1 = 10;
        int n2 = 5;
        Calculable f1 = Calculate('+');
        Calculable f2 = Calculate('-');
        System.out.printf("%d + %d = %d \n", n1, n2, f1.calculateInt(n1, n2));
        System.out.printf("%d - %d = %d \n", n1, n2, f2.calculateInt(n1, n2));
    }

    private static Calculable Calculate(char opr) {
        Calculable result;
        if (opr == '+') {
            result = new Calculable() {
                @Override
                public int calculateInt(int a, int b) {
                    return a + b;
                }
            };
        } else {
            result = (int a, int b) -> {
                return a - b;
            };
        }
        return result;
    }
}
