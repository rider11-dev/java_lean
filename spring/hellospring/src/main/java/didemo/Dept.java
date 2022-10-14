package didemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Dept {
    private static final Log logger = LogFactory.getLog(Dept.class);
    private String deptNo;
    private String deptName;

    public void setDeptNo(String deptNo) {
        logger.info("正在执行Dept的setDeptNo方法...");
        this.deptNo = deptNo;
    }

    public void setDeptName(String deptName) {
        logger.info("正在执行Dept的setDeptName方法...");
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptNo='" + deptNo + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
