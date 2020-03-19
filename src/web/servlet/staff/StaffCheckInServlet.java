package web.servlet.staff;

import api.model.entity.BookingBill;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaffCheckInServlet extends AbstractServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }
        String url = FPConstant.Url.BASE_URL_API_BOOKING_BILL;
        List<String> params = new ArrayList<>();
        params.add("status="+ FPConstant.BookingStatus.BOOKED);
        String json = invokeService.get(url, params);
        FPResponse<List<BookingBill>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<BookingBill>>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }

        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("staff_check_in.jsp");
            request.setAttribute("pitchesCheckIn", fpResponse.getData());
            dispatcher.forward(request, response);
        }

    }
}
