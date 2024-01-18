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

    public byte[] generateOrderSummary(List<Product> products, double totalPrice, double totalPriceWithDelivery, DeliveryForm deliveryForm) throws DocumentException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("Order Summary:"));
            for (Product product : products) {
                document.add(new Paragraph(product.getTitle() + " - Quantity: " + product.getQuantity() + " - price: " + product.getPrice() + "$" + " - delivery price: " + product.getDeliveryPrice() + "$"));
            }
            document.add(new Paragraph("Total Price: " + totalPrice + "$"));
            document.add(new Paragraph("Total Price with Delivery: " + (totalPriceWithDelivery) + "$"));

            document.add(new Paragraph("\nDelivery Details:"));
            document.add(new Paragraph("Delivery Method: " + deliveryForm.getDeliveryMethod()));
            document.add(new Paragraph("Street: " + deliveryForm.getStreet()));
            document.add(new Paragraph("House Number: " + deliveryForm.getHouseNumber()));
            document.add(new Paragraph("Apartment Number: " + deliveryForm.getApartmentNumber()));
            document.add(new Paragraph("Postal Code: " + deliveryForm.getPostalCode()));
            document.add(new Paragraph("City: " + deliveryForm.getCity()));

            document.close();

            return baos.toByteArray();
        } catch (Exception e) {
            throw new DocumentException("Error during PDF generation", e);
        }
    }
}

