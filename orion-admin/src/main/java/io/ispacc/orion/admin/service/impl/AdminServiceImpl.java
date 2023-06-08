package io.ispacc.orion.admin.service.impl;

import io.ispacc.orion.admin.dto.UserParam;
import io.ispacc.orion.admin.entity.User;
import io.ispacc.orion.admin.mapper.UserMapper;
import io.ispacc.orion.admin.service.AdminService;
import io.ispacc.orion.admin.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public AdminServiceImpl(JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
//        try {
//            UserDetails userDetails = loadUserByUsername(username);
//            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//                Asserts.fail("密码不正确");
//            }
//            if (!userDetails.isEnabled()) {
//                Asserts.fail("帐号已被禁用");
//            }
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            token = jwtTokenUtil.generateToken(userDetails);
////            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
//        } catch (AuthenticationException e) {
//            log.warn("登录异常:{}", e.getMessage());
//        }
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
//        User admin = getAdminByUsername(username);
//        if (admin != null) {
//            List<UmsResource> resourceList = getResourceList(admin.getId());
//            return new AdminUserDetails(admin, resourceList);
//        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public User register(UserParam userParam) {
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        user.setStatus(1);
        //查询是否有相同用户名的用户
//        UmsAdminExample example = new UmsAdminExample();
//        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
//        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
//        if (umsAdminList.size() > 0) {
//            return null;
//        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.insert(user);
        return null;
    }
}
