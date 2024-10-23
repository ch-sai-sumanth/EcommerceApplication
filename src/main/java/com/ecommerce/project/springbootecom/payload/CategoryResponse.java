package com.ecommerce.project.springbootecom.payload;


import lombok.Data;

import java.util.List;

@Data
public class CategoryResponse {
   private List<CategoryDTO> content;
}
