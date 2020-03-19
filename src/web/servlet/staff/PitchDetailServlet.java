package web.servlet.staff;

import api.model.entity.Pitch;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UpdatePitchServlet")
public class PitchDetailServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-user-name") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }
        String pitchId = request.getParameter("pitchId");
        String json = invokeService.get(FPConstant.Url.BASE_URL_API_PITCH+pitchId, new ArrayList<>());
        FPResponse<Pitch> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<Pitch>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("pitch_detail.jsp");
            request.setAttribute("pitch-target",fpResponse.getData());
            requestDispatcher.forward(request,response);
            return;
        }
    }
}
