package web.servlet.customer;

import api.model.entity.Account;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.dto.response.FPResponse;
import web.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CheckUserNameExistedServlet")
public class CheckUserNameExistedServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("user_name") == null) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "sign_up.jsp");
            return;
        }
        String userName = request.getParameter("user_name");
        userName = userName.toLowerCase();
        List<String> params = new ArrayList<>();
        params.add("user_name=" + userName);
        String json = invokeService.get(FPConstant.Url.BASE_URL_API_ACCOUNT, params);
        FPResponse<List<Account>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<Account>>>(){}.getType());
        if (fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            if (fpResponse.getData().size() > 0) {
                response.getWriter().print(FPMessage.USERNAME_EXISTED);
                return;
            }
            else {
                response.getWriter().print(FPMessage.USERNAME_VALID);
            }
        }
        else {
            response.getWriter().print(FPMessage.SERVER_ERROR);
        }
    }

}
