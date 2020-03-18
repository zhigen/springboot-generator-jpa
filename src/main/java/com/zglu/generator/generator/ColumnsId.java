package com.zglu.generator.generator;

import lombok.Data;

import java.io.Serializable;

/**
 * 联合主键对象
 *
 * @author zglu
 */
@Data
public class ColumnsId implements Serializable {
    private String tableSchema;
    private String tableName;
    private String columnName;
}
