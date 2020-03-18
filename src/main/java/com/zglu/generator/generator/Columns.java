package com.zglu.generator.generator;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zglu
 */
@Data
@Entity
@Table(name = "COLUMNS")
@IdClass(ColumnsId.class)
public class Columns {
    @Id
    @Column(name = "TABLE_SCHEMA")
    private String tableSchema;
    @Id
    @Column(name = "TABLE_NAME")
    private String tableName;
    @Id
    @Column(name = "COLUMN_NAME")
    private String columnName;
    @Column(name = "ORDINAL_POSITION")
    private int ordinalPosition;
    @Column(name = "COLUMN_DEFAULT")
    private String columnDefault;
    @Column(name = "IS_NULLABLE")
    private String isNullable;
    @Column(name = "DATA_TYPE")
    private String dataType;
    @Column(name = "COLUMN_KEY")
    private String columnKey;
    @Column(name = "COLUMN_COMMENT")
    private String columnComment;
}
