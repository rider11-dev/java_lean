package rider11.hellospringboot.component;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import rider11.hellospringboot.entity.ShiroAccount;
import rider11.hellospringboot.service.ShiroAccountService;

/**
 * shiro用户密码认证规则
 * 自定义过滤器
 */
public class ShiroUserPwdRealm extends AuthorizingRealm {

    private ShiroAccountService accountService;

    public ShiroUserPwdRealm(ShiroAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前登录对象
        Subject subject = SecurityUtils.getSubject();
        ShiroAccount account = (ShiroAccount) subject.getPrincipal();
        // 设置角色
        Set<String> roles = new HashSet<>();
        roles.add(account.getRole());
        SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo(roles);
        // 设置权限
        auth.addStringPermission(account.getPerms());
        return auth;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        ShiroAccount account = this.accountService.findByUsername(token.getUsername());
        if (account != null) {
            return new SimpleAuthenticationInfo(account, account.getPassword(), account.getUsername());
        }

        return null;
    }

}
