package kata;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class PokeClientTest {

    @Mock
    private OkHttpClient okHttpClient;
    @Mock
    private Call call;
    @Mock
    private Response response;
    @Mock
    private ResponseBody responseBody;

    @Test
    void testGetName() throws IOException {
        given(okHttpClient.newCall(any())).willReturn(call);
        given(call.execute()).willReturn(response);
        given(response.isSuccessful()).willReturn(true);
        given(response.body()).willReturn(responseBody);
        given(responseBody.string()).willReturn("""
                {
                  "id": 25,
                  "name": "pikachu"
                }""");
        PokeClient pokeClient = new PokeClient(new PokeConfig("http://whatever", "anyApiKey"), okHttpClient);

        String actual = pokeClient.getName(25);

        assertThat(actual).isEqualTo("pikachu");
    }

    @Test
    void testGetLocations() throws IOException {
        given(okHttpClient.newCall(any())).willReturn(call);
        given(call.execute()).willReturn(response);
        given(response.isSuccessful()).willReturn(true);
        given(response.body()).willReturn(responseBody);
        given(responseBody.string()).willReturn("""
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
                      },
                      {
                        "version": {
                          "name": "heartgold",
                          "url": "https://pokeapi.co/api/v2/version/15/"
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
                          "name": "blue",
                          "url": "https://pokeapi.co/api/v2/version/2/"
                        }
                      },
                      {
                        "version": {
                          "name": "firered",
                          "url": "https://pokeapi.co/api/v2/version/10/"
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
                        "encounter_details": [
                          {
                            "chance": 4,
                            "condition_values": [],
                            "max_level": 25,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 25
                          },
                          {
                            "chance": 1,
                            "condition_values": [],
                            "max_level": 27,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 27
                          }
                        ],
                        "max_chance": 5,
                        "version": {
                          "name": "ruby",
                          "url": "https://pokeapi.co/api/v2/version/7/"
                        }
                      },
                      {
                        "encounter_details": [
                          {
                            "chance": 4,
                            "condition_values": [],
                            "max_level": 25,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 25
                          },
                          {
                            "chance": 1,
                            "condition_values": [],
                            "max_level": 27,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 27
                          }
                        ],
                        "max_chance": 5,
                        "version": {
                          "name": "sapphire",
                          "url": "https://pokeapi.co/api/v2/version/8/"
                        }
                      }
                    ]
                  } \s
                ]""");
        PokeClient pokeClient = new PokeClient(new PokeConfig("http://whatever", "anyApiKey"), okHttpClient);

        List<String> locations = pokeClient.getLocations(25);

        assertThat(locations).hasSize(2);
        assertThat(locations).contains("viridian-forest-area", "power-plant-area");
    }
}
