package api.servlet.bookingBill;

import api.model.entity.Account;
import api.model.entity.BookingBill;
import api.service.BookingBillService;
import api.service.impl.BookingBillServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(name = "SearchAndCreateBookingBillServlet")
public class SearchAndCreateBookingBillServlet extends AbstractServlet {

    private BookingBillService bookingBillService = new BookingBillServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BookingBill bookingBill = gson.fromJson(readRequestBody(request), BookingBill.class);
            response.setContentType("text/html;charset=UTF-8");
            Optional<BookingBill> opt = bookingBillService.create(bookingBill);
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
            String timeIn = request.getParameter("time_in");
            String status = request.getParameter("status");
            String pitchName = request.getParameter("pitch_name");
            String staffUserName = request.getParameter("staff_username");
            String customerUsername = request.getParameter("customer_username");
            ArrayList<BookingBill> bookingBills = (ArrayList<BookingBill>) bookingBillService.search(timeIn,status,pitchName,staffUserName,customerUsername);
            response.getWriter().print(toResponse(bookingBills));
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }
}
