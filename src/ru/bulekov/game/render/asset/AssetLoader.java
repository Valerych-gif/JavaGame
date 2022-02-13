package ru.bulekov.game.render.asset;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;

public class AssetLoader {

    public static ImagesPack loadImagesPack(String file) {
        ImagesPack imagesPack = null;
        try {
            Gson gson = new Gson();
            JsonReader jsonReader = gson.newJsonReader(new FileReader(file));
            jsonReader.beginObject();
            imagesPack = gson.fromJson(new FileReader(file), ImagesPack.class);
        } catch (IOException e){
            System.out.println(e);
            System.exit(1);
        }
        return imagesPack;
    }
}
