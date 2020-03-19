package api.servlet.servicebill;

import api.model.entity.ServiceBill;
import api.service.ServiceBillService;
import api.service.impl.ServiceBillServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SearchServiceBillByTimeServlet extends AbstractServlet {

    private ServiceBillService serviceBillService = new ServiceBillServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String startTime = request.getParameter("start_time");
            String endTime = request.getParameter("end_time");
            List<ServiceBill> serviceBills = serviceBillService.findByTime(startTime, endTime);
            response.getWriter().print(toResponse(serviceBills));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }
}
