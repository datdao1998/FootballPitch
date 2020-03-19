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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StaffAddAvailableServiceDetailServlet")
public class StaffAddAvailableServiceDetailServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }

        if (request.getParameter("staff-service-manage-add-available-id-select") == null) {
            response.sendRedirect("staff_service_manage_add_available.jsp");
            return;
        }

        String url = FPConstant.Url.BASE_URL_API_SERVICE;
        List<String> params = new ArrayList<>();
        String name = request.getParameter("staff-service-manage-add-available-name-search");
        String type = request.getParameter("staff-service-manage-add-available-type-search");
        params.add("name="+name);
        params.add("type="+type);
        String json = invokeService.get(url, params);
        FPResponse<List<Service>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<Service>>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("staff_service_manage_add_available_detail.jsp");
            request.setAttribute("serviceAvailableDetail", fpResponse.getData());
            dispatcher.forward(request, response);
        }
    }
}
