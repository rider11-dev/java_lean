public class VarTest {
    public static void main(String[] args) {
        DataClass dc = new DataClass();
        System.out.println(dc.name);
        System.out.println(dc.age);

        System.out.println(dc.WebSite);
        System.out.println(dc.URL);

        System.out.println(DataClass.WebSite);
        System.out.println(DataClass.URL);
    }
}
