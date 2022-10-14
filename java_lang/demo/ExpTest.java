import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ExpTest {
    public static void main(String[] args) {
        // System.out.println(Test1());
        // Test2();
        // Test3();
        Test4();
    }

    private static int Test1() {
        try {
            int a = 10;
            int b = 0;
            int c = a / b;
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println();
            e.printStackTrace();
            System.out.println();
            System.out.println(e.toString());
        } finally {
            return 2;
        }
    }

    private static void Test2() {
        FileInputStream readFile = null;
        InputStreamReader ir = null;
        BufferedReader in = null;

        try {
            readFile = new FileInputStream("readme.txt");
            ir = new InputStreamReader(readFile);
            in = new BufferedReader(ir);
            String str = in.readLine();
            if (str == null) {
                return;
            }
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = df.parse(str);
            System.out.println(df.format(dt));
        } catch (FileNotFoundException e) {
            System.out.println("处理FileNotFoundException...");
            e.printStackTrace();
        } catch (IOException | ParseException e) {
            System.out.println("处理" + e.getClass().getName() + "...");
            e.printStackTrace();
        } finally {
            if (readFile != null) {
                try {
                    readFile.close();
                    System.out.println("关闭打开的文件");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void Test3() {
        try (
                BufferedReader br = new BufferedReader(new FileReader("readme.txt"));
                PrintStream ps = new PrintStream(new FileOutputStream("out.txt"));) {
            String line = br.readLine();
            System.out.println(line);
            ps.println(line);
        } catch (FileNotFoundException e) {
            System.out.println("处理FileNotFoundException...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("处理IOException...");
            e.printStackTrace();
        }
    }

    private static void Test4() {
        Test4_1();
    }

    private static void Test4_1() {
        Test4_2();
    }

    private static void Test4_2() {
        Test4_3();
    }

    private static void Test4_3() {
        int a = 1 / 0;
    }
}
