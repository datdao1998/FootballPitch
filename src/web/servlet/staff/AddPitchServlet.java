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

@WebServlet(name = "AddPitchServlet")
public class AddPitchServlet extends AbstractServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("staff-account-user-name") == null) {
            response.sendRedirect("staff_login.jsp");
            return;
        }
        String pitchName = request.getParameter("staff-add-pitch-input-name");
        String pitchDescription = request.getParameter("staff-add-pitch-input-description");
        String typeId = request.getParameter("staff-add-pitch-input-num-player");
        Type type = new Type();
        type.setId(Integer.parseInt(typeId));
        Pitch pitch = new Pitch();
        pitch.setType(type);
        pitch.setName(pitchName);
        pitch.setDescription(pitchDescription);
        String json = invokeService.post(FPConstant.Url.BASE_URL_API_PITCH, pitch);
        FPResponse<Pitch> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<Pitch>>(){}.getType());
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
