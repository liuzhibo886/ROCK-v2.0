package com.lzb.rock.system.ms.service.impl;

import com.lzb.rock.system.open.model.User;
import com.lzb.rock.system.ms.mapper.UserMapper;
import com.lzb.rock.system.ms.service.IUserService;
import org.springframework.stereotype.Service;
import com.lzb.rock.base.facade.impl.ServiceImpl;
/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author lzb123
 * @since 2019-09-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
