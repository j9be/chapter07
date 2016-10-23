package packt.java9.by.example.mybusiness.productinformation.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import packt.java9.by.example.mybusiness.productinformation.ProductInformation;
import packt.java9.by.example.mybusiness.productinformation.ProductLookup;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ResourceBasedProductLookup implements ProductLookup {
    Logger log = LoggerFactory.getLogger(ResourceBasedProductLookup.class);

    ProductInformation emptyProductInformation = new ProductInformation();

    public ResourceBasedProductLookup() {
        emptyProductInformation.setTitle("");
        emptyProductInformation.setDescription("");
        emptyProductInformation.setId("");
        emptyProductInformation.setWeight(0);
        emptyProductInformation.getSize()[0] = 0;
        emptyProductInformation.getSize()[1] = 0;
        emptyProductInformation.getSize()[2] = 0;

    }

    @Override
    public ProductInformation byId(String id) {
        Properties productProperties = new Properties();
        ProductInformation pi = new ProductInformation();
        try (InputStream is =
                     ResourceBasedProductLookup.class
                             .getClassLoader()
                             .getResourceAsStream("products/" + id + ".properties");) {
            productProperties.load(is);
            pi.setId(id);
            pi.setTitle(productProperties.getProperty("title"));
            pi.setDescription(productProperties.getProperty("description"));
            pi.setWeight(Double.parseDouble(productProperties.getProperty("weight")));
            pi.getSize()[0] = Double.parseDouble(productProperties.getProperty("width"));
            pi.getSize()[1] = Double.parseDouble(productProperties.getProperty("height"));
            pi.getSize()[2] = Double.parseDouble(productProperties.getProperty("depth"));
        } catch (IOException e) {
            log.warn("there was a query for product with id {} that does not exist", id);
        }
        return pi;

    }

    @Override
    public List<ProductInformation> byQuery(String query) {
        return null;
    }
}