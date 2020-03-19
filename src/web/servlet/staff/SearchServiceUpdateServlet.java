package web.servlet.staff;

import api.model.entity.Service;
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

@WebServlet(name = "UpdateServiceServlet")
public class SearchServiceUpdateServlet extends AbstractServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("staff-update-service-input-name");
        String type = request.getParameter("staff-update-service-input-description");
        List<String> params = new ArrayList<>();
        if(name!=null){
            params.add("name="+name);
        }
        else{
            params.add("name=");
        }
        if(type!=null){
            params.add("type="+type);
        }
        else{
            params.add("type=");
        }
        String json = invokeService.get(FPConstant.Url.BASE_URL_API_SERVICE,params);
        FPResponse<List<Service>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<Service>>>(){}.getType());
        if (fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("staff_update_service.jsp");
            request.setAttribute("list-service", fpResponse.getData());
            requestDispatcher.forward(request, response);
            return;
        }
        else {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
    }
}
