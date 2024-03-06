package utils;

import com.google.common.io.Files;
import fixtures.OrderFixture;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.IOException;


class PdfUtilTest {

    @Test
    void should_convert_html_to_pdf() throws IOException {
        String htmlContent = getHtmlContent();
        byte[] pdfBytes = PdfUtil.htmlToPdf(htmlContent, "waterMark");
        Files.write(pdfBytes, new File("order-detail.pdf"));
    }

    @SneakyThrows
    private String getHtmlContent() {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("order", OrderFixture.buildData());
        return templateEngine.process("/html-templates/order_details.html", context);
    }

}
