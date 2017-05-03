package pe.edu.upc.petnet2.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eric on 27/04/17.
 */

public class Post {
    private String title;
    private String content;
    private boolean active;

    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Post setContent(String content) {
        this.content = content;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Post setActive(boolean active) {
        this.active = active;
        return this;
    }

    public static Post build(JSONObject jsonPost){
        Post post = new Post();
        try {
            if(jsonPost.getString("active").equals("true")){
                post.setActive(true);
            }else{
                post.setActive(false);
            }
            post.setTitle(jsonPost.getString("title"))
                    .setContent(jsonPost.getString("content"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return post;
    }

}
