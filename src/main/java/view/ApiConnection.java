package view;

import javafx.scene.layout.TilePane;
import model.Internet;
import model.ModelGet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.components.misc.ModelVBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ApiConnection {

    public ArrayList<ModelGet> modelList = new ArrayList<>();

    private ApiConnection(TilePane tilePane) {

        System.out.println("APPEL API");

        String data = null;
        JSONParser jsonParser = new JSONParser();
        JSONObject jsObject = null;

        try {
            data = Internet.sendHttpGETRequest("http://40.113.148.168:3000/3dModel");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            jsObject = (JSONObject) jsonParser.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray dataJs = (JSONArray) jsObject.get("data");
        Iterator<JSONObject> it = dataJs.iterator();
        while (it.hasNext()) {
            JSONObject key = it.next();
            this.modelList.add(new ModelGet((int) Long.parseLong(key.get("id").toString()), (String) key.get("name"), (String) key.get("imglink")));
        }

    }

    private static int i = 0;
    private static ApiConnection data = null;

    public static ApiConnection loadData(TilePane tilePane) {

        if(data == null) {
            data = new ApiConnection(tilePane);
        }

        data.modelList.forEach(x -> {
            tilePane.getChildren().add(new ModelVBox(x));
        });

        return data;
    }

}
