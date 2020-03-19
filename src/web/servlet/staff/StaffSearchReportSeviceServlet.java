package web.servlet.staff;

import api.model.entity.ServiceBill;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "staffSearchSeviceServlet")
public class StaffSearchReportSeviceServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDay = request.getParameter("booked-service-input-date");
        String endDay = request.getParameter("booked-service-end-input-date");
        int row = Integer.parseInt(request.getParameter("row"));
        List<String> params = new ArrayList<>();
        params.add("start_time=" + startDay);
        params.add("end_time=" + endDay);
        String json = invokeService.get("http://localhost:8080/football_pitch/api/service_bills/search_by_time", params);
        FPResponse<List<ServiceBill>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<ServiceBill>>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("staff_home_report_service_searched.jsp");
            List<ServiceBill> tmp = fpResponse.getData();
            Collections.sort(tmp,(t1, t2) -> {
                return (int) (t2.getTotalPayment() - t1.getTotalPayment());
            });
            request.setAttribute("servicesBill", tmp);
            request.setAttribute("start_time", startDay);
            request.setAttribute("end_time", endDay);
            if(row >= 0){
                request.setAttribute("listUservice", fpResponse.getData().get(row).getUsedServiceList());
            }
            else {
                request.setAttribute("listUservice",new ArrayList<>());

            }
            dispatcher.forward(request, response);
        }

    }
}
