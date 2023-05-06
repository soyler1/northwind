package kodlamaio.northwind.unit;

import kodlamaio.northwind.business.concretes.ProductManager;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.util.ProductUtil;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.*;


public class ProductManagerTest {

    ProductDao mockProductDao = mock(ProductDao.class);
    private ProductManager productManager = new ProductManager(mockProductDao);

    @Test
    void testGetAll(){

        when(mockProductDao.findAll())
                .thenReturn(ProductUtil.dataOfDataResult(ProductUtil.getSampleProductList()));

        DataResult<List<Product>> resultProductList = productManager.getAll();

        BDDAssertions.then(resultProductList.getData().get(0).getProductName()).isEqualTo("TestInstance");
        BDDAssertions.then(resultProductList.getData().get(0).getQuantityPerUnit()).isEqualTo("TestInstance");
        BDDAssertions.then(resultProductList.getData().get(0).getUnitsInStock()).isEqualTo((short) 0);
        BDDAssertions.then(resultProductList.getData().get(0).getUnitPrice()).isEqualTo(1.0);
        BDDAssertions.then(resultProductList.getData().get(0).getCategory().getCategoryId()).isEqualTo(1);

        verify(mockProductDao, times(1)).findAll();
    }

    @Test
    void testAdd(){

        when(mockProductDao.save(ProductUtil.getSampleProductForSave()))
                .thenReturn(ProductUtil.getSampleProduct());

        Result result = productManager.add(ProductUtil.getSampleProductForSave());

        BDDAssertions.then(result.getClass()).isEqualTo(SuccessResult.class);
        BDDAssertions.then(result.getMessage()).isEqualTo("Ürün eklendi");

        verify(mockProductDao,times(1)).save(ProductUtil.getSampleProductForSave());
    }
}
