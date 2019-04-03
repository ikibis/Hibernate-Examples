package ru.kibis.itemlist.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.kibis.itemlist.model.Item;
import ru.kibis.itemlist.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ItemServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //JSONArray array = new JSONArray();
        List<Item> items = validateService.findItems();
/*        for (Item item : items) {
            JSONObject json = new JSONObject();
            json.put("id", item.getId());
            json.put("name", item.getName());
            json.put("desc", item.getDesc());
            json.put("created", item.getCreated());
            json.put("status", item.isDone());
            array.add(json);
        }*/
        String jsonInString = mapper.writeValueAsString(items);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}
