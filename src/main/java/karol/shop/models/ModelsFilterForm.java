package karol.shop.models;

import lombok.Data;

@Data
public class ModelsFilterForm {
    private String selectedModel;
    private String selectedSortOption;
    private String selectedSortDirection;
}
