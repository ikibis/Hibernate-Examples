package ru.kibis.itemlist.controller;

import org.codehaus.jackson.map.ObjectMapper;
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
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(result);
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append(jsonInString);
        writer.flush();
    }
}

