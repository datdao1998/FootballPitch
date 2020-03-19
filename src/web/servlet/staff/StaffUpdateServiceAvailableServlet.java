package web.servlet.staff;

import api.model.entity.Service;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;
import web.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaffUpdateServiceAvailableServlet extends AbstractServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }

        if (request.getParameter("staff-service-manage-detail-id") == null) {
            response.sendRedirect("staff_service_manage_add_available.jsp");
            return;
        }

        Integer id = Integer.parseInt(request.getParameter("staff-service-manage-detail-id"));
        Integer newAvailable = Integer.parseInt(request.getParameter("staff-service-manage-add-available-input-add"));
        Service service = new Service();
        service.setAvailable(newAvailable);
        String url = FPConstant.Url.BASE_URL_API_SERVICE;
        String json = invokeService.put(url,service,id);

        FPResponse<Service> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<Service>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            response.sendRedirect("staff_service_manage_add_available_detail_success.jsp");
        }
    }

}
