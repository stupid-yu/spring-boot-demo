package org.kubeman.springdatajpa.foobar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.kubeman.springdatajpa.config.exception.JpaDemoException;
import org.kubeman.springdatajpa.foobar.controller.dto.FooBarDTO;
import org.kubeman.springdatajpa.foobar.controller.vo.FooBarQueryVO;
import org.kubeman.springdatajpa.foobar.service.FooBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yuhui
 * @since 4/13/21
 **/
@RestController
@RequestMapping("/foobars")
@Api(tags = {"FooBar API"})
public class FooBarController {

    @Autowired
    private FooBarService fooBarService;

    @GetMapping
    @ApiOperation(value = "获取 foobar 列表", response = FooBarQueryVO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "查询条件", dataType = "String", required = false, paramType = "query"),
            @ApiImplicitParam(name = "page", value = "第几页（！！第1页从0开始)", dataType = "Integer", required = true, paramType = "query"),
            @ApiImplicitParam(name = "size", value = "一页条数", dataType = "Integer", required = true, paramType = "query")})
    public Page<FooBarQueryVO> getFooBarPageList(@RequestParam(required = false) String query, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return fooBarService.getFooBarPageList(query, pageable);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据 id 获取 foobar", response = FooBarQueryVO.class)
    @ApiImplicitParam(name = "id", value = "foobar id", dataType = "String", required = true, paramType = "path")
    public FooBarQueryVO getFooBar(@PathVariable("id") String id) {
        return fooBarService.getFooBar(id);
    }

    @PostMapping
    @ApiOperation(value = "新增 foobar")
    @ApiImplicitParam(name = "dto", value = "FooBar dto对象", dataType = "FooBarDTO", required = true, paramType = "body")
    public Object create(@Valid @RequestBody FooBarDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(objectError -> {
                throw new JpaDemoException(objectError.getDefaultMessage(), HttpStatus.CONFLICT);
            });
        }
        fooBarService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping
    @ApiOperation(value = "修改 foobar")
    @ApiImplicitParam(name = "dto", value = "FooBar dto对象", dataType = "FooBarDTO", required = true, paramType = "body")
    public Object update(@Valid @RequestBody FooBarDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(objectError -> {
                throw new JpaDemoException(objectError.getDefaultMessage(), HttpStatus.CONFLICT);
            });
        }
        fooBarService.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "根据 id 删除 foobar")
    @ApiImplicitParam(name = "id", value = "foobar id", dataType = "String", required = true, paramType = "path")
    public Object delete(@PathVariable("id") String id) {
        fooBarService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
