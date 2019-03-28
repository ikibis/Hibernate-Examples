package ru.kibis.itemlist.persistence;

import ru.kibis.itemlist.model.Item;

import java.sql.Timestamp;
import java.util.List;

public class DbStore {
    private static final DbStore INSTANCE = new DbStore();
    private static final StorageWrapper WRAPPER = StorageWrapper.getINSTANCE();

    public static DbStore getInstance() {
        return INSTANCE;
    }

    public Item addItem(String name, String desc) {
        return WRAPPER.tx(session -> {
            Item item = new Item();
            item.setName(name);
            item.setDesc(desc);
            item.setCreated(new Timestamp(System.currentTimeMillis()));
            item.setDone(false);
            session.saveOrUpdate(item);
            return item;
        });
    }

    public List<Item> findItems() {
        return WRAPPER.tx(
                session -> session.createQuery("from Item").list()
        );
    }

    public Item doneItem(int id) {
        return WRAPPER.tx(session -> {
            Item result = findItems()
                    .stream()
                    .filter(item -> item.getId() == id)
                    .findFirst()
                    .map(item -> {
                        item.setDone(true);
                        return item;
                    })
                    .orElse(null);
            session.saveOrUpdate(result);
            return result;
        });
    }


}