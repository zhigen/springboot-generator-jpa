package com.zglu.generator.target.{packageName}.controller;

import com.zglu.generator.target.{packageName}.dao.{className};
import com.zglu.generator.target.{packageName}.dto.{className}Dto;
import com.zglu.generator.target.{packageName}.service.{className}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zglu
 */
@Api("{tableComment}")
@RestController
@AllArgsConstructor
public class {className}Controller {
    private final {className}Service {valName}Service;

    @PostMapping("/{tableNameMid}")
    @ApiOperation("增")
    public {className} add(@RequestBody {className}Dto {valName}Dto) {
        return {valName}Service.add({valName}Dto.to{className}());
    }

    @GetMapping("/{tableNameMid}/{id}")
    @ApiOperation("查")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "id", required = true),
    })
    public {className} get(@PathVariable long id) {
        return {valName}Service.get(id);
    }

    @GetMapping("/{tableNameMid}")
    @ApiOperation("查")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "q", value = "搜索，语法与SQL一致，除属性驼峰外全小写", defaultValue = "id >= 2 and id<=10"),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "order", value = "排序，语法与SQL一致，除属性驼峰外全小写", defaultValue = "name asc, id desc"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "offset", value = "偏移量", defaultValue = "0"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "limit", value = "行数", defaultValue = "10"),
    })
    public List<{className}> list(String q, String order, Integer offset, Integer limit) {
        return {valName}Service.list(q, order, offset, limit);
    }

    @GetMapping("/{tableNameMid}/page")
    @ApiOperation("查分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "q", value = "搜索，语法与SQL一致，除属性驼峰外全小写", defaultValue = "id >= 2 and id<=10"),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "order", value = "排序，语法与SQL一致，除属性驼峰外全小写", defaultValue = "name asc, id desc"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "number", value = "页号", defaultValue = "0"),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "size", value = "行数", defaultValue = "10"),
    })
    public Page<{className}> page(String q, String order, Integer number, Integer size) {
        return {valName}Service.page(q, order, number, size);
    }

    @PutMapping("/{tableNameMid}")
    @ApiOperation("覆盖写入")
    public {className} put(@RequestBody {className}Dto {valName}Dto) {
        return {valName}Service.put({valName}Dto.to{className}());
    }

    @PatchMapping("/{tableNameMid}")
    @ApiOperation("改，忽略空属性")
    public {className} set(@RequestBody {className}Dto {valName}Dto) {
        return {valName}Service.set({valName}Dto.to{className}());
    }

    @DeleteMapping("/{tableNameMid}/{id}")
    @ApiOperation("删")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "long", name = "id", value = "id", required = true),
    })
    public void remove(@PathVariable long id) {
        {valName}Service.remove(id);
    }

}
