package api.servlet.service;

import api.model.entity.Service;
import api.service.ServiceService;
import api.service.impl.ServiceServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class SearchAndCreateServiceServlet extends AbstractServlet {

    private ServiceService serviceService = new ServiceServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Service service = gson.fromJson(readRequestBody(request), Service.class);

            Optional<Service> opt = serviceService.create(service);
            if (!opt.isPresent()) {
                response.getWriter().print(toErrorResponse(FPErrorCode.CREATE_FAIL, FPMessage.CREATE_FAIL));
                return;
            }

            response.getWriter().print(toResponse(opt));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.CREATE_FAIL, e.getMessage()));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            List<Service> services = serviceService.search(name, type);
            response.getWriter().print(toResponse(services));
        } catch (Exception e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }
}
