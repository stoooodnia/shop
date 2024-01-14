package karol.shop.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class DeliveryForm {

    @NotBlank(message = "Proszę wybrać sposób dostawy")
    private String deliveryMethod;

    @NotBlank(message = "Ulica nie może być pusta")
    @Size(max = 255, message = "Ulica nie może przekraczać 255 znaków")
    private String street;

    @NotBlank(message = "Numer domu nie może być pusty")
    @Size(max = 10, message = "Numer domu nie może przekraczać 10 znaków")
    private String houseNumber;

    @Size(max = 10, message = "Numer mieszkania nie może przekraczać 10 znaków")
    private String apartmentNumber;

    @NotBlank(message = "Kod pocztowy nie może być pusty")
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Nieprawidłowy format kodu pocztowego. Poprawny format: XX-XXX")
    private String postalCode;

    @NotBlank(message = "Miasto nie może być puste")
    @Size(max = 100, message = "Miasto nie może przekraczać 100 znaków")
    private String city;
}
