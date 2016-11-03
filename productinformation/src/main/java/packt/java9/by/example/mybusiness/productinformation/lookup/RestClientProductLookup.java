package packt.java9.by.example.mybusiness.productinformation.lookup;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import packt.java9.by.example.mybusiness.productinformation.ProductInformation;
import packt.java9.by.example.mybusiness.productinformation.ProductLookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestClientProductLookup  implements ProductLookup {
    private String byIdUrl;

    public void setByIdUrl(String byIdUrl) {
        this.byIdUrl = byIdUrl;
    }

    @Override
    public ProductInformation byId(String id) {
        Map<String,String> uriParameters = new HashMap<>();
        uriParameters.put("id",id);
        RestTemplate rest = new RestTemplate();
        return rest.getForObject(byIdUrl,ProductInformation.class,uriParameters);
    }

    @Override
    public List<String> byQuery(String query) {
        return null;
    }
}
