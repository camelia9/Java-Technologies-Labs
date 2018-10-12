package model;

public class Record {
    private String category, key, name;

    public Record() {
    }

    public Record(String category, String key, String name) {
        this.category = category;
        this.key = key;
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }
}
