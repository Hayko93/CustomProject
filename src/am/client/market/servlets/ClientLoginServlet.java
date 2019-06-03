package am.client.market.servlets;

import am.client.market.model.Client;
import am.client.market.servlets.util.validator.RequestValidator;
import am.client.market.util.DataValitator;
import am.client.market.util.EncryptionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class ClientLoginServlet extends BaseClientServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestValidator<Client> validator = this.validate(req);
        try {
            if (!validator.isHasError()) {
                Client client = validator.getValue();
                Optional<Client> optionalClient = this.clientService.get(client.getEmail(), client.getPassword());
                if (!optionalClient.isPresent()) {
                    req.setAttribute("wrongEmailPassword", "Wrong email or password");
                } else {
                    client = optionalClient.get();
                    req.getSession().setAttribute("client", client);
                    resp.sendRedirect("homePage");
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("globalError", "");
        }
        req.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(req, resp);
    }


    private RequestValidator<Client> validate(HttpServletRequest request) {
        boolean hasError = false;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (DataValitator.isEmpty(email)) {
            request.setAttribute("errorEmail", "Email is required!");
            hasError = true;
        }
        if (DataValitator.isEmpty(password)) {
            request.setAttribute("passwordError", "Password is required!");
            hasError = true;
        }

        RequestValidator<Client> requestValidator = new RequestValidator<>();
        if (!hasError) {
            Client client = new Client();
            client.setEmail(email);
            client.setPassword(EncryptionUtil.encrypt(password));
            requestValidator.setValue(client);
        } else {
            requestValidator.setHasError(true);
        }
        return requestValidator;
    }
}
