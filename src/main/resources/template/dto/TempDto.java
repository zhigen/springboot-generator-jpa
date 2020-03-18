package com.zglu.generator.target.{packageName}.dto;

import com.alibaba.fastjson.JSON;
import com.zglu.generator.target.{packageName}.dao.{className};
import lombok.Data;

/**
 * @author {author}
 */
@Data
public class {className}Dto {
    private Long id;

    public {className} to{className}() {
        return JSON.parseObject(JSON.toJSONString(this), {className}.class);
    }
}
