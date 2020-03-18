package com.zglu.generator.generator;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zglu
 */
@Data
@Entity
@Table(name = "TABLES")
@IdClass(TablesId.class)
public class Tables {
    @Id
    @Column(name = "TABLE_SCHEMA")
    private String tableSchema;
    @Id
    @Column(name = "TABLE_NAME")
    private String tableName;
    @Column(name = "TABLE_COMMENT")
    private String tableComment;
}
