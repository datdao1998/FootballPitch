package web.servlet.staff;

import api.model.entity.ServiceBill;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StaffServiceChangeQuantityServlet")
public class StaffServiceChangeQuantityServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }
        if (request.getParameter("staff-service-manage-sell-input-quantity-id") == null) {
            response.sendRedirect("staff_service_manage.jsp");
            return;
        }

        ServiceBill serviceBill = (ServiceBill) session.getAttribute("staff-sell-service-bill");

        Integer serviceId = Integer.parseInt(request.getParameter("staff-service-manage-sell-input-quantity-id"));
        Integer quantity = Integer.parseInt(request.getParameter("staff-service-manage-sell-input-quantity"));
        serviceBill.getUsedServiceList().forEach(usedService -> {
            if (usedService.getService().getId().equals(serviceId))
                usedService.setQuantity(quantity);
        });

        serviceBill.getUsedServiceList().removeIf(usedService -> {
            return usedService.getQuantity() == 0;
        });

        session.setAttribute("staff-sell-service-bill", serviceBill);

        RequestDispatcher dispatcher = request.getRequestDispatcher("staff_manage_service_sell_search");
        dispatcher.forward(request, response);
    }
}
