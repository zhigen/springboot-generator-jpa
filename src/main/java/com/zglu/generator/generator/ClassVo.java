package com.zglu.generator.generator;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 类数据对象
 *
 * @author zglu
 */
@Data
public class ClassVo {
    private String packageName;
    private String importString;
    private String author;
    private String tableName;
    private String tableComment;
    private String tableNameMid;
    private String className;
    private String valName;
    private String fieldString;
    private String updateSql;

    public ClassVo(String tableName, List<Columns> columnsList, GeneratorConfig generatorConfig, String tableComment) {
        this.author = generatorConfig.getAuthor();
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.tableNameMid = ReplaceUtils.getUrlName(this.tableName);
        this.className = ReplaceUtils.getClassName(this.tableName);
        this.valName = ReplaceUtils.getFieldName(this.tableName);
        this.packageName = ReplaceUtils.getPackageName(this.tableName);
        List<FieldVo> fieldVoList = columnsList.stream().map(m -> new FieldVo(m, generatorConfig)).collect(Collectors.toList());
        this.importString = this.getImportStringByField(fieldVoList);
        this.fieldString = this.getFieldStringByField(fieldVoList);
        updateSql = fieldVoList.stream().map(m -> m.getName() + " = CASE WHEN :#{#" + valName + "." + m.getName() + "} IS NULL THEN " + m.getName() + " ELSE :#{#" + valName + "." + m.getName() + "} END").collect(Collectors.joining(generatorConfig.getSqlLineFeed()));
    }

    private String getImportStringByField(List<FieldVo> fieldVoList) {
        String temp = fieldVoList.stream().flatMap(m -> m.getNeedImport().stream()).distinct().collect(Collectors.joining(";\n"));
        if (StringUtils.hasText(temp)) {
            temp += ";\n";
        }
        return temp;
    }

    private String getFieldStringByField(List<FieldVo> fieldVoList) {
        String temp = fieldVoList.stream().map(FieldVo::toString).collect(Collectors.joining("\n"));
        if (StringUtils.hasText(temp)) {
            temp += "\n";
        }
        return temp;
    }

}
