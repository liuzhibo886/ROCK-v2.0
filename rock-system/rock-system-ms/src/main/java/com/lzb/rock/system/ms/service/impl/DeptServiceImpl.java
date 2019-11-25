package com.lzb.rock.system.ms.service.impl;

import com.lzb.rock.system.open.model.Dept;
import com.lzb.rock.system.ms.mapper.DeptMapper;
import com.lzb.rock.system.ms.service.IDeptService;
import org.springframework.stereotype.Service;
import com.lzb.rock.base.facade.impl.ServiceImpl;
/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author lzb123
 * @since 2019-10-26
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
