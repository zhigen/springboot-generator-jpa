package com.zglu.generator.target.{packageName}.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author {author}
 */
@Repository
public interface {className}Repository extends PagingAndSortingRepository<{className}, Long> {

    /**
     * U
     *
     * @param {valName} 记录
     * @return 影响行数
     */
    @Modifying
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "UPDATE {className} SET " +
            "{updateSql} " +
            "WHERE id=:#{#{valName}.id}")
    int update(@Param("{valName}") {className} {valName});
}
