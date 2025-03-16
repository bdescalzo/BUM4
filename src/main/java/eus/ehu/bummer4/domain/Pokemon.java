package eus.ehu.bummer4.domain;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Pokemon {
    String name;
    Integer id;
    Integer weight;
    Integer height;

    public Integer getId() {
        return id;
    }
    public String getTypes() {
        StringBuilder sb = new StringBuilder();
        for (TypeWrapper type : types) {
            sb.append(type.type.name).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()); // Delete the last comma and space

        return sb.toString();
    }
    public String getSprite() {
        return sprites.other.officialArtwork.front_default;
    }

    class TypeWrapper {
        class Type {
            String name;
        }

        Type type;
    }

    public String getName() {
        return name;
    }
    List<TypeWrapper> types;

    class SpriteWrapper {
        String front_default;
        String back_default;
        String front_shiny;
        String back_shiny;


        class Sprite {
            class OfficialArtwork {
                String front_default;
                String front_shiny;

                @Override
                public String toString() {
                    return "OfficialArtwork{" +
                            "front_shiny='" + front_shiny + '\'' +
                            '}';
                }
            }

            @SerializedName("official-artwork")
            OfficialArtwork officialArtwork;
        }
        Sprite other;

        @Override
        public String toString() {
            return "SpriteWrapper{" +
                    "front_default='" + front_default + '\'' +
                    ", back_default='" + back_default + '\'' +
                    ", front_shiny='" + front_shiny + '\'' +
                    ", back_shiny='" + back_shiny + '\'' +
                    ", other=" + other +
                    '}';
        }
    }

    SpriteWrapper sprites;

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", weight=" + weight +
                ", height=" + height +
                ", sprites=" + sprites +
                '}';
    }
}
