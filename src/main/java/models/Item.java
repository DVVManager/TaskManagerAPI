package models;

/**
 * Created by Administrator on 10/29/2017.
 */
public class Item {
    int user_id;
    int id;
    String title;
    String categorty;
    String short_name;

    public int getUser_id() {
        return user_id;
    }

    public Item setUser_id(int user_id) {
        this.user_id = user_id;
        return this;
    }

    public int getId() {
        return id;
    }

    public Item setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Item setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCategorty() {
        return categorty;
    }

    public Item setCategorty(String categorty) {
        this.categorty = categorty;
        return this;
    }

    public String getShort_name() {
        return short_name;
    }

    public Item setShort_name(String short_name) {
        this.short_name = short_name;
        return this;
    }
}
