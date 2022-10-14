import java.util.EnumMap;

class DBInfo {
    enum DBType {
        MySQL, Oracle, DB2, SqlServer
    }

    EnumMap<DBType, String> urls = new EnumMap<>(DBType.class);

    public DBInfo() {
        urls.put(DBType.DB2, "jdbc:db2://localhost:5000/sample");
        urls.put(DBType.MySQL, "jdbc:mysql://localhost/mydb");
        urls.put(DBType.Oracle, "jdbc:oracle:thin:@localhost:1521:sample");
        urls.put(DBType.SqlServer, "jdbc:microsoft:sqlserver://sql:1433;Database=mydb");
        System.out.println("DBInfo初始化");
    }

    public String getUrl(DBType type) {
        return urls.get(type);
    }
}

public class EnumTest {
    enum Signal {
        GREEN, YELLOW, RED
    }

    enum WeekDay {
        Mon("Monday"), Tue("Tuesday"), Wed("Wednesday"), Thu("Thursday"), Fri("Friday"), Sat("Saturday"), Sun("Sunday");

        private final String day;

        private WeekDay(String day) {
            this.day = day;
        }

        public static void printDay(int i) {
            switch (i) {
                case 1:
                    System.out.println(WeekDay.Mon);
                    break;
                case 2:
                    System.out.println(WeekDay.Tue);
                    break;
                case 3:
                    System.out.println(WeekDay.Wed);
                    break;
                case 4:
                    System.out.println(WeekDay.Thu);
                    break;
                case 5:
                    System.out.println(WeekDay.Fri);
                    break;
                case 6:
                    System.out.println(WeekDay.Sat);
                    break;
                case 7:
                    System.out.println(WeekDay.Sun);
                    break;
                default:
                    System.out.println("wrong number!");
            }
        }

        public String getDay() {
            return day;
        }

        @Override
        public String toString() {
            return this.day;
        }
    }

    public static void main(String[] args) {
        Test1();
        Test2();
        Test3();
    }

    static void Test1() {
        for (int idx = 0; idx < Signal.values().length; idx++) {
            System.out.println("枚举成员:" + Signal.values()[idx]);
        }
        System.out.println(Signal.valueOf("GREEN").toString());// 区分大小写
    }

    static void Test2() {
        for (WeekDay day : WeekDay.values()) {
            System.out.println(day + " => " + day.getDay());
        }
    }

    static void Test3() {
        System.out.println(new DBInfo().getUrl(DBInfo.DBType.MySQL));
    }
}
