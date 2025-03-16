package eus.ehu.bummer4;

import com.google.gson.Gson;
import eus.ehu.bummer4.domain.PokeList;
import eus.ehu.bummer4.domain.Pokemon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class HelloController {

    int currPok = 0;
    int amount = 0;

    private PokeList pokemons;

    @FXML
    private ImageView icon;

    @FXML
    private TextField pkId;

    @FXML
    private TextField pkName;

    @FXML
    private TextField pkType;

    @FXML
    private Button nextBtn;

    @FXML
    private Button prevBtn;

    @FXML
    void nextPok(ActionEvent event) {
        if (currPok < (amount - 1)) {
            prevBtn.setDisable(false);
            currPok++;
            loadCurrentPokemon();
        }

        // Disable next button if no more pokemons to the right
        if (currPok == amount - 1) {
            nextBtn.setDisable(true);
        }
    }

    @FXML
    void previousPok(ActionEvent event) {
        if (currPok > 0) {
            nextBtn.setDisable(false);
            currPok--;
            loadCurrentPokemon();
        }

        // Disable previous button if no more pokemons to the left
        if (currPok == 0) {
            prevBtn.setDisable(true);
        }
    }

    void loadCurrentPokemon() {
        Gson gson = new Gson();
        String json = null;
        try {
            json = Utils.query(pokemons.getUrl(currPok));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        Pokemon pokemon = gson.fromJson(json, Pokemon.class);

        String url = pokemon.getSprite();
        icon.setImage(new Image(url));
        pkName.setText(pokemon.getName());
        pkType.setText(pokemon.getTypes());
        pkId.setText(pokemon.getId().toString());

    }

    @FXML
    void initialize() {
        Gson gson = new Gson();
        String json = null;

        // At the beginning we are on the leftmost pokemon
        prevBtn.setDisable(true);
        // We get the list of pokemons (amount and name + URL for each)
        try {
            json = Utils.query("https://pokeapi.co/api/v2/pokemon?limit=100000&offset=0");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        pokemons = gson.fromJson(json, PokeList.class);

        amount = pokemons.getCount();

        // Load the first pokemon
        loadCurrentPokemon();
    }





}
