package com.example.common.utils;

import com.example.common.error.BusinessException;
import com.openhtmltopdf.extend.FSObjectDrawer;
import com.openhtmltopdf.extend.FSObjectDrawerFactory;
import com.openhtmltopdf.extend.OutputDevice;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.render.RenderingContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Element;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static com.example.common.error.CommonError.WRITE_FILE_FAILED;

/**
 * PDF 导出理想方案，性能好，支持分页、水印
 */
@Slf4j
public class PdfUtil {

    private PdfUtil() {
    }

    public static byte[] htmlToPdf(String htmlContent, String waterMark) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.useObjectDrawerFactory(new WatermarkDrawerFactory(waterMark));
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        } catch (IOException e) {
            log.error("Fail to create pdf", e);
            throw new BusinessException(WRITE_FILE_FAILED);
        }
    }

    @AllArgsConstructor
    private static class WatermarkDrawer implements FSObjectDrawer {
        private String waterMark;

        @Override
        public Map<Shape, String> drawObject(Element e, double x, double y, double width, double height, OutputDevice outputDevice, RenderingContext ctx, int dotsPerPixel) {
            outputDevice.drawWithGraphics((float) x, (float) y, (float) width / dotsPerPixel, (float) height / dotsPerPixel, (Graphics2D g2d) -> {

                double realWidth = width / dotsPerPixel;
                double realHeight = height / dotsPerPixel;

                Font font;
                try {
                    InputStream fontInputStream = WatermarkDrawer.class.getResourceAsStream("/fonts/Arial.ttf");
                    Font parent = Font.createFont(Font.TRUETYPE_FONT, fontInputStream);
                    font = parent.deriveFont(20f);
                } catch (FontFormatException | IOException e1) {
                    log.error("Fail to draw watermark", e1);
                    throw new BusinessException(WRITE_FILE_FAILED);
                }
                Rectangle2D bounds = font.getStringBounds(waterMark, g2d.getFontRenderContext());

                g2d.setFont(font);
                g2d.setPaint(Color.BLACK);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
                g2d.rotate(Math.toRadians(45));
                int margin = 100;
                int offset = 600;

                // Watermark matrix
                for (int row = 0; row < 10; row++) {
                    for (int column = 0; column < 10; column++) {
                        g2d.drawString(waterMark,
                                (float) ((bounds.getWidth() + margin) * (column + 1) - offset),
                                (float) ((bounds.getHeight() + margin) * (row + 1) - offset)
                        );
                    }
                }
            });

            return null;
        }
    }

    @AllArgsConstructor
    private static class WatermarkDrawerFactory implements FSObjectDrawerFactory {
        private String waterMark;

        @Override
        public FSObjectDrawer createDrawer(Element e) {
            if (isReplacedObject(e)) {
                return new WatermarkDrawer(this.waterMark);
            }
            return null;
        }

        @Override
        public boolean isReplacedObject(Element e) {
            return e.getAttribute("type").equals("watermark");
        }
    }
}
