package com.zglu.generator.generator;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zglu
 */
@Repository
public interface TablesRepository extends PagingAndSortingRepository<Tables, Integer> {

    /**
     * 查询指定表的字段信息
     *
     * @param tableSchema 库名
     * @param tableName   表名数组
     * @return 字段数组
     */
    List<Tables> findByTableSchemaAndTableNameIn(String tableSchema, String[] tableName);

    /**
     * 查询指定库的所有表的字段信息
     *
     * @param tableSchema 库名
     * @return 字段数组
     */
    List<Tables> findByTableSchema(String tableSchema);
}
