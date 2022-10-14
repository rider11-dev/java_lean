package transaction.dao.impl;

import java.math.BigDecimal;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import transaction.dao.AccountDao;
import transaction.entity.Account;

@Repository
public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbc;

    public AccountDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Account selectByUserId(String userId) {
        String sql = "  select * from account where user_id = ?";
        return this.jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class), userId);
    }

    @Override
    public int decrease(String userId, BigDecimal money) {
        String sql = "update account set residue=residue-?,used=used+? where user_id=?;";
        return this.jdbc.update(sql, money, money, userId);
    }

}
