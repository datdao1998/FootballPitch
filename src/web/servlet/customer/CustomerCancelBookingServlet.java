package web.servlet.customer;

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

@WebServlet(name = "UpdateBookingBillStatusServlet")
public class CustomerCancelBookingServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("customer-view-booking-pitch-id") == null) {
            response.sendRedirect("customer_booked.jsp");
            return;
        }
        HttpSession session = request.getSession();
        if (session.getAttribute("customer-account-id") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        Integer bookingId = Integer.parseInt(request.getParameter("customer-view-booking-pitch-id"));
        String url = FPConstant.Url.BASE_URL_API_BOOKING_BILL;
        BookingBill bookingBill = new BookingBill();
        bookingBill.setStatus(FPConstant.BookingStatus.CANCELLED);
        String json = invokeService.put(url, bookingBill, bookingId);

        FPResponse<BookingBill> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<BookingBill>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            response.sendRedirect("customer_cancel_booking_success.jsp");
        }

    }

}
