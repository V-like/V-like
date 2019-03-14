package com.jmyp.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by on 2018/12/24.
 */
@Table(name = "tb_specification")
public class Specification implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String spec_name;
    private Integer category_id;


    //一个规格，对应多个规格选项
    private List<SpecificationOption> optionList;

    public List<SpecificationOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<SpecificationOption> optionList) {
        this.optionList = optionList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpec_name() {
        return spec_name;
    }

    public void setSpec_name(String spec_name) {
        this.spec_name = spec_name;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }
}
