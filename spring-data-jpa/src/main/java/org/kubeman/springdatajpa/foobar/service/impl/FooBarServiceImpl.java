package org.kubeman.springdatajpa.foobar.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.kubeman.springdatajpa.config.exception.JpaDemoException;
import org.kubeman.springdatajpa.foobar.controller.dto.FooBarDTO;
import org.kubeman.springdatajpa.foobar.controller.param.FooBarQueryParam;
import org.kubeman.springdatajpa.foobar.controller.vo.FooBarQueryVO;
import org.kubeman.springdatajpa.foobar.domain.entity.FooBar;
import org.kubeman.springdatajpa.foobar.domain.repository.FooBarRepository;
import org.kubeman.springdatajpa.foobar.service.FooBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;

/**
 * @author yuhui
 * @since 4/13/21
 **/
@Slf4j
@Service
public class FooBarServiceImpl implements FooBarService {

    @Autowired
    private FooBarRepository fooBarRepository;

    @Override
    public Page<FooBarQueryVO> getFooBarPageList(String query, Pageable pageable) {
        List<FooBarQueryVO> voList = Lists.newArrayList();
        Page<FooBar> page;
        if (Strings.isNotBlank(query)) {
            try {
                FooBarQueryParam param = new ObjectMapper().readValue(query, FooBarQueryParam.class);
                page = fooBarRepository.findAll((root, criteriaQuery, cb) -> {
                    List<Predicate> predicateList = Lists.newArrayList();
                    if (Strings.isNotBlank(param.getName())) {
                        predicateList.add(cb.like(root.get("name"), "%" + param.getName() + "%"));
                    }
                    if (Strings.isNotBlank(param.getFoo())) {
                        predicateList.add(cb.equal(root.get("foo"), param.getFoo()));
                    }
                    if (Strings.isNotBlank(param.getBar())) {
                        predicateList.add(cb.equal(root.get("bar"), param.getBar()));
                    }
                    Predicate[] predicateArr = new Predicate[predicateList.size()];
                    return cb.and(predicateList.toArray(predicateArr));
                }, pageable);
            } catch (JsonProcessingException e) {
                log.error("query param parse error!", e);
                throw new JpaDemoException("query param parse error!", HttpStatus.CONFLICT);
            }
        } else {
            page = fooBarRepository.findAll(pageable);
        }
        page.getContent().forEach(fooBar -> voList.add(FooBarQueryVO.convertPOToVO(fooBar)));
        return new PageImpl<>(voList, pageable, page.getTotalElements());
    }

    @Override
    public FooBarQueryVO getFooBar(String id) {
        return FooBarQueryVO.convertPOToVO(this.findById(id));
    }

    private FooBar findById(String id) {
        return fooBarRepository.findById(id).orElseThrow(
                () -> new JpaDemoException("The FooBar [" + id + "] not found!", HttpStatus.CONFLICT));
    }

    @Override
    public void create(FooBarDTO dto) {
        fooBarRepository.save(dto.convertToPO());
    }

    @Override
    public void update(FooBarDTO dto) {
        FooBar fooBar = this.findById(dto.getId());
        fooBar.setName(dto.getName());
        fooBar.setFoo(dto.getFoo());
        fooBar.setBar(dto.getBar());
        fooBarRepository.save(fooBar);
    }

    @Override
    public void delete(String id) {
        fooBarRepository.deleteById(id);
    }
}
