package api.servlet.usedservice;
// Author: anhnv

import api.model.entity.UsedService;
import api.service.UsedServiceService;
import api.service.impl.UsedServiceServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchUsedServiceByTimeServlet extends AbstractServlet {

    private UsedServiceService service = new UsedServiceServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String startTime = request.getParameter("start_time");
            String endTime = request.getParameter("end_time");
            List<UsedService> usedServices = service.findByTime(startTime, endTime);
            response.getWriter().print(toResponse(usedServices));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }
}
