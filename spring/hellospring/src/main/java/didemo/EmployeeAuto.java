package didemo;

public class EmployeeAuto {
    // 员工编号
    private String empNo;
    // 员工姓名
    private String empName;
    // 部门信息
    private DeptAuto dept;

    public EmployeeAuto() {
        System.out.println("正在执行 EmployeeAuto 的无参构造方法>>>>");
    }

    public EmployeeAuto(String empNo, String empName, DeptAuto dept) {
        System.out.println("正在执行 EmployeeAuto 的有参构造方法>>>>");
        this.empNo = empNo;
        this.empName = empName;
        this.dept = dept;
    }

    public void setEmpNo(String empNo) {
        System.out.println("正在执行 EmployeeAuto 的 setEmpNo 方法>>>>");
        this.empNo = empNo;
    }

    public void setEmpName(String empName) {
        System.out.println("正在执行 EmployeeAuto 的 setEmpName 方法>>>>");
        this.empName = empName;
    }

    public void setDept(DeptAuto dept) {
        System.out.println("正在执行 EmployeeAuto 的 setDept 方法>>>>");
        this.dept = dept;
    }

    public DeptAuto getDept() {
        return dept;
    }

    public String getEmpNo() {
        return empNo;
    }

    public String getEmpName() {
        return empName;
    }

    @Override
    public String toString() {
        return "EmployeeAuto{" +
                "empNo='" + empNo + '\'' +
                ", empName='" + empName + '\'' +
                ", dept=" + dept +
                '}';
    }
}
