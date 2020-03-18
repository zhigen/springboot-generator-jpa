package com.zglu.generator.generator;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 代码生成实现类
 *
 * @author zglu
 */
@Log4j2
@Service
@AllArgsConstructor
public class GeneratorService {

    private final TablesRepository tablesRepository;
    private final ColumnsRepository columnsRepository;
    private final GeneratorConfig generatorConfig;

    public void generate() {
        // 查询表字段
        List<Columns> list;
        List<Tables> tablesList;
        if (generatorConfig.getTables().length > 0) {
            list = columnsRepository.findByTableSchemaAndTableNameIn(generatorConfig.getDatabase(), generatorConfig.getTables());
            tablesList = tablesRepository.findByTableSchemaAndTableNameIn(generatorConfig.getDatabase(), generatorConfig.getTables());
        } else {
            list = columnsRepository.findByTableSchema(generatorConfig.getDatabase());
            tablesList = tablesRepository.findByTableSchema(generatorConfig.getDatabase());
        }
        // 按表分组字段
        Map<String, List<Columns>> map = list.stream().collect(Collectors.groupingBy(Columns::getTableName));
        Map<String, String> tablesMap = tablesList.stream().collect(Collectors.toMap(Tables::getTableName, Tables::getTableComment));
        // 按表生成
        map.forEach((k, v) -> {
            try {
                this.createFiles(generatorConfig, k, v, tablesMap.get(k));
            } catch (IOException e) {
                log.info(e);
            }
        });
    }

    /**
     * 创建类
     *
     * @param generatorConfig 生成配置
     * @param tableName       表名
     * @param list            字段数组
     * @throws IOException io异常
     */
    public void createFiles(GeneratorConfig generatorConfig, String tableName, List<Columns> list, String tableComment) throws IOException {
        log.info("开始生成" + tableName + "数据");
        log.info("生成Class数据");
        ClassVo classVo = new ClassVo(tableName, list, generatorConfig, tableComment);

        log.info("读取Entity模版");
        String content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/dao", "Temp.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成Entity文件");
        String targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "dao");
        Path path = FileUtils.create(targetDir, classVo.getClassName() + ".java", false);
        log.info("写入Entity文件");
        FileUtils.write(path, content);

        log.info("读取mapper模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/dao", "TempRepository.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成mapper文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "dao");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Repository.java", false);
        log.info("写入mapper文件");
        FileUtils.write(path, content);

        log.info("读取dao模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/dao", "TempDao.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成dao文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "dao");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Dao.java", false);
        log.info("写入dao文件");
        FileUtils.write(path, content);

        log.info("读取service模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/service", "TempService.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成service文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "service");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Service.java", false);
        log.info("写入service文件");
        FileUtils.write(path, content);

        log.info("读取dto模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/dto", "TempDto.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成dto文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "dto");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Dto.java", false);
        log.info("写入dto文件");
        FileUtils.write(path, content);

        log.info("读取controller模版");
        content = FileUtils.getTemplate(generatorConfig.getTemplatePath() + "/controller", "TempController.java");
        content = ReplaceUtils.replaceContent(content, classVo);
        log.info("生成controller文件");
        targetDir = FileUtils.createDir(generatorConfig.getTargetPath(), generatorConfig.getTargetDir(), classVo.getPackageName(), "controller");
        path = FileUtils.create(targetDir, classVo.getClassName() + "Controller.java", false);
        log.info("写入controller文件");
        FileUtils.write(path, content);
    }

}
