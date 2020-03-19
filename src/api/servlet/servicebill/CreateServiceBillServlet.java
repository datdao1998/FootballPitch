package api.servlet.servicebill;

import api.model.entity.ServiceBill;
import api.service.ServiceBillService;
import api.service.impl.ServiceBillServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateServiceBillServlet extends AbstractServlet {

    private ServiceBillService serviceBillService = new ServiceBillServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            ServiceBill serviceBill = gson.fromJson(readRequestBody(request), ServiceBill.class);

            Optional<ServiceBill> opt = serviceBillService.create(serviceBill);
            if (!opt.isPresent()) {
                response.getWriter().print(toErrorResponse(FPErrorCode.CREATE_FAIL, FPMessage.CREATE_FAIL));
                return;
            }

            response.getWriter().print(toResponse(opt));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.CREATE_FAIL, e.getMessage()));
        }
    }

}
