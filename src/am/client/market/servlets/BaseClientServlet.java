package am.client.market.servlets;

import am.client.market.service.ClientService;
import am.client.market.service.impl.ClientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BaseClientServlet extends HttpServlet {

    protected ClientService clientService;

    public void init() throws ServletException {
        this.clientService = new ClientServiceImpl();
    }

}
