package rider11.hellospringboot.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import rider11.hellospringboot.entity.ShiroAccount;
import rider11.hellospringboot.mapper.ShiroAccountMapper;
import rider11.hellospringboot.service.ShiroAccountService;

@Service
public class ShiroAccountServiceImpl implements ShiroAccountService {

    private ShiroAccountMapper mapper;

    public ShiroAccountServiceImpl(ShiroAccountMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ShiroAccount findByUsername(String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        return this.mapper.selectOne(wrapper);
    }

}
