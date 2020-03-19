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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PitchReportDetailServlet")
public class PitchReportDetailServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String listBookingBillId = request.getParameter("list-booking-bill-id");
        listBookingBillId = listBookingBillId.trim();
        String[] strings = listBookingBillId.split(" ");
        ArrayList<BookingBill> bookingBills = new ArrayList<>();
        for(int i = 0 ; i < strings.length ; i ++){
            String json = invokeService.get(FPConstant.Url.BASE_URL_API_BOOKING_BILL + strings[i], new ArrayList<>());
            FPResponse<BookingBill> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<BookingBill>>(){}.getType());
            if (fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
                bookingBills.add(fpResponse.getData());
            }
            else{
                response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
                return;
            }
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("staff_pitch_report_detail.jsp");
        request.setAttribute("list-booking-bill-matched",bookingBills);
        requestDispatcher.forward(request,response);
        return;
    }
}
