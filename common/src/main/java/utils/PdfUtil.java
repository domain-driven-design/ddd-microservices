package utils;

import com.openhtmltopdf.extend.FSObjectDrawer;
import com.openhtmltopdf.extend.FSObjectDrawerFactory;
import com.openhtmltopdf.extend.OutputDevice;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.render.RenderingContext;
import org.w3c.dom.Element;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * PDF 导出理想方案，性能好，支持分页、水印
 */
public class PdfUtil {

    private PdfUtil() {
    }

    public static byte[] htmlToPdf(String htmlContent, String title, String waterMark) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.useObjectDrawerFactory(new WatermarkDrawerFactory());
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class WatermarkDrawer implements FSObjectDrawer {
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
                    e1.printStackTrace();
                    throw new RuntimeException(e1);
                }
                // TODO as parameter
                String watermarkString = "OpenHTMLToPDF";
                Rectangle2D bounds = font.getStringBounds(watermarkString, g2d.getFontRenderContext());

                g2d.setFont(font);
                g2d.setPaint(Color.RED);
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

                g2d.drawString(watermarkString, (float) ((realWidth - bounds.getWidth()) / 2), (float) ((realHeight - bounds.getHeight()) / 2));

            });

            return null;
        }
    }

    private static class WatermarkDrawerFactory implements FSObjectDrawerFactory {
        @Override
        public FSObjectDrawer createDrawer(Element e) {
            if (isReplacedObject(e)) {
                return new WatermarkDrawer();
            }
            return null;
        }

        @Override
        public boolean isReplacedObject(Element e) {
            return e.getAttribute("type").equals("watermark");
        }
    }
}
