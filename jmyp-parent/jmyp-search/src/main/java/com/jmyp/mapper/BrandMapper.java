package com.jmyp.mapper;

import com.jmyp.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by on 2018/12/24.
 */
@org.apache.ibatis.annotations.Mapper
public interface BrandMapper extends Mapper<Brand> {

    /**
     * 需求：根据分类id查询品牌数据
     */
    @Select("SELECT * FROM tb_brand WHERE id IN (SELECT brand_id FROM tb_category_brand WHERE category_id = #{categoryId})")
    public List<Brand> findBrandListByCategoryId(Long categoryId);

}
