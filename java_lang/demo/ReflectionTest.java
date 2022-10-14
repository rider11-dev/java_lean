import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class ReflectionTest {
    public static void main(String[] args) {
        // Test1();
        Test2();
    }

    static void Test1() {
        Class<String> cls1 = String.class;
        String str = "Hello";
        Class<String> cls2 = (Class<String>) str.getClass();
        Class<Integer> cls3 = int.class;
        Class<Integer> cls4 = Integer.class;
        System.out.println("cls2类名称:" + cls2.getName());
        System.out.println("cls2是否为接口:" + cls2.isInterface());
        System.out.println("cls2是否为数组对象:" + cls2.isArray());
        System.out.println("cls2父类名称:" + cls2.getSuperclass().getName());
        System.out.println("cls2是否为基本类型:" + cls2.isPrimitive());
        System.out.println("cls3是否为基本类型:" + cls3.isPrimitive());
        System.out.println("cls4是否为基本类型:" + cls4.isPrimitive());

    }

    static void Test2() {
        try {
            Class c = Class.forName("java.lang.String");
            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                // 打印权限修饰符：public、protected、private
                System.out.print(Modifier.toString(m.getModifiers()));
                System.out.print(" " + m.getReturnType().getName() + " ");
                System.out.print(m.getName() + "(");
                Parameter[] params = m.getParameters();
                for (int idx = 0; idx < params.length; idx++) {
                    Parameter p = params[idx];
                    System.out.print(p.getType().getName() + " " + p.getName());
                    if (idx < params.length - 1) {
                        System.out.print(",");
                    }
                }
                Class<?>[] exceptions = m.getExceptionTypes();

                System.out.print(")");
                if (exceptions.length > 0) {
                    System.out.print(") throws ");
                } else {
                    System.out.println();
                }
                for (int idx = 0; idx < exceptions.length; idx++) {
                    Class exp = exceptions[idx];
                    System.out.print(exp.getName());
                    if (idx < exceptions.length - 1) {
                        System.out.print(",");
                    } else {
                        System.out.println();
                    }
                }

            }
        } catch (ClassNotFoundException e) {
            System.out.println("找不到指定的类");
        }
    }
}
