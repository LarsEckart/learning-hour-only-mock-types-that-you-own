package learninghour.location.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

class LocationService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${dbip.api.key}")
    private String apiKey;
    @Autowired
    private CloseableHttpClient httpClient;

    public Location getLocation(String ip) throws IOException {
        String url = "http://api.db-ip.com/v2/" + apiKey() + "/" + ip;
        final HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        String bodyAsString = EntityUtils.toString(response.getEntity());
        if (bodyAsString.contains("errorCode")) { // { "errorCode": "INVALID_ADDRESS", "error": "invalid address '88.196.236.9511' }"
            throw new RuntimeException("Failed to get location: " + bodyAsString);
        }
        var dbIpResponse = objectMapper.readValue(bodyAsString, DbIpResponse.class);
        return new Location(dbIpResponse.city, dbIpResponse.countryName);
    }

    private String apiKey() {
        return apiKey == null ? "free" : apiKey;
    }

    record DbIpResponse(
            String ipAddress, String continentCode, String continentName, String countryCode, String countryName,
            String stateProvCode, String stateProv, String city) {
    }
}
