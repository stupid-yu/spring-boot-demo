package org.kubeman.springdatajpa.foobar.controller.vo;

import lombok.Getter;
import lombok.Setter;
import org.kubeman.springdatajpa.config.jpa.BaseEntityVO;
import org.kubeman.springdatajpa.foobar.domain.entity.FooBar;
import org.springframework.beans.BeanUtils;

/**
 * <pre>
 *     FooBar 查询结果对象
 * </pre>
 *
 * @author yuhui
 * @since 4/13/21
 **/
@Getter
@Setter
public class FooBarQueryVO extends BaseEntityVO {

    private String name;

    private String foo;

    private String bar;

    public static FooBarQueryVO convertPOToVO(FooBar po) {
        FooBarQueryVO vo = new FooBarQueryVO();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }
}
