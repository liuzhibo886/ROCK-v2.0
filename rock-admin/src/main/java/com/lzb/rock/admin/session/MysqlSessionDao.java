//package com.lzb.rock.admin.session;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import org.apache.shiro.session.Session;
//import org.apache.shiro.session.UnknownSessionException;
//import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.lzb.rock.base.Result;
//import com.lzb.rock.login.util.UtilShiroSession;
//import com.lzb.rock.system.open.client.SysSessionClient;
//import com.lzb.rock.system.open.dto.sysSession.SysSessionListReq;
//import com.lzb.rock.system.open.model.SysSession;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author lzb
// * 
// *         2019年4月16日 下午9:59:36
// */
////@Component
//@Slf4j
//public class MysqlSessionDao extends AbstractSessionDAO {
//	// https://www.jianshu.com/p/5aa03c2d118e
//	// 更新会话；如更新会话最后访问时间/停止会话/设置超时时间/设置移除属性等会调用
//	// shiro 一个线程会多次调用doReadSession
//	// 这里做个线程缓存，一个线程内若不进行修改session,doReadSession只会读取一次，其余从线程缓存中读取
//
//	@Autowired
//	SysSessionClient sessionClient;
//
//	@Override
//	public void update(Session session) throws UnknownSessionException {
//		UtilShiroSession.remove();
//		if (session == null && session.getId() == null) {
//			return;
//		}
//		String sessionId = UtilShiroSession.getKey(session.getId());
//
//		SysSession sysSession = new SysSession();
//		sysSession.setSessionText(UtilShiroSession.serialize(session));
//		sysSession.setSessionId(sessionId);
//		sessionClient.update(sysSession);
//	}
//
//	/**
//	 * 删除会话；当会话过期/会话停止（如用户退出时）会调用
//	 */
//	@Override
//	public void delete(Session session) {
//
//		UtilShiroSession.remove();
//		if (session == null || session.getId() == null) {
//			return;
//		}
//		String sessionId = UtilShiroSession.getKey(session.getId());
//		sessionClient.delete(sessionId);
//	}
//
//	// 获取当前所有活跃用户，如果用户量多此方法影响性能
//	@Override
//	public Collection<Session> getActiveSessions() {
//		SysSessionListReq sysSessionListReq = new SysSessionListReq();
//		Result<List<SysSession>> rs = sessionClient.records(sysSessionListReq, "9999", "0");
//		List<Session> sessionList = new ArrayList<>();
//		for (SysSession sysSession : rs.getData()) {
//			sessionList.add(UtilShiroSession.deserialize(sysSession.getSessionText()));
//		}
//		return sessionList;
//	}
//
//	/**
//	 * 如DefaultSessionManager在创建完session后会调用该方法；如保存到关系数据库/文件系统/NoSQL数据库；即可以实现会话的持久化；返回会话ID；主要此处返回的ID.equals(session.getId())；
//	 */
//	@Override
//	protected Serializable doCreate(Session session) {
//		UtilShiroSession.remove();
//		Serializable sessionId = generateSessionId(session);
//		assignSessionId(session, sessionId);
//		SysSession sysSession = new SysSession();
//
//		sysSession.setSessionText(UtilShiroSession.serialize(session));
//		sysSession.setSessionId(UtilShiroSession.getKey(sessionId));
//		sessionClient.add(sysSession);
//		return sessionId;
//	}
//
//	// 根据会话ID获取会话
//	@Override
//	protected Session doReadSession(Serializable sessionId) {
//		// 读取当前线程缓存，防止多次调用
//		Session session = UtilShiroSession.get();
//		if (session != null) {
//			return session;
//		}
//		if (sessionId == null) {
//			return session;
//		}
//
//		String sysSessionId = UtilShiroSession.getKey(sessionId);
//		// 远程调用失败，重试最多3次
//		for (int i = 0; i < 3; i++) {
//			Result<SysSession> sysSession = sessionClient.detail(sysSessionId);
//			if (sysSession.checkAndNotNull()) {
//				session = UtilShiroSession.deserialize(sysSession.getData().getSessionText());
//				UtilShiroSession.set(session);
//				break;
//			} else {
//				try {
//					Thread.sleep(200);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				log.error("doReadSession 远程调用失败,重试{}次", i);
//			}
//		}
//
//		return session;
//	}
//
//}
