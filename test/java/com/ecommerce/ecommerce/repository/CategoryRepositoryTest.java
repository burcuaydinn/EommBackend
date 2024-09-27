package com.ecommerce.ecommerce.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.ecommerce.ecommerce.dto.request.CategoryRequest;
import com.ecommerce.ecommerce.dto.response.CategoryResponse;
import com.ecommerce.ecommerce.entity.Category;
import com.ecommerce.ecommerce.enums.CategoryType;
import com.ecommerce.ecommerce.repository.CategoryRepository;
import com.ecommerce.ecommerce.service.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;

    @BeforeEach
    public void setUp() {

        category = new Category();
        category.setId(2L);
        category.setName("KoltukTakımı");
        category.setType(CategoryType.HOME);
    }

    @Test
    public void testSaveCategory() {

        CategoryRequest request = new CategoryRequest("KoltukTakımı", CategoryType.HOME);


        when(categoryRepository.save(any(Category.class))).thenReturn(category);


        CategoryResponse savedCategory = categoryService.addCategory(request);
        assertNotNull(savedCategory);
        assertEquals("KoltukTakımı", savedCategory.name());

        verify(categoryRepository, times(1)).save(any(Category.class));
    }


    @Test
    public void testFindById() {

        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));


        CategoryResponse foundCategory = categoryService.getCategoryById(2L);


        assertNotNull(foundCategory);
        assertEquals(2L, foundCategory.id());
        assertEquals("KoltukTakımı", foundCategory.name());


        verify(categoryRepository, times(1)).findById(2L);
    }


}
