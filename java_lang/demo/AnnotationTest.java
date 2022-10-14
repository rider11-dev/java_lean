import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface MyInherited {
}

@MyInherited
class TestA {
}

class TestB extends TestA {
}

class TestC extends TestB {
}

public class AnnotationTest {
    public static void main(String[] args) {
        Test1();
    }

    static void Test1() {
        System.out.println(TestA.class.getAnnotation(MyInherited.class));
        System.out.println(TestB.class.getAnnotation(MyInherited.class));
        System.out.println(TestC.class.getAnnotation(MyInherited.class));
    }
}
