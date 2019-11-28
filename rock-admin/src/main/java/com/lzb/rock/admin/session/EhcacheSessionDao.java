package com.lzb.rock.admin.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lzb.rock.ehcache.mapper.EhCacheMapper;
import com.lzb.rock.login.util.UtilShiroSession;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lzb
 * 
 *         2019年4月16日 下午9:59:36
 */
@Component
@Slf4j
public class EhcacheSessionDao extends AbstractSessionDAO {
	// https://www.jianshu.com/p/5aa03c2d118e
	// 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
	// shiro 一个线程会多次调用doReadSession
	// 这里做个线程缓存，一个线程内若不进行修改session,doReadSession只会读取一次，其余从线程缓存中读取

	private final static String CACHE_NAME = "SHIRO";

	@Autowired
	EhCacheMapper ehCacheMapper;

	@Override
	public void update(Session session) throws UnknownSessionException {
//		log.debug("update==>sessionId:{}", session.getId());
		UtilShiroSession.remove();
		if (session != null && session.getId() != null) {
			String sessionId = UtilShiroSession.getKey(session.getId());
			ehCacheMapper.set(CACHE_NAME, sessionId, session);
		}

	}

	/**
	 * 删除会话；当会话过期/会话停止（如用户退出时）会调用
	 */
	@Override
	public void delete(Session session) {
//		log.debug("delete==>sessionId:{}", session.getId());
		UtilShiroSession.remove();
		if (session == null || session.getId() == null) {
			return;
		}
		String sessionId = UtilShiroSession.getKey(session.getId());
		ehCacheMapper.del(CACHE_NAME, sessionId);
	}

	/**
	 * 获取当前所有活跃用户，如果用户量多此方法影响性能
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Session> getActiveSessions() {
//		log.debug("getActiveSessions==>");
		List<Session> sessionList = ehCacheMapper.getKeys(CACHE_NAME);
//		Integer size = 0;
//		if (sessionList != null) {
//			size = sessionList.size();
//		}
//		log.debug("getActiveSessions==>size:{}", size);
		return sessionList;
	}

	/**
	 * 如DefaultSessionManager在创建完session后会调用该方法；如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；返回会话ID；主要此处返回的ID.equals(session.getId())；
	 */
	@Override
	protected Serializable doCreate(Session session) {
		UtilShiroSession.remove();
		Serializable sessionId = generateSessionId(session);
		assignSessionId(session, sessionId);

		ehCacheMapper.set(CACHE_NAME, UtilShiroSession.getKey(sessionId), session);

//		log.debug("doCreate==>sessionId:{}", sessionId);
		return sessionId;
	}

	/**
	 * 根据会话ID获取会话
	 */
	@Override
	protected Session doReadSession(Serializable sessionId) {
//		log.debug("doReadSession==>sessionId：{}", sessionId);
		// 读取当前线程缓存，防止多次调用
		Session session = UtilShiroSession.get();
		if (session != null) {
			return session;
		}
		if (sessionId == null) {
			return session;
		}

		String sysSessionId = UtilShiroSession.getKey(sessionId);
		session = ehCacheMapper.get(CACHE_NAME, sysSessionId);
		return session;
	}

}
