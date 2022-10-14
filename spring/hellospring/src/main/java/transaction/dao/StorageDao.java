package transaction.dao;

import transaction.entity.Storage;

public interface StorageDao {
     /**
     * 查询商品的库存
     * @param productId
     * @return
     */
    Storage selectByProductId(String productId);
    /**
     * 扣减商品库存
     * @param record
     * @return
     */
    int decrease(Storage record);
}
