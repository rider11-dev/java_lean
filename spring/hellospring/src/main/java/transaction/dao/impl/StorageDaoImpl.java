package transaction.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javafx.animation.StrokeTransitionBuilder;
import transaction.dao.StorageDao;
import transaction.entity.Storage;

@Repository
public class StorageDaoImpl implements StorageDao {
    private JdbcTemplate jdbc;

    public StorageDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Storage selectByProductId(String productId) {
        String sql = "select *   from storage where product_id = ?";
        return this.jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(Storage.class), productId);
    }

    @Override
    public int decrease(Storage record) {
        String sql = "update storage set used=?,residue=? where product_id=?";
        return jdbc.update(sql, record.getUsed(), record.getResidue(), record.getProductId());
    }

}
