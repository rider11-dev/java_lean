package didemo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Employee {
    private static final Log logger = LogFactory.getLog(Employee.class);
    // 员工编号
    private String empNo;
    // 员工姓名
    private String empName;
    // 地址
    private List<String> addressList;
    private Set<String> addressSet;
    private Map<Integer, String> addressMap;

    public void setAddressSet(Set<String> addressSet) {
        this.addressSet = addressSet;
    }

    public void setAddressMap(Map<Integer, String> addressMap) {
        this.addressMap = addressMap;
    }

    public void setAddressList(List<String> addressList) {
        this.addressList = addressList;
    }

    // 部门信息
    private Dept dept;

    public Dept getDept() {
        return this.dept;
    }

    public void setEmpNo(String empNo) {
        logger.info("正在执行Employee的setEmpNo方法...");
        this.empNo = empNo;
    }

    public void setEmpName(String empName) {
        logger.info("正在执行Employee的setEmpName方法...");
        this.empName = empName;
    }

    public void setDept(Dept dept) {
        logger.info("正在执行Employee的setDept方法...");
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empNo='" + empNo + '\'' +
                ", empName='" + empName + '\'' +
                ", addressList=" + addressList +
                ", addressSet=" + addressSet +
                ", addressMap=" + addressMap +
                ", dept=" + dept +
                '}';
    }
}
