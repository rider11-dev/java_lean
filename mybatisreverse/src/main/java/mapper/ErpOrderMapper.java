package mapper;

import java.util.List;
import pojo.ErpOrder;

public interface ErpOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ErpOrder row);

    ErpOrder selectByPrimaryKey(Long id);

    List<ErpOrder> selectAll();

    int updateByPrimaryKey(ErpOrder row);
}