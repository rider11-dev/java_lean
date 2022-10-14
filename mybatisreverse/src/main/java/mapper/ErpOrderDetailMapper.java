package mapper;

import java.util.List;
import pojo.ErpOrderDetail;

public interface ErpOrderDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ErpOrderDetail row);

    ErpOrderDetail selectByPrimaryKey(Long id);

    List<ErpOrderDetail> selectAll();

    int updateByPrimaryKey(ErpOrderDetail row);
}