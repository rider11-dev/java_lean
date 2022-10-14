import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class CollectionTest {
    public static void main(String[] args) {
        // Test1();
        // Test2();
        // Test3();
        // Test4();
        // Test5();
        // Test6();
        // Test7(args[0].charAt(0));
        Test8();
    }

    private static void Test1() {
        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        list1.add("one");
        list1.add("two");
        list1.add("one");
        list2.addAll(list1);
        Print(list2);
        Iterator<String> it1 = list1.iterator();
        while (it1.hasNext()) {
            if (it1.next().equals("one")) {
                System.out.println("删除one");
                it1.remove();// 只能通过Iterator删除，如果用list1.remove，会导致并发修改异常java.util.ConcurrentModificationException
            }
        }
        Print(list1);
        Print(list2);

        List<String> list3 = list2.subList(1, 2);
        Print(list3);
        list3.set(0, "a");
        Print(list3);
        Print(list2);
    }

    private static void Test2() {
        HashSet<String> hs = new HashSet<String>();
        hs.add("Java");
        hs.add("Python");
        hs.add("C");
        hs.add("Golang");
        hs.add("java");
        hs.add("Java");
        Print(hs);// 存储顺序随机

        TreeSet<Double> scores = new TreeSet<>();
        scores.add(100.0);
        scores.add(60.0);
        scores.add(50.0);
        scores.add(70.0);
        scores.add(80.0);
        Print(scores);

        SortedSet<Double> score1 = scores.headSet(60.0);// <60
        Print(score1);
        SortedSet<Double> score2 = scores.tailSet(80.0);// ≥80
        Print(score2);
    }

    private static void Test3() {
        HashMap<String, String> users = new HashMap<>();
        users.put("11", "张三");
        users.put("22", "李四");
        users.put("33", "王五");
        Iterator<String> it = users.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            String val = users.get(key);
            System.out.println("学号：" + key + "，姓名：" + val);
        }
        Scanner input = new Scanner(System.in);
        System.out.println("请输入要删除的学号：");
        String no = input.next();
        if (users.containsKey(no)) {
            users.remove(no);

            for (Map.Entry<String, String> entry : users.entrySet()) {
                System.out.println("学号：" + entry.getKey() + "，姓名：" + entry.getValue());
            }

        } else {
            System.out.println("该学生不存在");
        }
        // 遍历的另一种方式
        Iterator<Map.Entry<String, String>> entries = users.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            System.out.println("学号：" + entry.getKey() + "，姓名：" + entry.getValue());
        }
    }

    private static void Test4() {
        List<String> products = new ArrayList<String>();
        products.add("橘子");
        products.add("苹果");
        products.add("西红柿");
        Print(products);
        Collections.fill(products, "未填写");
        Print(products);

        List<Integer> nums = new ArrayList<Integer>();
        nums.add(2);
        nums.add(-5);
        nums.add(3);
        nums.add(0);
        Print(nums);
        System.out.printf("最大:%d\n", Collections.max(nums));
        System.out.printf("最小:%d\n", Collections.min(nums));
        Collections.replaceAll(nums, 0, 1);
        System.out.println("替换后:");
        Print(nums);
        System.out.printf("-5出现次数:%d\n", Collections.frequency(nums, -5));
        Collections.sort(nums);
        System.out.println("排序后:");
        Print(nums);
        System.out.printf("二分查找[3]:%d\n", Collections.binarySearch(nums, 3));
    }

    private static void Test5() {
        Scanner input = new Scanner(System.in);
        List<String> src = new ArrayList<>();
        List<String> dest = new ArrayList<>();
        dest.add("苏打水");
        dest.add("木糖醇");
        dest.add("方便面");
        dest.add("火腿肠");
        dest.add("冰红茶");
        System.out.println("原有商品如下：");
        Print(dest);
        System.out.println("输入替换的商品名称：");
        for (int i = 0; i < 3; i++) {
            System.out.printf("第 %d 个商品：", i + 1);
            src.add(input.next());
        }
        Collections.copy(dest, src);
        System.out.println("当前商品有：");
        Print(dest);
    }

    private static void Test6() {
        Collection<String> objs = new HashSet<String>();
        objs.add("java");
        objs.add("c");
        objs.add("c++");
        objs.forEach(obj -> System.out.println("迭代集合元素：" + obj));

        objs.removeIf(item -> item.length() < 2);

        Iterator<String> it = objs.iterator();
        it.forEachRemaining(item -> System.out.println("lambda+iterator：" + item));
    }

    private static void Test7(char cmd) {
        IntStream is = IntStream.builder().add(20).add(13).add(-2).add(18).build();
        switch (cmd) {
            case '1':
                System.out.printf("最小值：%d\n", is.min().getAsInt());
                break;
            case '2':
                System.out.printf("总和：%d\n", is.sum());
                break;
            case '3':
                System.out.printf("总数：%d\n", is.count());
                break;
            case '4':
                System.out.printf("平均值：%g\n", is.average().getAsDouble());
                break;
            case '5':
                System.out.printf("所有元素的平方是否都>20：%b\n", is.allMatch(ele -> ele * ele > 20));
                break;
            case '6':
                System.out.printf("是否包含任何元素的平方>20：%b\n", is.anyMatch(ele -> ele * ele > 20));
                break;
            case '7':
                IntStream newIs = is.map(ele -> ele * 2 + 1);
                newIs.forEach(ele -> System.out.printf("%d ", ele));
                break;
            default:
                break;
        }

    }

    private static void Test8() {
        // 创建一个集合
        Collection<String> objs = new HashSet<String>();
        objs.add(new String("C语言中文网Java教程"));
        objs.add(new String("C语言中文网C++教程"));
        objs.add(new String("C语言中文网C语言教程"));
        objs.add(new String("C语言中文网Python教程"));
        objs.add(new String("C语言中文网Go教程"));
        System.out.printf("包含'中文网'的元素个数：%d\n", objs.stream().filter(ele -> ele.contains("中文网")).count());
        objs.stream().mapToInt(ele -> ele.length())
                .forEach(System.out::println);
    }

    private static <T> void Print(Collection<T> list) {
        if (list == null) {
            System.out.println("list is null");
            return;
        }
        System.out.println("==>");
        Iterator<T> it1 = list.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }
    }
}
