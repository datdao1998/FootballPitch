package web.servlet.staff;

import api.model.entity.Service;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;
import web.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServiceUpdateServlet")
public class ServiceUpdateServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-user-name") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }
        String id = request.getParameter("updated-service-id");
        String name = request.getParameter("staff-service-detail-input-name");
        String type = request.getParameter("staff-service-detail-input-type");
        String price = request.getParameter("staff-service-detail-input-price");
        Service service = new Service();
        service.setName(name);
        service.setType(type);
        service.setPrice(Double.parseDouble(price));
        String json = invokeService.put(FPConstant.Url.BASE_URL_API_SERVICE,service,Integer.parseInt(id));
        FPResponse<Service> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<Service>>(){}.getType());
        System.out.println(json);
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            response.sendRedirect("add_pitch_success.jsp");
            return;
        }
    }
}
