package org.kubeman.springdatajpa.foobar.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.kubeman.springdatajpa.config.jpa.BaseEntity;
import org.kubeman.springdatajpa.foobar.domain.entity.FooBar;
import org.springframework.beans.BeanUtils;

/**
 * <pre>
 *     FooBar 前端入参
 * </pre>
 *
 * @author yuhui
 * @since 4/13/21
 **/
@Getter
@Setter
public class FooBarDTO extends BaseEntity {

    private String name;

    private String foo;

    private String bar;

    public FooBar convertToPO() {
        FooBar po = new FooBar();
        BeanUtils.copyProperties(this, po);
        return po;
    }
}
