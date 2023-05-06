package kodlamaio.northwind.unit;

import jdk.jfr.ContentType;
import kodlamaio.northwind.api.controllers.ProductsController;
import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.util.ProductUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductsController.class)
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void whenGetAll_shouldReturnAllProductsAndStatusCodeIsOk() throws Exception {

        when(productService.getAll()).thenReturn(ProductUtil.getSampleProductList());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/products/getall")
                .header("Content-Type", "application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("success").value(true))
                .andExpect(jsonPath("message").value("Data listelendi"));

        verify(productService, times(1)).getAll();

    }

    @Test
    void whenPostProduct_shouldReturnSuccessResultAndStatusCodeCreated() throws Exception {

        Product product = ProductUtil.getSampleProductForSave();

        when(productService.add(refEq(product)))
                .thenReturn(ProductUtil.getSampleSuccessResultForAdd());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(ProductUtil.productToJson(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("success").value(true))
                .andExpect(jsonPath("message").value("Ürün eklendi"));

        verify(productService, times(1)).add(refEq(ProductUtil.getSampleProductForSave()));

    }

}
