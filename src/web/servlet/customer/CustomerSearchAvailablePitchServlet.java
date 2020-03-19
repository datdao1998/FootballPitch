package web.servlet.customer;

import api.model.entity.Pitch;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;
import common.util.FPUtils;
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

public class CustomerSearchAvailablePitchServlet extends AbstractServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("customer-account-id") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        if (request.getParameter("customer-booking-input-time") == null) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "customer_booking.jsp");
            return;
        }
        String time = request.getParameter("customer-booking-input-time");
        String date = request.getParameter("customer-booking-input-date");
        session.setAttribute("customer-booking-time-in", time + " " + date);
        session.setAttribute("customer-booking-time-out", FPUtils.toTimeOut(time) + " " + date);
        String numOfPlayer = request.getParameter("customer-booking-input-num-player");
        String url = FPConstant.Url.BASE_URL_API_PITCH + "available/";
        List<String> params = new ArrayList<>();
        params.add("num_of_player=" + numOfPlayer);
        params.add("time_in=" + time + " " + date);
        String json = invokeService.get(url, params);

        FPResponse<List<Pitch>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<Pitch>>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("customer_booking.jsp");
            request.setAttribute("pitchsAvailable", fpResponse.getData());
            dispatcher.forward(request, response);
        }
    }
}
