package packt.java9.by.example.mybusiness.productinformation;

public class ProductInformationServiceUrlBuilder {
    private final String baseUrl;

    public ProductInformationServiceUrlBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String url(String service, String parameter) {
        final String serviceUrl;
        switch (service) {
            case "pi":
                serviceUrl = baseUrl + ":8081/product/" + parameter;
                break;
            case "query":
                serviceUrl = baseUrl + ":8081/query/" + parameter;
                break;
            case "inventory":
                serviceUrl = baseUrl + ":8083/inventory/" + parameter;
                break;
            default:
                serviceUrl = null;
                break;
        }
        return serviceUrl;
    }
}
