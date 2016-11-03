package packt.java9.by.example.mybusiness.productinformation.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;
import packt.java9.by.example.mybusiness.productinformation.ProductInformation;
import packt.java9.by.example.mybusiness.productinformation.ProductLookup;

import java.io.IOException;
import java.util.*;

@Service
public class ResourceBasedProductLookup implements ProductLookup {
    private static Logger log = LoggerFactory.getLogger(ResourceBasedProductLookup.class);

    private static final ProductInformation emptyProductInformation = new ProductInformation();

    static {
        emptyProductInformation.setTitle("");
        emptyProductInformation.setDescription("");
        emptyProductInformation.setId("");
        emptyProductInformation.setWeight(0);
        emptyProductInformation.getSize()[0] = 0;
        emptyProductInformation.getSize()[1] = 0;
        emptyProductInformation.getSize()[2] = 0;
    }

    public ResourceBasedProductLookup() {
    }

    @Override
    public ProductInformation byId(String id) {

        loadProducts();

        return products.get(id);
    }

    private ProductInformation fromProperties(Properties properties) {
        final ProductInformation pi = new ProductInformation();
        pi.setTitle(properties.getProperty("title"));
        pi.setDescription(properties.getProperty("description"));
        pi.setWeight(Double.parseDouble(properties.getProperty("weight")));
        pi.getSize()[0] = Double.parseDouble(properties.getProperty("width"));
        pi.getSize()[1] = Double.parseDouble(properties.getProperty("height"));
        pi.getSize()[2] = Double.parseDouble(properties.getProperty("depth"));
        return pi;
    }


    final private Map<String, ProductInformation> products = new HashMap<>();
    private boolean productsAreNotLoaded = true;

    private void loadProducts() {
        if (productsAreNotLoaded) {
            try {
                Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:products/*.properties");
                for (Resource resource : resources) {
                    final int dotPos = resource.getFilename().lastIndexOf('.');
                    if (dotPos != -1) {
                        final String fileName = resource.getFilename().substring(0, dotPos);
                        Properties properties = new Properties();
                        properties.load(resource.getInputStream());
                        final ProductInformation pi = fromProperties(properties);
                        pi.setId(fileName);
                        products.put(fileName, pi);
                    }
                }
                productsAreNotLoaded = false;
            } catch (IOException ex) {
                log.error("Test resources can not be read",ex);
            }
        }
    }

    private static final List<String> noProducts = new LinkedList<>();

    @Override
    public List<String> byQuery(String query) {
        log.error("query is {}", query);
        loadProducts();
        List<String> pis = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(query, "&=");
        while (st.hasMoreTokens()) {
            final String key = st.nextToken();
            if (st.hasMoreTokens()) {
                final String value = st.nextToken();
                log.debug("processing {}={} query", key, value);
                if (!"title".equals(key)) {
                    log.error("Search by title is allowed only");
                    return noProducts;
                }
                for (String id : products.keySet()) {
                    log.error("key: {} value:{} id:{}", key, value, id);
                    ProductInformation pi = products.get(id);
                    if (pi.getTitle().startsWith(value)) {
                        pis.add(id);
                    }
                }
            }
        }
        return pis;
    }
}