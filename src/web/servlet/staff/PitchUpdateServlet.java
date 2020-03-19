package web.servlet.staff;

import api.model.entity.Pitch;
import api.model.entity.Type;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;
import web.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "PitchUpdateServlet")
public class PitchUpdateServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-user-name") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }
        String id = request.getParameter("updated-pitch-id");
        String name = request.getParameter("staff-pitch-detail-input-name");
        String description = request.getParameter("staff-pitch-detail-input-description");
        String typeId = request.getParameter("staff-pitch-detail-input-num-player");
        Pitch pitch = new Pitch();
        pitch.setName(name);
        pitch.setDescription(description);
        Type type = new Type();
        type.setId(Integer.parseInt(typeId));
        pitch.setType(type);
        System.out.println(FPConstant.Url.BASE_URL_API_PITCH+id);
        String json = invokeService.put(FPConstant.Url.BASE_URL_API_PITCH,pitch,Integer.parseInt(id));
        FPResponse<Pitch> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<Pitch>>(){}.getType());
        System.out.println(json);
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
        else {
            response.sendRedirect("add_pitch_success.jsp");
            return;
        }
    }
}
