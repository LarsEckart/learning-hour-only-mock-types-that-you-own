package kata;

import okhttp3.OkHttpClient;

import java.io.IOException;

class App {

    public static void main(String[] args) throws IOException {
        PokeClient pokeClient = new PokeClient(new PokeConfig("https://pokeapi.co/api/v2/", "anyApiKey"), new OkHttpClient());

        System.out.println(pokeClient.getPokemonNameAndLocations(25));
    }
}
