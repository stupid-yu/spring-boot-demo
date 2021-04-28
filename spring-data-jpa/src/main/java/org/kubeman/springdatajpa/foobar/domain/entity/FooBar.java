package org.kubeman.springdatajpa.foobar.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.kubeman.springdatajpa.config.jpa.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yuhui
 * @since 4/13/21
 **/
@Getter
@Setter
@Entity
@Table(name = "foo_bar")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class FooBar extends BaseEntity {

    private String name;

    private String foo;

    private String bar;

}
