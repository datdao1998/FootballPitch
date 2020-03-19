package web.servlet.staff;

import api.model.entity.Account;
import api.model.entity.Service;
import api.model.entity.ServiceBill;
import api.model.entity.UsedService;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StaffAddSellServiceServlet")
public class StaffAddSellServiceServlet extends AbstractServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }
        if (request.getParameter("staff-service-manage-sell-id-select") == null) {
            response.sendRedirect("staff_service_manage_sell.jsp");
            return;
        }

        if (session.getAttribute("staff-sell-service-bill") == null) {
            ServiceBill serviceBill = new ServiceBill();

            Account staffAccount = new Account();
            staffAccount.setId((Integer) session.getAttribute("staff-account-id"));
            serviceBill.setAccount(staffAccount);

            LocalDate todayDate = LocalDate.now();
            String s[] = todayDate.toString().split("-");
            String today = s[2] + "/" + s[1] + "/" + s[0];
            serviceBill.setTime(today);

            serviceBill.setUsedServiceList(new ArrayList<>());

            session.setAttribute("staff-sell-service-bill", serviceBill);
        }

        ServiceBill serviceBill1 = (ServiceBill) session.getAttribute("staff-sell-service-bill");

        Integer serviceId = Integer.parseInt(request.getParameter("staff-service-manage-sell-id-select"));
        String json = invokeService.get(FPConstant.Url.BASE_URL_API_SERVICE + serviceId, new ArrayList<>());
        FPResponse<Service> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<Service>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect("server_error.jsp");
            return;
        }


        Service service = fpResponse.getData();
        UsedService usedService = new UsedService();
        usedService.setTime(serviceBill1.getTime());
        usedService.setService(service);
        usedService.setQuantity(1);

        List<Integer> serviceIds = new ArrayList<>();
        serviceBill1.getUsedServiceList().forEach(usedService1 -> {
            serviceIds.add(usedService1.getService().getId());
        });
        if (!serviceIds.contains(service.getId())) {
            serviceBill1.getUsedServiceList().add(usedService);
        }

        session.setAttribute("staff-sell-service-bill", serviceBill1);

        RequestDispatcher dispatcher = request.getRequestDispatcher("staff_manage_service_sell_search");
        dispatcher.forward(request, response);
    }
}
