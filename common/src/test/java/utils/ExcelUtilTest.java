package utils;

import com.alibaba.excel.annotation.ExcelProperty;
import com.google.common.io.Files;
import fixtures.OrderFixture;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ExcelUtilTest {

    @Test
    void should_read_excel_file_to_data_set() {
    }

    @Test
    void should_generate_excel_file_from_list() throws IOException {
        List<DemoData> dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setColumn1("Row " + (i + 1) + ", Column 1");
            data.setColumn2("Row " + (i + 1) + ", Column 2");
            dataList.add(data);
        }
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        ExcelUtil.writeToList("list-excel-example.xlsx", DemoData.class, mockHttpServletResponse, dataList, Lists.emptyList(), "");

        Files.write(mockHttpServletResponse.getContentAsByteArray(), new File("list-excel-example.xlsx"));
    }

    @Test
    void should_generate_excel_detail() throws IOException {
        Object data = OrderFixture.buildData();

        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        ExcelUtil.writeToDetail("detail-excel-example.xlsx", "excel-templates/template.xlsx", data, mockHttpServletResponse, "");

        Files.write(mockHttpServletResponse.getContentAsByteArray(), new File("detail-excel-example.xlsx"));
    }

    @Data
    public static class DemoData {
        @ExcelProperty("Column1")
        private String column1;
        @ExcelProperty("Column2")
        private String column2;
    }
}
