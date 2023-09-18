package learninghour.poke;

import learninghour.poke.PokeClient;
import learninghour.poke.PokeConfig;
import okhttp3.OkHttpClient;

import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        PokeClient pokeClient = new PokeClient(new PokeConfig("https://pokeapi.co/api/v2/", "anyApiKey"), new OkHttpClient());

        System.out.println(pokeClient.getName(25));
        System.out.println(pokeClient.getLocations(25));
    }
}
