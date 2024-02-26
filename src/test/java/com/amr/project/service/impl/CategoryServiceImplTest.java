package com.amr.project.service.impl;

import com.amr.project.dao.abstracts.CategoryDao;
import com.amr.project.model.entity.Category;
import com.amr.project.util.Util;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoryServiceImplTest {
    @Mock
    private CategoryDao categoryDao;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeAll
    void init() {
        System.out.println("Before all: " + this);
    }

    @Test
    void updateCategory() {
        Long id = 1L;
        String name = "old_category";

        Category category = Category.builder()
                .id(id)
                .name(name)
                .guid(Util.getGuid())
                .build();

        when(categoryDao.getCategoryById(id)).thenReturn(category);

        Category updateCategory = Category.builder().id(id).name("new_category").guid(Util.getGuid()).build();

        categoryService.update(updateCategory);

        System.out.println(category);
        System.out.println(updateCategory);

        Assertions.assertEquals("new_category", updateCategory.getName());
        Assertions.assertEquals(category.getGuid(), updateCategory.getGuid());
    }

}

