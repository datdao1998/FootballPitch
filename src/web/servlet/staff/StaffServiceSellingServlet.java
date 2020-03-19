package web.servlet.staff;

import api.model.entity.ServiceBill;
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

@WebServlet(name = "StaffServiceSellingServlet")
public class StaffServiceSellingServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }
        if (session.getAttribute("staff-sell-service-bill") == null) {
            response.sendRedirect("staff_service_manage.jsp");
            return;
        }

        ServiceBill serviceBill = (ServiceBill) session.getAttribute("staff-sell-service-bill");
        session.removeAttribute("staff-sell-service-bill");
        String json = invokeService.post(FPConstant.Url.BASE_URL_API_SERVICE_BILL, serviceBill);
        FPResponse<ServiceBill> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<ServiceBill>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect("server_error.jsp");
            return;
        }
        else {
            response.sendRedirect("staff_sell_service_success.jsp");
            return;
        }
    }
}
