package kodlamaio.northwind.util;

import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.entities.concretes.Category;
import kodlamaio.northwind.entities.concretes.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductUtil {
    public static DataResult<List<Product>> getSampleProductList(){
        List<Product> list = new ArrayList<>();
        list.add(Product.builder()
                .id(1)
                .productName("TestInstance")
                .unitPrice(1.0)
                .unitsInStock((short) 0)
                .quantityPerUnit("TestInstance")
                .category(Category.builder().categoryId(1).categoryName("CategoryTest").build())
                .build());
        return new DataResult<List<Product>>(list, true, "Data listelendi");
    }

    public static Result getSampleSuccessResultForAdd(){
        return new SuccessResult("Ürün eklendi");
    }

    public static Product getSampleProductForSave(){
        return Product.builder()
                .productName("TestInstance")
                .unitPrice(1.0)
                .unitsInStock((short) 0)
                .quantityPerUnit("TestInstance")
                .category(Category.builder().categoryId(1).build())
                .build();
    }

    public static String productToJson(Product p){
        return "{\"category\": {\"categoryId\": " + p.getCategory().getCategoryId() +"}," +
                "\"productName\": \"" + p.getProductName() +"\"," +
                "\"quantityPerUnit\": \"" + p.getQuantityPerUnit() +"\"," +
                "\"unitPrice\": " + p.getUnitPrice() +"," +
                "\"unitsInStock\": " + p.getUnitsInStock() +
                "}";
    }

    public static List<Product> dataOfDataResult(DataResult<List<Product>> result){
        return result.getData();
    }

    public static Product getSampleProduct(){
        return Product.builder()
                .id(1)
                .productName("TestInstance")
                .unitPrice(1.0)
                .unitsInStock((short) 0)
                .quantityPerUnit("TestInstance")
                .category(Category.builder().categoryId(1).categoryName("CategoryTest").build())
                .build();
    }
}
