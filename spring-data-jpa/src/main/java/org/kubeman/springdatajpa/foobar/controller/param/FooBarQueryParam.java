package org.kubeman.springdatajpa.foobar.controller.param;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 *     FooBar 查询参数对象
 * </pre>
 *
 * @author yuhui
 * @since 4/13/21
 **/
@Getter
@Setter
public class FooBarQueryParam {

    private String name;

    private String foo;

    private String bar;

}
