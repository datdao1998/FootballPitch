package web.servlet.staff;

import api.model.entity.Account;
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
import java.io.IOException;

@WebServlet(name = "StaffSMangeAddServlet")
public class StaffServiceMangeAddServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("input-staff-service-add-service-name");
        String type = request.getParameter("input-staff-service-add-service-type");
        int available = Integer.parseInt(request.getParameter("input-staff-service-add-service-available"));
        String unit = request.getParameter("input-staff-service-add-service-unit");
        double price = Double.parseDouble(request.getParameter("input-staff-service-add-service-price"));
        Service service = new Service();
        service.setName(name);
        service.setType(type);
        service.setAvailable(available);
        service.setUnit(unit);
        service.setPrice(price);
        String jsonResponse = invokeService.post(FPConstant.Url.BASE_URL_API_SERVICE, service);
        System.out.println("done");
        FPResponse<Account> fpResponse = gson.fromJson(jsonResponse, new TypeToken<FPResponse<Account>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect("server_error.jsp");
            return;
        }
        //staff_service_manage_add_success.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("staff_service_manage_add_success.jsp");
        request.setAttribute("nameService", name);
        dispatcher.forward(request, response);
    }

}
