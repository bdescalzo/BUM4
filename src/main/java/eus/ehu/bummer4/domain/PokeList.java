package eus.ehu.bummer4.domain;

import java.util.List;

import static java.lang.Math.min;

public class PokeList {
    private int count;

    class Pokeref {
        String name;
        String url;
    }
    private List<Pokeref> results;

    public int getCount() {
        // "count" parameter in query does not consider the limit passed in the URL
        return min(count, results.size());
    }

    public String getUrl(int index) {
        return results.get(index).url;
    }
}
