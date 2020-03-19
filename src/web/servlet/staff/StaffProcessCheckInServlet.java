package web.servlet.staff;

import api.model.entity.Account;
import api.model.entity.BookingBill;
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

@WebServlet(name = "StaffProcessCheckInServlet")
public class StaffProcessCheckInServlet extends AbstractServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-id") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }

        if (request.getParameter("staff-check-in-booking-pitch-id") == null) {
            response.sendRedirect("staff_check_in.jsp");
            return;
        }
        Integer bookingId = Integer.parseInt(request.getParameter("staff-check-in-booking-pitch-id"));
        Integer accountStaffId = (Integer) session.getAttribute("staff-account-id");
        String url = FPConstant.Url.BASE_URL_API_BOOKING_BILL;
        BookingBill bookingBill = new BookingBill();
        Account account = new Account();
        account.setId(accountStaffId);
        bookingBill.setStatus(FPConstant.BookingStatus.CHECKED_IN);
        bookingBill.setAccountStaff(account);
        String json = invokeService.put(url, bookingBill, bookingId);

        FPResponse<BookingBill> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<BookingBill>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            response.sendRedirect("staff_process_check_in_booking_success.jsp");
        }
    }
}
