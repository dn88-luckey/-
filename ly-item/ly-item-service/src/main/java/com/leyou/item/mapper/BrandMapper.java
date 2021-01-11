package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper;

import java.util.List;

/**
 * @author dn
 * @create 2021-01-06 15:19
 */
public interface BrandMapper extends Mapper<Brand> ,UpdateByExampleSelectiveMapper<Brand> {
    @Insert("insert into tb_category_brand (category_id,brand_id) values (#{bid},#{cid})")
    int saveBrand(@Param("bid") Long bid,@Param("cid") Long cid);
    @Select("select * from tb_brand a inner join tb_category_brand b on a.id= b.brand_id where b.category_id=#{cid}")
    List<Brand> queryBrandsByCid(Long cid);
    @Update("update tb_brand set name =#{name},letter=#{letter} where id =#{id}")
    int updateBrandByBid(Brand brand);
    @Update("update tb_category_brand set bid=#{bid} where cid =#{cid}")
    int updateByCid(@Param("cid") Long cid);
}
