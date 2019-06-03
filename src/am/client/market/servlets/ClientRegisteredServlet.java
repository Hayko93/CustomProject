package am.client.market.servlets;

import am.client.market.model.Client;
import am.client.market.servlets.util.validator.RequestValidator;
import am.client.market.util.DataValitator;
import am.client.market.util.EncryptionUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/register")
public class ClientRegisteredServlet extends BaseClientServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RequestValidator<Client> validator = this.validate(req);
            if (!validator.isHasError()) {
                Client client = validator.getValue();
                boolean clientExist = this.clientService.clientExist(client.getEmail());
                if (!  clientExist){
                    InputStream imageStream = !DataValitator.isEmpty(validator.getFileItemList())
                            ? validator.getFileItemList().get(0).getInputStream() : null;
                    this.clientService.add(client, imageStream);
                    req.getSession().setAttribute("registered", "");
                    resp.sendRedirect("login");
                    return;
                } else {
                    req.setAttribute("clientExist", String.format("User with email = %s " + " already exists!", client.getEmail()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("globalError", "");
        }
        req.getRequestDispatcher("WEB-INF/pages/register.jsp").forward(req, resp);
    }


    private RequestValidator<Client> validate(HttpServletRequest request) throws FileUploadException {
        String name = null;
        String surname = null;
        String email = null;
        String passwrod = null;
        String confirmPassword = null;
        String country = null;

        List<FileItem> fileItems = new ArrayList<>();
        boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
        if (isMultiPart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    switch (item.getFieldName()) {
                        case "name":
                            name = item.getString();
                            break;
                        case "surname":
                            surname = item.getString();
                            break;
                        case "email":
                            email = item.getString();
                            break;
                        case "password":
                            passwrod = item.getString();
                            break;
                        case "confirmPassword":
                            confirmPassword = item.getString();
                        case "country":
                            country = item.getString();
                            break;
                    }
                } else {
                    if (item.getSize() > 0) {
                        fileItems.add(item);
                    }
                }
            }
        } else {
            name = request.getParameter("name");
            surname = request.getParameter("surname");
            email = request.getParameter("email");
            passwrod = request.getParameter("password");
            confirmPassword = request.getParameter("confirmPassword");
            country = request.getParameter("country");
        }
        boolean hasError = false;
        if (DataValitator.isEmpty(name)) {
            request.setAttribute("errorName", "Name is required!");
            hasError = true;
        }
        if (DataValitator.isEmpty(surname)) {
            request.setAttribute("errorSurname", "Surname is required!");
            hasError = true;
        }
        if (DataValitator.isEmpty(email)) {
            request.setAttribute("emailError", "Email is required!");
            hasError = true;
        }else if (!DataValitator.isValidEmail(email)) {
            request.setAttribute("errorEmail", "Wrong email format!");
            hasError = true;
        }
        if (DataValitator.isEmpty(passwrod)) {
            request.setAttribute("errorPassword", "Password is required!");
            hasError = true;
        } else {
            if (DataValitator.isEmpty(confirmPassword) || !passwrod.equals(confirmPassword)) {
                request.setAttribute("errorConfirmPassword", "Password doesn't match!");
                hasError = true;
            }
        }
        if (DataValitator.isEmpty(country)) {
            request.setAttribute("errorCountry", "Country is required");
            hasError = true;
        }
        RequestValidator<Client> requestValidator = new RequestValidator<>();
        if (!hasError) {
            Client client = new Client();
            client.setName(name);
            client.setSurname(surname);
            client.setEmail(email);
            client.setPassword(EncryptionUtil.encrypt(passwrod));
            client.setCountry(country);
            requestValidator.setValue(client);
            requestValidator.setFileItemList(fileItems);
        } else {
            requestValidator.setHasError(true);
        }
        return requestValidator;
    }
}
