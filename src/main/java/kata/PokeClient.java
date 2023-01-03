package kata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class PokeClient {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

    private final OkHttpClient okHttpClient;
    private final String baseUrl;
    private final String apiKey;

    PokeClient(PokeConfig pokeConfig, OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        this.baseUrl = pokeConfig.baseUrl();
        this.apiKey = pokeConfig.apiKey();
    }

    public String getName(int id) throws IOException {
        if (id < 1 || 1008 < id) {
            throw new IllegalArgumentException("id '%s' is not between 1 and 1008.".formatted(id));
        }
        Request request = new Request.Builder()
                .get()
                .url(baseUrl + "pokemon/" + id)
                .header("Authorization", apiKey)
                .build();

        try(
    Response response = okHttpClient.newCall(request).execute())

    {
        if (response.isSuccessful()) {
            String rawJson = response.body().string();

            JsonNode jsonNode = objectMapper.readTree(rawJson);
            String name = jsonNode.get("name").asText();
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        } else {
            return "Unknown";
        }
    }

}

    /**
     * Returns all locations where this pokemon appears in the games Pokemon Red and Pokemon Blue.
     */
    public List<String> getLocations(int id) throws IOException {
        if (id < 1 || 1008 < id) {
            throw new IllegalArgumentException("id '%s' is not between 1 and 1008.".formatted(id));
        }
        Request request = new Request.Builder()
                .get()
                .url(baseUrl + "pokemon/" + id + "/encounters")
                .header("Authorization", apiKey)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String rawJson = response.body().string();

                LocationResponse[] array = objectMapper.readValue(rawJson, LocationResponse[].class);
                return Arrays.stream(array)
                        .filter(x -> Arrays.stream(x.version_details()).anyMatch(y -> "red".equals(y.version().name()) || "blue".equals(y.version().name())))
                        .map(r -> r.location_area().name())
                        .toList();
            } else {
                return Collections.emptyList();
            }
        }
    }

}
