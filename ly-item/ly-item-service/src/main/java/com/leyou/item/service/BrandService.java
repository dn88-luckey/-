package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.commom.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author dn
 * @create 2021-01-06 15:20
 */
@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;


    /**
     * 分页查询品牌
     *
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        //初始化example对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        //添加模糊查询
        if (StringUtils.isNoneBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        //添加分页
        PageHelper.startPage(page, rows);
        //添加排序
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + (desc ? " desc" : " asc"));
        }
        List<Brand> brands = this.brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());

    }

    /**
     * 新增品牌
     *
     * @param brand
     * @param cids
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveBrand(Brand brand, List<Long> cids) {
        this.brandMapper.insertSelective(brand);
        cids.forEach(cid -> {
            this.brandMapper.saveBrand(brand.getId(), cid);
        });
    }

    public List<Brand> queryBrandsByCid(Long cid) {
        return this.brandMapper.queryBrandsByCid(cid);

    }

    /**
     * 根据id删除品牌
     *
     * @param bid
     * @return
     */
    public ResponseEntity<Boolean> deleteByBrand(Long bid) {
        Brand brand = new Brand();
        brand.setId(bid);
        int rows = this.brandMapper.delete(brand);
        if (rows < 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(true);

    }


}
