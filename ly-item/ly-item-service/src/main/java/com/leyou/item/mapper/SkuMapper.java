package com.leyou.item.mapper;

import com.leyou.item.pojo.Sku;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author dn
 * @create 2021-01-08 16:22
 */
public interface SkuMapper extends Mapper<Sku>, InsertListMapper<Sku> {
}
