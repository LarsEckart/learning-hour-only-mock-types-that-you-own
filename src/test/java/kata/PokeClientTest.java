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
        given(response.body()).willReturn(responseBody);
        given(responseBody.string()).willReturn("""
                {
                  "id": 25,
                  "name": "pikachu"
                }""");
        PokeClient pokeClient = new PokeClient("http://whatever", okHttpClient);

        String actual = pokeClient.getName(25);

        assertThat(actual).isEqualTo("pikachu");
    }

    @Test
    void testGetLocations() throws IOException {
        given(okHttpClient.newCall(any())).willReturn(call);
        given(call.execute()).willReturn(response);
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
                        "encounter_details": [
                          {
                            "chance": 4,
                            "condition_values": [],
                            "max_level": 3,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 3
                          },
                          {
                            "chance": 1,
                            "condition_values": [],
                            "max_level": 5,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 5
                          }
                        ],
                        "max_chance": 5,
                        "version": {
                          "name": "red",
                          "url": "https://pokeapi.co/api/v2/version/1/"
                        }
                      },
                      {
                        "encounter_details": [
                          {
                            "chance": 4,
                            "condition_values": [],
                            "max_level": 3,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 3
                          },
                          {
                            "chance": 1,
                            "condition_values": [],
                            "max_level": 5,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 5
                          }
                        ],
                        "max_chance": 5,
                        "version": {
                          "name": "blue",
                          "url": "https://pokeapi.co/api/v2/version/2/"
                        }
                      },
                      {
                        "encounter_details": [
                          {
                            "chance": 4,
                            "condition_values": [],
                            "max_level": 3,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 3
                          },
                          {
                            "chance": 1,
                            "condition_values": [],
                            "max_level": 5,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 5
                          }
                        ],
                        "max_chance": 5,
                        "version": {
                          "name": "firered",
                          "url": "https://pokeapi.co/api/v2/version/10/"
                        }
                      },
                      {
                        "encounter_details": [
                          {
                            "chance": 4,
                            "condition_values": [],
                            "max_level": 3,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 3
                          },
                          {
                            "chance": 1,
                            "condition_values": [],
                            "max_level": 5,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 5
                          }
                        ],
                        "max_chance": 5,
                        "version": {
                          "name": "leafgreen",
                          "url": "https://pokeapi.co/api/v2/version/11/"
                        }
                      },
                      {
                        "encounter_details": [
                          {
                            "chance": 4,
                            "condition_values": [],
                            "max_level": 4,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 4
                          },
                          {
                            "chance": 1,
                            "condition_values": [
                              {
                                "name": "time-morning",
                                "url": "https://pokeapi.co/api/v2/encounter-condition-value/3/"
                              }
                            ],
                            "max_level": 7,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 7
                          },
                          {
                            "chance": 1,
                            "condition_values": [
                              {
                                "name": "time-day",
                                "url": "https://pokeapi.co/api/v2/encounter-condition-value/4/"
                              }
                            ],
                            "max_level": 7,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 7
                          },
                          {
                            "chance": 1,
                            "condition_values": [],
                            "max_level": 4,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 4
                          }
                        ],
                        "max_chance": 7,
                        "version": {
                          "name": "heartgold",
                          "url": "https://pokeapi.co/api/v2/version/15/"
                        }
                      },
                      {
                        "encounter_details": [
                          {
                            "chance": 4,
                            "condition_values": [],
                            "max_level": 4,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 4
                          },
                          {
                            "chance": 1,
                            "condition_values": [
                              {
                                "name": "time-morning",
                                "url": "https://pokeapi.co/api/v2/encounter-condition-value/3/"
                              }
                            ],
                            "max_level": 7,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 7
                          },
                          {
                            "chance": 1,
                            "condition_values": [
                              {
                                "name": "time-day",
                                "url": "https://pokeapi.co/api/v2/encounter-condition-value/4/"
                              }
                            ],
                            "max_level": 7,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 7
                          },
                          {
                            "chance": 1,
                            "condition_values": [],
                            "max_level": 4,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 4
                          }
                        ],
                        "max_chance": 7,
                        "version": {
                          "name": "soulsilver",
                          "url": "https://pokeapi.co/api/v2/version/16/"
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
                        "encounter_details": [
                          {
                            "chance": 15,
                            "condition_values": [],
                            "max_level": 20,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 20
                          },
                          {
                            "chance": 10,
                            "condition_values": [],
                            "max_level": 24,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 24
                          }
                        ],
                        "max_chance": 25,
                        "version": {
                          "name": "red",
                          "url": "https://pokeapi.co/api/v2/version/1/"
                        }
                      },
                      {
                        "encounter_details": [
                          {
                            "chance": 15,
                            "condition_values": [],
                            "max_level": 20,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 20
                          },
                          {
                            "chance": 10,
                            "condition_values": [],
                            "max_level": 24,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 24
                          }
                        ],
                        "max_chance": 25,
                        "version": {
                          "name": "blue",
                          "url": "https://pokeapi.co/api/v2/version/2/"
                        }
                      },
                      {
                        "encounter_details": [
                          {
                            "chance": 10,
                            "condition_values": [],
                            "max_level": 22,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 22
                          },
                          {
                            "chance": 10,
                            "condition_values": [],
                            "max_level": 24,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 24
                          },
                          {
                            "chance": 4,
                            "condition_values": [],
                            "max_level": 26,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 26
                          },
                          {
                            "chance": 1,
                            "condition_values": [],
                            "max_level": 26,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 26
                          }
                        ],
                        "max_chance": 25,
                        "version": {
                          "name": "firered",
                          "url": "https://pokeapi.co/api/v2/version/10/"
                        }
                      },
                      {
                        "encounter_details": [
                          {
                            "chance": 10,
                            "condition_values": [],
                            "max_level": 22,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 22
                          },
                          {
                            "chance": 10,
                            "condition_values": [],
                            "max_level": 24,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 24
                          },
                          {
                            "chance": 4,
                            "condition_values": [],
                            "max_level": 26,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 26
                          },
                          {
                            "chance": 1,
                            "condition_values": [],
                            "max_level": 26,
                            "method": {
                              "name": "walk",
                              "url": "https://pokeapi.co/api/v2/encounter-method/1/"
                            },
                            "min_level": 26
                          }
                        ],
                        "max_chance": 25,
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
        PokeClient pokeClient = new PokeClient("http://whatever", okHttpClient);

        List<String> locations = pokeClient.getLocations(25);

        assertThat(locations).hasSize(2);
        assertThat(locations).contains("viridian-forest-area", "power-plant-area");
    }
}
