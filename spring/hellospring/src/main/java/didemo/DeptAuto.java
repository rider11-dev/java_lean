package didemo;

public class DeptAuto {
    // 部门编号
    private String deptNo;
    // 部门名称
    private String deptName;

    public DeptAuto() {
        System.out.println("正在执行 DeptAuto 的无参构造方法>>>>");
    }

    public DeptAuto(String deptNo, String deptName) {
        System.out.println("正在执行 DeptAuto 的有参构造方法>>>>");
        this.deptNo = deptNo;
        this.deptName = deptName;
    }

    public void setDeptNo(String deptNo) {
        System.out.println("正在执行 DeptAuto 的 setDeptNo 方法>>>>");
        this.deptNo = deptNo;
    }

    public void setDeptName(String deptName) {
        System.out.println("正在执行 DeptAuto 的 setDeptName 方法>>>>");
        this.deptName = deptName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    @Override
    public String toString() {
        return "DeptAuto{" +
                "deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }

}
