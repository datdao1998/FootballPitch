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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchPitchUpdateServlet")
public class SearchPitchUpdateServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("staff-update-pitch-input-name");
        String description = request.getParameter("staff-update-pitch-input-description");
        String numOfPlayer = request.getParameter("staff-update-pitch-input-num-player");
        List<String> params = new ArrayList<>();
        if(name!=null){
            params.add("name="+name);
        }
        else{
            params.add("name=");
        }
        if(description!=null){
            params.add("description="+description);
        }
        else{
            params.add("description=");
        }
        if(numOfPlayer!=null){
            params.add("num_of_player="+numOfPlayer);
        }
        else{
            params.add("num_of_player=");
        }
        String json = invokeService.get(FPConstant.Url.BASE_URL_API_PITCH,params);
        FPResponse<List<Pitch>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<Pitch>>>(){}.getType());
        if (fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("staff_update_pitch.jsp");
            request.setAttribute("list-pitch", fpResponse.getData());
            requestDispatcher.forward(request, response);
            return;
        }
        else {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
    }
}
