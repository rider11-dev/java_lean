package transaction.dao;

import java.math.BigDecimal;

import transaction.entity.Account;

public interface AccountDao {
     /**
     * 根据用户查询账户金额
     * @param userId
     * @return
     */
    Account selectByUserId(String userId);
    /**
     * 扣减账户金额
     * @param userId
     * @param money
     * @return
     */
    int decrease(String userId, BigDecimal money);
}
