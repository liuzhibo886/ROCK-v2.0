package com.lzb.rock.test.ms.myThread;

import com.lzb.rock.base.holder.SpringContextHolder;
import com.lzb.rock.test.ms.service.IMemberService;
import com.lzb.rock.test.ms.util.UtilTest;
import com.lzb.rock.test.open.model.Member;

/**
 * 生成用户线程
 *
 * @author lzb
 *
 * @date 2019年11月14日 下午4:14:41
 */
public class MemberThread implements Runnable {




	@Override
	public void run() {
		IMemberService memberService = SpringContextHolder.getBean(IMemberService.class);
		for (int i = 0; i < 1000000000; i++) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Member member = new Member();
			member.setMemberName(UtilTest.getName(UtilTest.getRandom(10L, 60L)));
			memberService.insert(member);
		}
	}

}
