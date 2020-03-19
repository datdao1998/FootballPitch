package web.servlet.staff;

import api.model.entity.Service;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;
import web.servlet.AbstractServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServiceDetailServlet")
public class ServiceDetailServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-user-name") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }
        String serviceId = request.getParameter("serviceId");
        String json = invokeService.get(FPConstant.Url.BASE_URL_API_SERVICE+serviceId, new ArrayList<>());
        FPResponse<Service> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<Service>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("service_detail.jsp");
            request.setAttribute("service-target",fpResponse.getData());
            requestDispatcher.forward(request,response);
            return;
        }
    }
}
