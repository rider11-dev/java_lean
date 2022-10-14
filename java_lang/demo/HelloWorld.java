
// import java.io.Console;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("你好 java");
        // BitwiseOpt();
        // StringTest();
        // InputTest();
        // ForeachTest();
        // NumberFormatTest();
        // DateTest();
        // ObjTest(new Date());
        // ObjTest("as;dfasd");
        // StreamTest();
        // ArrayTest();
        ArrayCopyTest();
    }

    private static void ArrayCopyTest() {
        int[] scores = new int[] { 57, 81, 68, 75, 91, 66, 75, 84 };
        System.out.println("src:" + Arrays.toString(scores));
        System.out.println("===============Arrays.copyOf=============");
        int[] cp1 = Arrays.copyOf(scores, scores.length);
        System.out.println("cp1:" + Arrays.toString(cp1));
        cp1[0] = 1;
        System.out.println("cp1:" + Arrays.toString(cp1));
        System.out.println("src:" + Arrays.toString(scores));
        System.out.println("===============Arrays.copyOfRange=============");
        int[] cp2 = Arrays.copyOfRange(scores, 0, scores.length + 1);
        System.out.println("cp2:" + Arrays.toString(cp2));
        cp2[0] = 1;
        System.out.println("cp2:" + Arrays.toString(cp2));
        System.out.println("src:" + Arrays.toString(scores));
        System.out.println("===============System.arraycopy=============");
        int[] cp3 = new int[scores.length];
        System.arraycopy(scores, 0, cp3, 0, scores.length);
        System.out.println("cp3:" + Arrays.toString(cp3));
        cp3[0] = 1;
        System.out.println("cp3:" + Arrays.toString(cp3));
        System.out.println("src:" + Arrays.toString(scores));
        System.out.println("===============Object.clone=============");
        int[] cp4 = scores.clone();
        System.out.println("cp4:" + Arrays.toString(cp4));
        cp4[0] = 1;
        System.out.println("cp4:" + Arrays.toString(cp4));
        System.out.println("src:" + Arrays.toString(scores));
    }

    private static void ArrayTest() {
        int[] nums = { 1, 2, 3, 4 };
        System.out.println(Arrays.toString(nums));
        double[][] scores = { { 100, 99, 99 }, { 100, 98, 97 }, { 100, 100, 99.5 }, { 99.5, 99, 98.5 } };
        System.out.println(Arrays.deepToString(scores));
        ;
        for (double[] row : scores) {
            for (double col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println("===========Arrays工具类===========");
        int[] arr1 = new int[] { 3, 4, 25, 16, 30, 18 };
        Arrays.parallelSort(arr1);
        System.out.println(Arrays.toString(arr1));
        int[] arr2 = new int[] { 13, -4, 25, 16, 30, 18 };
        Arrays.parallelPrefix(arr2, new IntBinaryOperator() {
            // left代表当前索引的前一个元素（计算第一个元素时left为1），right是当前索引的元素
            public int applyAsInt(int left, int right) {
                return left * right;
            }
        });
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = new int[5];
        Arrays.parallelSetAll(arr3, new IntUnaryOperator() {
            // operand代表正在计算的元素索引
            public int applyAsInt(int operand) {
                return operand * 5;
            }
        });
        System.out.println(Arrays.toString(arr3));
    }

    private static void StreamTest() {
        System.out.println("当前时间戳:" + System.currentTimeMillis());
        System.out.println("请输入字符，按回车键结束：");
        int c;
        try {
            InputStreamReader in = new InputStreamReader(System.in, "GBK");
            c = in.read();
            while (c != '\r') {
                System.out.println((char) c + "," + c);
                c = in.read();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            System.err.println();
        }
        System.out.println("=============系统属性===============");
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
    }

    private static void ObjTest(Object obj) {
        System.out.println("==================================");
        System.out.println("obj is instanceof Date=>" + (obj instanceof Date));
        System.out.println("obj is instanceof String=>" + (obj instanceof String));
        System.out.println("类名: " + obj.getClass().getName());
        System.out.println("父类: " + obj.getClass().getSuperclass().getName());
        System.out.println("实现的接口：");
        Class<?>[] interfaces = obj.getClass().getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println(interfaces[i]);
        }
    }

    private static void DateTest() {
        Date now = new Date();
        System.out.println("时间戳:" + now.getTime());
        System.out.println(now.toString());
        System.out.println(DateFormat.getDateInstance(DateFormat.FULL, Locale.CHINA).format(new Date()));

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(f.format(now));
    }

    private static void BitwiseOpt() {
        System.out.println(Integer.toBinaryString(60));
        int i = 60 >>> 2;
        System.out.println(i + "," + Integer.toBinaryString(i));

        System.out.println(Integer.toBinaryString(-10));
        i = -10 >>> 2;
        System.out.println(i + "," + Integer.toBinaryString(i));
    }

    private static void StringTest() {
        String s0 = "hello";
        String s1 = "hello";
        String s2 = "he" + "llo";
        System.out.println(s0 == s1);
        System.out.println(s0 == s2);
        System.out.println(s0.substring(2, 3));

        System.out.println("=============split===========");
        String colors = "Red,Black,White_Yellow,Blue,1,2,3";
        String[] arr1 = colors.split(",|_");
        System.out.println(String.join(" ", arr1));
        String[] arr2 = colors.split(",|_", 4);
        System.out.println(String.join(" ", arr2));

        System.out.println("=============equals===========");
        String s3 = "hello";
        System.out.println("Hello".equals(s3));
        System.out.println("Hello".equalsIgnoreCase(s3));
        System.out.println("Hello".compareTo(s3));
        System.out.println("Hello".compareToIgnoreCase(s3));

        System.out.println("=============indexOf===========");
        String s4 = "Hello Java";
        System.out.println(s4.indexOf('v'));
        System.out.println(s4.indexOf("ll"));
        System.out.println(s4.indexOf('w'));
        System.out.println(s4.lastIndexOf('l'));
        System.out.println(s4.charAt(1));

        System.out.println("=============regex===========");
        String s5 = "我想找一套适合自己的JAVA教程，尽快联系我13500006666 交朋友，电话号码是13611125565 出售二手电脑，联系方式15899903312";
        Matcher m = Pattern.compile("((13\\d)|(15\\d))\\d{8}").matcher(s5);
        while (m.find()) {
            System.out.println(m.group());
        }
    }

    private static void InputTest() {
        int sum = 0;
        int num = 0;
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= 6; i++) {
            System.out.println("请输入第" + i + "个月的销售数量");
            num = sc.nextInt();
            sum += num;
        }
        System.out.println("上半年的销售总量为：" + sum);
    }

    private static void ForeachTest() {
        int[] nums = { 1, 2, 3, 4, 5 };
        for (int item : nums) {
            System.out.print(item + " ");
        }
    }

    private static void NumberFormatTest() {
        DecimalFormat df1 = new DecimalFormat("0.0");
        DecimalFormat df2 = new DecimalFormat("#.#");
        DecimalFormat df3 = new DecimalFormat("000.000");
        DecimalFormat df4 = new DecimalFormat("###.###");
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入一个float类型的数字:");
        float f = scan.nextFloat();
        System.out.println("0.0 格式: " + df1.format(f));
        System.out.println("#.# 格式:" + df2.format(f));
        System.out.println("000.000 格式:" + df3.format(f));
        System.out.println("###.### 格式:" + df4.format(f));
    }
}