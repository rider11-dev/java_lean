package rider11.hellospringboot.controller;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/jdbc",method = RequestMethod.GET)
@Slf4j
public class JdbcController {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public JdbcController(DataSource ds, JdbcTemplate jt) {
        this.dataSource = ds;
        this.jdbcTemplate = jt;
    }

    @RequestMapping("/test")
    @ResponseBody
    public Map test() throws SQLException {
        Map<String, Object> result = new HashMap<>();
        result.put("data_source", this.dataSource.getClass());
        DatabaseMetaData meta = this.dataSource.getConnection().getMetaData();
        result.put("connection", meta.getURL() + "," + meta.getUserName() + "," + meta.getDatabaseProductName() + ","
                + meta.getDatabaseProductVersion());
        Integer i = this.jdbcTemplate.queryForObject("select count(1) from `user`", Integer.class);
        result.put("users", i);

        return result;
    }
}
