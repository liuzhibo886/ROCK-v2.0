package com.lzb.rock.admin.factroy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.lzb.rock.base.facade.IShiro;
import com.lzb.rock.base.model.LogUser;
import com.lzb.rock.base.model.ShiroAuthz;
import com.lzb.rock.base.model.ShiroMenu;
import com.lzb.rock.base.model.ShiroRole;
import com.lzb.rock.base.model.ShiroUser;
import com.lzb.rock.base.util.UtilJson;
import com.lzb.rock.base.util.UtilObject;
import com.lzb.rock.login.shiro.ShiroKit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lzb
 * 
 *         2019年3月31日 下午8:59:12
 */
@Slf4j
@Component
public class ShiroFactroy implements IShiro {



	/**
	 * 获取shiro的认证信息 根据账号查询用户信息
	 */
	@Override
	public ShiroUser getShiroAuthByUserAccount(String userAccount) {
		ShiroUser shiroUser=new ShiroUser();
		shiroUser.setUserId(1L);
		shiroUser.setUserAccount("admin");
		shiroUser.setUserName("张三");
		shiroUser.setUserPassword("ecfadcde9305f8891bcfe5a1e28c253e");
		shiroUser.setUserSalt("8pgby");
		shiroUser.setRoles(new ArrayList<ShiroRole>());
		shiroUser.setMenus(new ArrayList<ShiroMenu>());
		shiroUser.setAuthzs(new HashSet<ShiroAuthz>());
		return shiroUser;
	}

	@Override
	public Set<ShiroAuthz> getShiroAuthzByShiroRoles(List<ShiroRole> roles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShiroRole> getShiroRolesByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LogUser getUser() {
//		LogUser user=new LogUser();
//		user.setUserId(1L);
//		user.setUserAccount(userAccount);
//		user.setUserName("张三");
		ShiroUser shiroUser = ShiroKit.getUser();
		LogUser user =UtilObject.javaBean(shiroUser, LogUser.class);
		return user;
	}

	

}
