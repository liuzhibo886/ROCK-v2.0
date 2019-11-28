/**
 * @author lzb
 *
 * 
 *         2019年4月16日 下午10:23:37
 */
package com.lzb.rock.admin.session;

/**
 * @author lzb
 * 
 *         2019年4月16日 下午10:23:37
 */
//public class RedisSessionDao extends AbstractSessionDAO {
//
//    @Resource
//    RedisUtil redisUtil;
//
//    private final String SHIRO_SESSIOM_PREFIX = "shiro-session";
//
//    private byte[] getKey(String key){
//        return (SHIRO_SESSIOM_PREFIX+key).getBytes();
//    }
//
//    @Override
//    protected Serializable doCreate(Session session) {
//        Serializable sessionId = generateSessionId(session);
//        assignSessionId(session,sessionId);
//        saveSession(session);
//        return sessionId;
//    }
//
//    @Override
//    protected Session doReadSession(Serializable sessionId) {
//        if (sessionId == null) {
//            return null;
//        }
//        byte[] key = getKey(sessionId.toString());
//        byte[] value = redisUtil.get(key);
//        return (Session) SerializationUtils.deserialize(value);
//    }
//
//
//    @Override
//    public void update(Session session) throws UnknownSessionException {
//        saveSession(session);
//
//    }
//
//    private void saveSession(Session session){
//        if(session !=null&& session.getId()!=null) {
//            byte[] key = getKey(session.getId().toString());
//            byte[] value = SerializationUtils.serialize(session);
//            redisUtil.set(key, value);
//            redisUtil.expire(key, 600);
//        }
//    }
//
//    @Override
//    public void delete(Session session) {
//        if(session == null || session.getId() ==null){
//            return;
//        }
//        byte[] key = getKey(session.getId().toString());
//        redisUtil.del(key);
//    }
//
//    @Override
//    public Collection<Session> getActiveSessions() {
//        Set<byte[]> keys = redisUtil.getKeys(SHIRO_SESSIOM_PREFIX);
//        Set<Session> sessions = new HashSet<>();
//        if(CollectionUtils.isEmpty(keys)){
//            return sessions;
//        }
//        for(byte[] key:keys){
//            Session session = (Session)SerializationUtils.deserialize(redisUtil.get(key));
//            sessions.add(session);
//        }
//        return sessions;
//    }
//}