package rider11.hellospringboot.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.extern.slf4j.Slf4j;
import rider11.hellospringboot.entity.ErpOrder;
import rider11.hellospringboot.entity.ErpOrderDetail;
import rider11.hellospringboot.entity.MpUser;
import rider11.hellospringboot.entity.dto.ErpOrderDto;
import rider11.hellospringboot.mapper.ErpOrderDetailMapper;
import rider11.hellospringboot.mapper.ErpOrderMapper;
import rider11.hellospringboot.mapper.MpUserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping(value = "mp")
public class MpController {
    private MpUserMapper userMapper;
    private ErpOrderMapper orderMapper;
    private ErpOrderDetailMapper orderDetailMapper;

    public MpController(MpUserMapper userMapper,
            ErpOrderMapper orderMapper,
            ErpOrderDetailMapper orderDetailMapper) {
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
    }

    @GetMapping("/users")
    public List<MpUser> getUsers() {
        return this.userMapper.selectList(null);
    }

    @PostMapping(value = "/adduser")
    public MpUser postMethodName(@RequestBody MpUser entity) {
        this.userMapper.insert(entity);
        return entity;
    }

    @GetMapping("orders")
    public List<ErpOrderDto> findOrders() {
        // 查询订单主表
        List<ErpOrder> orders = this.orderMapper.selectList(Wrappers.emptyWrapper());
        List<ErpOrderDto> dtos = null;
        if (orders.size() > 0) {
            dtos = orders.stream().map(ErpOrderDto::build).collect(Collectors.toList());
            findOrderDetails(dtos);
        }
        return dtos;
    }

    @GetMapping("orders_page")
    public IPage<ErpOrderDto> findOrdersPage(Page page) {
        IPage<ErpOrder> orderPage = this.orderMapper.selectPage(page, Wrappers.emptyWrapper());
        IPage<ErpOrderDto> dtoPage = orderPage.convert(ErpOrderDto::build);
        if (dtoPage.getRecords().size() > 0) {
            findOrderDetails(dtoPage.getRecords());
        }
        return dtoPage;
    }

    private void findOrderDetails(List<ErpOrderDto> orders) {
        List<Long> ids = orders.stream().map(ErpOrderDto::getId).collect(Collectors.toList());
        // 批量查询商品明细
        LambdaQueryWrapper<ErpOrderDetail> wrapper = Wrappers.lambdaQuery(ErpOrderDetail.class)
                .in(ErpOrderDetail::getOrder_id, ids);
        List<ErpOrderDetail> details = this.orderDetailMapper.selectList(wrapper);
        // 将商品明细按order_id分组
        Map<Long, List<ErpOrderDetail>> map = details.stream()
                .collect(Collectors.groupingBy(ErpOrderDetail::getOrder_id));
        // 设置商品明细
        orders.forEach(o -> o.setDetails(map.get(o.getId())));
    }
}
