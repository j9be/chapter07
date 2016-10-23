package packt.java9.by.example.mybusiness.productinformation;

import java.util.List;

public interface ProductLookup {

    ProductInformation byId(String id);
    List<ProductInformation> byQuery(String query);
}
