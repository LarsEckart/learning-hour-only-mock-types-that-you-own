package learninghour.location.v2;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    CloseableHttpClient httpClient;
    @Mock
    CloseableHttpResponse response;
    @Mock
    HttpEntity httpEntity;

    @InjectMocks
    LocationService locationService;

    @Test
    void testGetLocation() throws IOException {
        when(httpClient.execute(any())).thenReturn(response);
        when(response.getEntity()).thenReturn(httpEntity);
        String responseBody = """
                {
                    "ipAddress": "1.1.1.1",
                    "continentCode": "OC",
                    "continentName": "Oceania",
                    "countryCode": "AU",
                    "countryName": "Australia",
                    "stateProvCode": "NSW",
                    "stateProv": "New South Wales",
                    "city": "Sydney"
                }
                """;
        InputStream targetStream = new ByteArrayInputStream(responseBody.getBytes());
        when(httpEntity.getContent()).thenReturn(targetStream);

        Location location = locationService.getLocation("1.1.1.1");

        assertThat(location.city()).isEqualTo("Sydney");
        assertThat(location.country()).isEqualTo("Australia");
    }
}
