package pe.edu.upc.petnet2.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 3/05/17.
 */

public class Result {
    private String title;
    private String content;
    private String name;

    public String getTitle() {
        return title;
    }

    public Result setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Result setContent(String content) {
        this.content = content;
        return this;
    }

    public String getName() {
        return name;
    }

    public Result setName(String name) {
        this.name = name;
        return this;
    }

    public static Result build(JSONObject jsonResult){
        Result result = new Result();
        try {
            result.setTitle(jsonResult.getString("title"));
            result.setContent(jsonResult.getString("content"));
            result.setName(jsonResult.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static List<Result> build(JSONArray jsonArray){
        List<Result> results = new ArrayList<>();
        int length = jsonArray.length();
        for(int i = 0;i < length; i++){
            try {
                results.add(Result.build(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;

    }
}
