package packt.java9.by.example.mybusiness.productinformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductInformationController {

    @Autowired
    ProductLookup lookup;

    @RequestMapping("/pi/{productId}")
    public ProductInformation getProductInformation(@PathVariable String productId, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        ProductInformation productInformation = lookup.byId(productId);
        return productInformation;
    }
}
