package org.kubeman.springdatajpa.foobar.service;

import org.kubeman.springdatajpa.foobar.controller.dto.FooBarDTO;
import org.kubeman.springdatajpa.foobar.controller.vo.FooBarQueryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author yuhui
 * @since 4/13/21
 **/
public interface FooBarService {
    Page<FooBarQueryVO> getFooBarPageList(String query, Pageable pageable);

    FooBarQueryVO getFooBar(String id);

    void create(FooBarDTO dto);

    void update(FooBarDTO dto);

    void delete(String id);
}
