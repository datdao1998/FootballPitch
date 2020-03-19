package web.servlet.customer;

import api.model.entity.Account;
import api.model.entity.BookingBill;
import api.model.entity.Pitch;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;
import web.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CustomerConfirmBookingServlet")
public class CustomerConfirmBookingServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("customer-confirm-booking-pitch-id") == null) {
            response.sendRedirect("customer_booking.jsp");
            return;
        }
        HttpSession session = request.getSession();
        if (session.getAttribute("customer-account-id") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Integer pitchId = Integer.parseInt(request.getParameter("customer-confirm-booking-pitch-id"));
        Pitch pitch = new Pitch();
        pitch.setId(pitchId);
        Integer accountId = (Integer) session.getAttribute("customer-account-id");
        Account accountCustomer = new Account();
        accountCustomer.setId(accountId);
        String timeIn = (String) session.getAttribute("customer-booking-time-in");
        String timeOut = (String) session.getAttribute("customer-booking-time-out");
        session.removeAttribute("customer-booking-time-in");
        session.removeAttribute("customer-booking-time-out");
        BookingBill bookingBill = new BookingBill();
        bookingBill.setStatus(FPConstant.BookingStatus.BOOKED);
        bookingBill.setTimeIn(timeIn);
        bookingBill.setTimeOut(timeOut);
        bookingBill.setAccountCustomer(accountCustomer);
        bookingBill.setPitch(pitch);

        String json = invokeService.post(FPConstant.Url.BASE_URL_API_BOOKING_BILL, bookingBill);
        FPResponse<BookingBill> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<BookingBill>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "customer_booking_success.jsp");
            return;
        }
    }

}
