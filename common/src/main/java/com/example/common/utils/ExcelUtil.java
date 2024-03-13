package com.example.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.common.error.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.example.common.error.CommonError.FILE_READING_ERROR;
import static com.example.common.error.CommonError.READ_FILE_FAILED;
import static com.example.common.error.CommonError.WRITE_FILE_FAILED;

@Slf4j
/**
 * 1. Export data set as list
 * 2. Read Excel file to data set
 * 3. Export complex Excel file with template
 */ public class ExcelUtil {

    public static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    private ExcelUtil() {
    }

    public static <T> void read(MultipartFile file, Class<T> clazz, Consumer<T> consumer) {
        List<String> errorMessages = new ArrayList<>();
        try {
            EasyExcel.read(file.getInputStream(), clazz, getReadListener(consumer, errorMessages)).sheet().doRead();
        } catch (IOException e) {
            throw new BusinessException(READ_FILE_FAILED, file.getName());
        }
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            throw new BusinessException(FILE_READING_ERROR, errorMessages, file.getName());
        }
    }

    public static <T> void writeToList(String fileName, Class<T> clazz, HttpServletResponse response, List<T> entityExports, List<String> excludeColumns, String errorMessage) {
        try {
            ExcelWriter excelWriter = getEasyExcelWriter(fileName, clazz, response, excludeColumns);
            String sheetName = fileName.split("\\.")[0];
            excelWriter.write(entityExports, EasyExcelFactory.writerSheet(sheetName).build());
            excelWriter.finish();
        } catch (Exception exception) {
            log.error(errorMessage);
            response.reset();
            throw new BusinessException(WRITE_FILE_FAILED, fileName);
        }
    }

    public static void writeToDetail(String fileName, String templateFileName, Object data, HttpServletResponse response, String errorMessage) {
        setupResponseHeaderForHttpDownload(fileName, response);
        try (InputStream is = ExcelUtil.class.getResourceAsStream("/" + templateFileName)) {
            Context context = new Context();
            context.putVar("data", data);
            JxlsHelper.getInstance().processTemplate(is, response.getOutputStream(), context);
        } catch (Exception exception) {
            log.error(errorMessage);
            response.reset();
            throw new BusinessException(WRITE_FILE_FAILED, fileName);
        }
    }

    private static <T> ExcelWriter getEasyExcelWriter(String fileName, Class<T> clazz, HttpServletResponse response, List<String> excludeColumns) throws IOException {
        setupResponseHeaderForHttpDownload(fileName, response);
        return EasyExcel.write(response.getOutputStream(), clazz).excludeColumnFieldNames(excludeColumns).build();
    }

    private static void setupResponseHeaderForHttpDownload(String encodedFileName, HttpServletResponse response) {
        encodedFileName = URLEncoder.encode(encodedFileName, StandardCharsets.UTF_8);
        response.setContentType(CONTENT_TYPE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
    }

    private static <T> ReadListener getReadListener(Consumer<T> consumer, List<String> errorMessages) {
        return new ReadListener<T>() {
            @Override
            public void invoke(T o, AnalysisContext analysisContext) {
                consumer.accept(o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        };
    }
}
