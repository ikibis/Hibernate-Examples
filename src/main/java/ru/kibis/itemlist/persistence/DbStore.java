package ru.kibis.itemlist.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.kibis.itemlist.model.Item;

import java.sql.Timestamp;
import java.util.List;

public class DbStore {
    private static final Logger LOGGER = LogManager.getLogger(DbStore.class.getName());
    private static final DbStore INSTANCE = new DbStore();

    public static DbStore getInstance() {
        return INSTANCE;
    }

    public Item addItem(String name, String desc) {
        Item result;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()
        ) {
            session.beginTransaction();
            Item item = new Item();
            item.setName(name);
            item.setDesc(desc);
            item.setCreated(new Timestamp(System.currentTimeMillis()));
            item.setDone(false);
            session.saveOrUpdate(item);
            session.getTransaction().commit();
            result = item;
        } catch (HibernateException e) {
            result = null;
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }

    public List<Item> findItems() {
        List list;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()
        ) {
            list = session.createQuery("from Item").list();
        } catch (HibernateException e) {
            list = null;
            LOGGER.error(e.getMessage(), e);
        }
        return list;
    }

    public Item doneItem(int id) {
        Item result = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory();
             Session session = factory.openSession()
        ) {
            for (Item item : this.findItems()) {
                if (item.getId() == id) {
                    result = item;
                    break;
                }
            }
            session.beginTransaction();
            result.setDone(true);
            session.saveOrUpdate(result);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            result = null;
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }
}