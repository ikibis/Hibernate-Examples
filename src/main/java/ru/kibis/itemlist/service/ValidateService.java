package ru.kibis.itemlist.service;

import ru.kibis.itemlist.model.Item;
import ru.kibis.itemlist.persistence.DbStore;

import java.util.List;

public class ValidateService {
    private final DbStore memory = DbStore.getInstance();

    private static class Holder {
        private static final ValidateService INSTANCE = new ValidateService();
    }

    public static ValidateService getInstance() {
        return Holder.INSTANCE;
    }

    public Item addItem(String name, String desc) {
        return memory.addItem(name, desc);
    }

    public List<Item> findItems() {
        return memory.findItems();
    }

    public Item doneItem(int id) {
        return memory.doneItem(id);
    }
}
