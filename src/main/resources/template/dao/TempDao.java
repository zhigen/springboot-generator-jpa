package com.zglu.generator.target.{packageName}.dao;

import com.zglu.generator.generator.ReplaceUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * @author {author}
 */
@Component
@AllArgsConstructor
public class {className}Dao {
    private final {className}Repository {valName}Repository;
    @PersistenceContext
    private final EntityManager entityManager;

    public {className} save({className} {valName}) {
        boolean flag = {valName}.getId() == null || {valName}.getId() == 0L || !{valName}Repository.existsById({valName}.getId());
        Assert.isTrue(flag, "Duplicate key!");
        return {valName}Repository.save({valName});
    }

    public {className} findById(long id) {
        return {valName}Repository.findById(id).orElse(null);
    }

    public List<{className}> findAll(String q, String order, Integer offset, Integer limit) {
        String searchSql = "SELECT * FROM {database}.{tableName} ";
        if (StringUtils.hasText(q)) {
            searchSql += "WHERE " + ReplaceUtils.getColumnName(q);
        }
        if (StringUtils.hasText(order)) {
            searchSql += " ORDER BY " + ReplaceUtils.getColumnName(order);
        }
        if (offset != null && limit != null) {
            searchSql += " LIMIT " + limit + " OFFSET " + offset;
        }
        Query query = entityManager.createNativeQuery(searchSql, {className}.class);
        return query.getResultList();
    }

    public long count(String q) {
        String searchSql = "SELECT COUNT(id) FROM {database}.{tableName} ";
        if (StringUtils.hasText(q)) {
            searchSql += "WHERE " + ReplaceUtils.getColumnName(q);
        }
        Query query = entityManager.createNativeQuery(searchSql);
        BigInteger count = (BigInteger) query.getSingleResult();
        return count.longValue();
    }

    public {className} put({className} {valName}) {
        Assert.notNull({valName}.getId(), "id must not be null!");
        Assert.isTrue({valName}.getId() != 0L, "id must not be zero!");
        return {valName}Repository.save({valName});
    }

    public {className} update({className} {valName}) {
        {valName}Repository.update({valName});
        return {valName};
    }

    public void deleteById(long id) {
        {valName}Repository.deleteById(id);
    }

}
