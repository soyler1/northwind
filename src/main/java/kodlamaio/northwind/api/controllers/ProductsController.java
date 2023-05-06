package kodlamaio.northwind.api.controllers;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductsController {

    private ProductService productService;
    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getall")
    public DataResult<List<Product>> getAll(){
        return this.productService.getAll();
    }
    @GetMapping("/getallByPage")
    public DataResult<List<Product>> getAll(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        return this.productService.getAll(pageNo-1, pageSize);
    }
    @GetMapping("/getallAsc")
    public DataResult<List<Product>> getAllSorted(){
        return this.productService.getAllSorted();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Result add(@RequestBody Product product){return this.productService.add(product);}
    @GetMapping("/getByProductName")
    public DataResult<Product> getByProductName(@RequestParam String productName){ return this.productService.getByProductName(productName);}
    @GetMapping("/getByProductNameAndCategoryId")
    public DataResult<Product> getByProductNameAndCategoryId(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId) {
        return this.productService.getByProductNameAndCategoryId(productName, categoryId);
    }
    @GetMapping("/getByProductNameOrCategoryId")
    public DataResult<List<Product>> getByProductNameOrCategoryId(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId){
        return this.productService.getByProductNameOrCategoryId(productName, categoryId);
    }
    @GetMapping("/getByCategoryIdIn")
    public DataResult<List<Product>> getByCategoryIdIn(@RequestParam List<Integer> categories){
        return this.productService.getByCategoryIdIn(categories);
    }
    @GetMapping("/getByProductNameContains")
    public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName){
        return this.productService.getByProductNameContains(productName);
    }
    @GetMapping("/getByProductNameStartsWith")
    public DataResult<List<Product>> getByProductNameStartsWith(@RequestParam String productName){
        return this.productService.getByProductNameStartsWith(productName);
    }
    @GetMapping("/getByNameAndCategory")
    public DataResult<List<Product>> getByNameAndCategory(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId){
        return this.productService.getByNameAndCategory(productName, categoryId);
    }

    @GetMapping("/getProductWithCategoryDetails")
    public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails(){
        return this.productService.getProductWithCategoryDetails();
    }
}
