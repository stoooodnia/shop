package karol.shop.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import karol.shop.models.DeliveryForm;
import karol.shop.models.Product;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class OrderSummaryPdfGenerator {

    public byte[] generateOrderSummary(List<Product> products, double totalPrice, DeliveryForm deliveryForm) throws DocumentException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            // Dodaj informacje o zamówieniu
            document.add(new Paragraph("Order Summary:"));
            for (Product product : products) {
                document.add(new Paragraph(product.getTitle() + " - " + product.getPrice() + "$"));
            }
            document.add(new Paragraph("Total Price: " + totalPrice + "$"));

            // Dodaj informacje o dostawie
            document.add(new Paragraph("\nDelivery Details:"));
            document.add(new Paragraph("Delivery Method: " + deliveryForm.getDeliveryMethod()));

            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new DocumentException("Error during PDF generation", e);
        }
    }
}
