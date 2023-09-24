package kz.timka.springmvc.controllers;

import kz.timka.springmvc.converters.ProductConverter;
import kz.timka.springmvc.exceptions.ResourceNotFoundException;
import kz.timka.springmvc.dto.ProductDto;
import kz.timka.springmvc.models.Product;
import kz.timka.springmvc.services.ProductService;
import kz.timka.springmvc.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;



    @GetMapping()
    public Page<ProductDto> findAll(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "part_title", required = false) String partTitle
    ) {
        if(page < 1) {
            page = 1;
        }

        return productService.find(minPrice, maxPrice, partTitle, page).map(
                productConverter::entityToDto
        );
    }

    @PostMapping()
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);

    }


    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found by id: " + id));
        return productConverter.entityToDto(product);
    }

    @PutMapping()
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productService.update(productDto);
        return productConverter.entityToDto(product);
    }




    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }




}
