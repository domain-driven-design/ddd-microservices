package utils;

import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class PdfUtilTest {

    @Test
    void should_convert_html_to_pdf() throws IOException {
        String htmlContent = getHtmlContent();
        byte[] pdfBytes = PdfUtil.htmlToPdf(htmlContent, "订单详情", "内部测试，请勿转发");
        Files.write(pdfBytes, new File("order-detail.pdf"));
    }

    @SneakyThrows
    private String getHtmlContent() {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateEngine.setTemplateResolver(templateResolver);

        // 准备数据
        Order order = new Order();
        order.setId("123456");
        order.setCustomerName("John Doe");
        order.setDate(new Date());
        for (int i = 0; i < 100; i++) {
            order.getItems().add(new Item("Widget" + i + 1, 2, 9.99));
            order.getItems().add(new Item("Gadget" + i + 1, 1, 19.99));
        }
        order.calculateTotal();

        Context context = new Context();
        context.setVariable("order", order);
        return templateEngine.process("/html-templates/order_details.html", context);
    }

    @Data
    @AllArgsConstructor
    public static class Item {
        private String name;
        private int quantity;
        private double price;
    }

    @Data
    public static class Order {
        private String id;
        private String customerName;
        private Date date;
        private List<Item> items = new ArrayList<>();
        private double total;

        public void calculateTotal() {
            total = items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        }
    }
}
