package org.kubeman.springdatajpa.config.jpa;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author yuhui
 * @since 4/13/21
 **/
@Getter
@Setter
public class BaseEntityVO {

    private String id;

    private String description;

    private Date createTime;

    private Date updateTime;
}
