package ru.kibis.itemlist.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import ru.kibis.itemlist.model.Item;
import ru.kibis.itemlist.service.ValidateService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddItemServlet extends HttpServlet {
    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String item = req.getParameter("item");
        String desc = req.getParameter("desc");
        Item result = validateService.addItem(item, desc);
        JSONObject json = new JSONObject();
        json.put("id", result.getId());
        json.put("name", result.getName());
        json.put("desc", result.getDesc());
        json.put("created", result.getCreated());
        json.put("status", result.isDone());
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(json);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}

