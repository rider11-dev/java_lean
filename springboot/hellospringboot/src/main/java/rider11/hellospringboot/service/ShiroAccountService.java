package rider11.hellospringboot.service;

import rider11.hellospringboot.entity.ShiroAccount;

public interface ShiroAccountService {
    public ShiroAccount findByUsername(String username);
}
