package ru.kibis.itemlist.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Item {
    private int id;
    private String name;
    private String desc;
    private Timestamp created;
    private boolean done;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                name.equals(item.name) &&
                desc.equals(item.desc) &&
                created.equals(item.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created);
    }
}
