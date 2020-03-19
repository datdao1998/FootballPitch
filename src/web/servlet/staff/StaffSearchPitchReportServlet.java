package web.servlet.staff;

import api.model.entity.BookingBill;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@WebServlet(name = "StaffSearchPitchReportServlet")
public class StaffSearchPitchReportServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateIn = request.getParameter("staff-pitch-report-input-time-in");
        String dateOut = request.getParameter("staff-pitch-report-input-time-out");
        String numOfplayer = request.getParameter("staff-pitch-report-input-num-player");
        ArrayList<String> params = new ArrayList<>();
        params.add("num_of_player=" + numOfplayer);
        String json = invokeService.get(FPConstant.Url.BASE_URL_API_PITCH, params);
        FPResponse<List<Pitch>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<Pitch>>>(){}.getType());
        ArrayList<BookingBill> bookingBills = new ArrayList<>();
        if (fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            for(int i = 0 ; i < fpResponse.getData().size() ; i ++){
                ArrayList<String> bookingBillParams = new ArrayList<>();
                bookingBillParams.add("pitch_name="+fpResponse.getData().get(i).getName());
                bookingBillParams.add("status="+FPConstant.BookingStatus.CHECKED_OUT);
                String bookingBillJson = invokeService.get(FPConstant.Url.BASE_URL_API_BOOKING_BILL, bookingBillParams);
                FPResponse<List<BookingBill>> bookingBillFpResponse = gson.fromJson(bookingBillJson, new TypeToken<FPResponse<List<BookingBill>>>(){}.getType());
                if(bookingBillFpResponse.getCode().equals(FPErrorCode.SUCCESS)){
                    bookingBills.addAll(bookingBillFpResponse.getData());
                }
                else{
                    response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
                    return;
                }
            }
        }
        else {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        bookingBills.removeIf(bookingBill -> {
            try {
                String i = bookingBill.getTimeOut().substring(9,19);
                if(FPUtils.checkTimeIsIn(i,dateIn,dateOut)){
                    return false;
                }
                else {
                    return true;
                }
            } catch (ParseException e) {
                return true;
            }
        });
        Set<Pitch> listPitch = new LinkedHashSet<>();
        for(BookingBill i : bookingBills){
            listPitch.add(i.getPitch());
        }
        ArrayList<Pitch> pitches = new ArrayList<>();
        pitches.addAll(listPitch);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("staff_home_report_pitch.jsp");
        request.setAttribute("list-booking-bill",bookingBills);
        request.setAttribute("set-pitch",pitches);
        requestDispatcher.forward(request,response);
        return;
    }
}
