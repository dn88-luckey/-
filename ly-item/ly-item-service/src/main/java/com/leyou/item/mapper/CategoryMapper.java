package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.BaseMapper;

/**
 * @author dn
 * @create 2021-01-06 10:02
 */
public interface CategoryMapper extends BaseMapper<Category>, SelectByIdListMapper<Category,Long> {
}
