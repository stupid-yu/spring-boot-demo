package org.kubeman.springdatajpa.foobar.domain.repository;

import org.kubeman.springdatajpa.foobar.domain.entity.FooBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author yuhui
 * @since 4/13/21
 **/
public interface FooBarRepository extends JpaRepository<FooBar, String>, JpaSpecificationExecutor<FooBar> {
}
