package com.zglu.generator.target.{packageName}.service;

import com.zglu.generator.target.{packageName}.dao.{className};
import com.zglu.generator.target.{packageName}.dao.{className}Dao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author {author}
 */
@Service
@AllArgsConstructor
public class {className}Service {
    private final {className}Dao {valName}Dao;

    public {className} add({className} {valName}) {
        return {valName}Dao.save({valName});
    }

    public {className} get(long id) {
        return {valName}Dao.findById(id);
    }

    public List<{className}> list(String q, String order, Integer offset, Integer limit) {
        return {valName}Dao.findAll(q, order, offset, limit);
    }

    public Page<{className}> page(String q, String order, Integer number, Integer size) {
        List<{className}> content = {valName}Dao.findAll(q, order, number * size, size);
        Pageable pageable = PageRequest.of(number, size);
        long total = {valName}Dao.count(q);
        return new PageImpl<>(content, pageable, total);
    }

    public {className} put({className} {valName}) {
        return {valName}Dao.put({valName});
    }

    public {className} set({className} {valName}) {
        return {valName}Dao.update({valName});
    }

    public void remove(long id) {
        {valName}Dao.deleteById(id);
    }

}
