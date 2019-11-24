package com.lzb.rock.test.ms.service.impl;

import com.lzb.rock.test.open.model.Member;
import com.lzb.rock.test.ms.mapper.MemberMapper;
import com.lzb.rock.test.ms.service.IMemberService;
import org.springframework.stereotype.Service;
import com.lzb.rock.base.facade.impl.ServiceImpl;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzb123
 * @since 2019-11-12
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}
