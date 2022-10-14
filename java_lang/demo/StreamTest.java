import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class StreamTest {
    public static void main(String[] args) {
        // Test1();
        // Test2();
        // Test3();
        // Test4();
        // Test5();
        Test6();
    }

    static void Test1() {
        System.out.println("系统默认编码：" + System.getProperty("file.encoding"));
    }

    static void Test2() {
        String path = "c:/windows/";
        File f = new File(path, "notepad.exe");
        System.out.println(path + "/notepad.exe文件信息如下：");
        System.out.println("=========================================");
        System.out.println("文件长度：" + f.length() + "字节");
        System.out.println("是否文件：" + f.isFile());
        System.out.println("是否目录：" + f.isDirectory());
        System.out.println("是否可读：" + f.canRead());
        System.out.println("是否可写：" + f.canWrite());
        System.out.println("是否隐藏：" + f.isHidden());
        System.out.println("最后修改时间：" + new Date(f.lastModified()));
        System.out.println("文件名称：" + f.getName());
        System.out.println("文件路径：" + f.getPath());
        System.out.println("文件绝对路径：" + f.getAbsolutePath());
    }

    static void Test3() {
        byte[] data = new byte[] { 1, -1, 25, -22, -5, 23 };
        ByteArrayInputStream bais = new ByteArrayInputStream(data, 0, 6);
        int i = bais.read();
        while (i != -1) {
            System.out.println("原值=" + (byte) i + "\t\t转换为int类型=" + i);
            i = bais.read();
        }
    }

    static void Test4() {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        byte[] bytes = new byte[] { 1, -1, 25, -22, -5, 23 };
        data.write(bytes, 0, 4);
        System.out.println("数组输出流中的字节数：" + data.size());
        byte[] newBytes = data.toByteArray();
        System.out.println(Arrays.toString(newBytes));

    }

    static String _srcFile = "D:/learn/java_learn/java_lang/demo/StreamTest.java";
    static String _targetFile = "D:/learn/java_learn/java_lang/demo/StreamTest.java.txt";

    static void Test5() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File srcFile = new File(_srcFile);
        File targetFile = new File(_targetFile);
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(targetFile);
            byte[] bytes = new byte[1024];
            int n = 0;
            // System.out.println("文件[" + file + "]内容如下：");
            while ((n = fis.read(bytes)) != -1) {
                // String s = new String(bytes, 0, n);
                // System.out.println(s);
                fos.write(bytes, 0, n);
            }
            System.out.println("写入结束");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void Test6() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(_srcFile);
            fw = new FileWriter(_targetFile);
            int i = 0;
            // System.out.println(_srcFile + "文件内容如下：");
            while ((i = fr.read()) != -1) {
                // System.out.print((char) i);
                fw.write(i);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                fr.close();
                fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
