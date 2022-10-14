@FunctionalInterface
interface Calculable {
    int calculateInt(int a, int b);
}

public class LambdaTest {
    private int value = 10;
    private static int staticValue = 5;

    public static Calculable add() {
        int localValue = 20;
        Calculable result = (int a, int b) -> {
            // localValue++;
            staticValue++;
            int c = a + b + staticValue + localValue;
            return c;
        };
        return result;
    }

    public Calculable sub() {
        final int localValue = 20;
        Calculable result = (int a, int b) -> {
            staticValue++;
            this.value++;
            int c = a - b - staticValue - this.value;
            // localValue = c;
            return c;
        };
        return result;
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }

    public static void main(String[] args) {
        int n1 = 10;
        int n2 = 5;
        display((a, b) -> a + b, n1, n2);
        display((a, b) -> a - b, n1, n2);
        display(add(), n1, n2);
        display(new LambdaTest().sub(), n1, n2);
        display(LambdaTest::add, n1, n2);
        display(LambdaTest::sub, n1, n2);
    }

    private static void display(Calculable calc, int n1, int n2) {
        System.out.println(calc.calculateInt(n1, n2));
    }
}
