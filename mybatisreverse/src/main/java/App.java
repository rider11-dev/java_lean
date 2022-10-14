

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // System.out.println(new File("/").getAbsolutePath());
        // System.out.println(new File(".").getAbsolutePath());
        try {
            App generatorSqlmap = new App();
            generatorSqlmap.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generator() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        // 指定配置文件
        File configFile = new File("./generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        org.mybatis.generator.config.Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
