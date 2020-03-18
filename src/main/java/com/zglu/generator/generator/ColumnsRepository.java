package com.zglu.generator.generator;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zglu
 */
@Repository
public interface ColumnsRepository extends PagingAndSortingRepository<Columns, Integer> {

    /**
     * 查询指定表的字段信息
     *
     * @param tableSchema 库名
     * @param tableName   表名数组
     * @return 字段数组
     */
    List<Columns> findByTableSchemaAndTableNameIn(String tableSchema, String[] tableName);

    /**
     * 查询指定库的所有表的字段信息
     *
     * @param tableSchema 库名
     * @return 字段数组
     */
    List<Columns> findByTableSchema(String tableSchema);
}
