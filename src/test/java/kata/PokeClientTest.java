package kata;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PokeClientTest {

    public static final String DUMMY_URL = "https://example.com/";
    public static final String DUMMY_API_KEY = "anyApiKey";
    @Mock
    private OkHttpClient okHttpClient;
    @Mock
    private Call call;
    @Mock
    private Response response;
    @Mock
    private ResponseBody responseBody;

    private PokeClient pokeClient;

    @BeforeEach
    void setUp() {
        pokeClient = new PokeClient(new PokeConfig(DUMMY_URL, DUMMY_API_KEY), okHttpClient);
    }

    @Test
    void retrieve_capitalised_pokemon_name() throws IOException {
        mockResponse("""
                {
                  "id": 25,
                  "name": "pikachu"
                }""");

        String actual = pokeClient.getName(25);

        assertThat(actual).isEqualTo("Pikachu");
    }

    private void mockResponse(String responseBody) throws IOException {
        given(okHttpClient.newCall(any())).willReturn(call);
        given(call.execute()).willReturn(response);
        given(response.isSuccessful()).willReturn(true);
        given(response.body()).willReturn(this.responseBody);
        given(this.responseBody.string()).willReturn(responseBody);
    }

    @Test
    void set_authorization_header() throws IOException {
        mockResponse("""
                {
                  "id": 25,
                  "name": "pikachu"
                }""");

        pokeClient.getName(25);

        ArgumentCaptor<Request> argumentCaptor = ArgumentCaptor.forClass(Request.class);
        verify(okHttpClient).newCall(argumentCaptor.capture());
        Request request = argumentCaptor.getValue();
        assertThat(request.header("Authorization")).isEqualTo(DUMMY_API_KEY);
    }

    @Test
    void make_request_to_expected_url() throws IOException {
        mockResponse("""
                {
                  "id": 25,
                  "name": "pikachu"
                }""");

        pokeClient.getName(25);

        ArgumentCaptor<Request> argumentCaptor = ArgumentCaptor.forClass(Request.class);
        verify(okHttpClient).newCall(argumentCaptor.capture());
        Request request = argumentCaptor.getValue();
        assertThat(request.url().toString()).isEqualTo("https://example.com/pokemon/25");
    }

    @Test
    void testGetNameFails() throws IOException {
        given(okHttpClient.newCall(any())).willReturn(call);
        given(call.execute()).willReturn(response);
        given(response.isSuccessful()).willReturn(false);

        String actual = pokeClient.getName(25);

        assertThat(actual).isEqualTo("Unknown");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 1009, 2000})
    void testGetNameInvalidId(int id) {
        PokeClient pokeClient = new PokeClient(new PokeConfig(null, null), null);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> pokeClient.getName(id))
                .withMessage("id '%s' is not between 1 and 1008.".formatted(id));
    }

    @Test
    void testGetLocations() throws IOException {
        mockResponse("""
                [
                  {
                    "location_area": {
                      "name": "viridian-forest-area",
                      "url": "https://pokeapi.co/api/v2/location-area/321/"
                    },
                    "version_details": [
                      {
                        "version": {
                          "name": "red",
                          "url": "https://pokeapi.co/api/v2/version/1/"
                        }
                      },
                      {
                        "version": {
                          "name": "blue",
                          "url": "https://pokeapi.co/api/v2/version/2/"
                        }
                      }
                    ]
                  },
                  {
                    "location_area": {
                      "name": "power-plant-area",
                      "url": "https://pokeapi.co/api/v2/location-area/330/"
                    },
                    "version_details": [
                      {
                        "version": {
                          "name": "red",
                          "url": "https://pokeapi.co/api/v2/version/1/"
                        }
                      },
                      {
                        "version": {
                          "name": "leafgreen",
                          "url": "https://pokeapi.co/api/v2/version/11/"
                        }
                      }
                    ]
                  },
                  {
                    "location_area": {
                      "name": "hoenn-safari-zone-sw",
                      "url": "https://pokeapi.co/api/v2/location-area/431/"
                    },
                    "version_details": [
                      {
                        "version": {
                          "name": "ruby",
                          "url": "https://pokeapi.co/api/v2/version/7/"
                        }
                      }
                    ]
                  }
                ]""");

        List<String> locations = pokeClient.getLocations(25);

        ArgumentCaptor<Request> argumentCaptor = ArgumentCaptor.forClass(Request.class);
        assertThat(locations).containsExactly("viridian-forest-area", "power-plant-area");
        verify(okHttpClient).newCall(argumentCaptor.capture());
        Request request = argumentCaptor.getValue();
        assertThat(request.url().toString()).isEqualTo("https://example.com/pokemon/25/encounters");
        assertThat(request.header("Authorization")).isEqualTo(DUMMY_API_KEY);
    }

    @Test
    void testGetLocationsFails() throws IOException {
        given(okHttpClient.newCall(any())).willReturn(call);
        given(call.execute()).willReturn(response);
        given(response.isSuccessful()).willReturn(false);

        List<String> locations = pokeClient.getLocations(25);

        assertThat(locations).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 1009, 2000})
    void testGetLocationsInvalidId(int id) {
        PokeClient pokeClient = new PokeClient(new PokeConfig(null, null), null);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> pokeClient.getLocations(id))
                .withMessage("id '%s' is not between 1 and 1008.".formatted(id));
    }
}
