package web.servlet.customer;

import api.model.entity.Account;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;
import common.util.FPUtils;
import web.servlet.AbstractServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerLoginServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("customer-login-input-user-name") == null) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "customer_login.jsp");
            return;
        }
        String userName = request.getParameter("customer-login-input-user-name");
        userName = userName.toLowerCase();
        String password = request.getParameter("customer-login-input-password");
        password = FPUtils.SHA1Encrypt(password);
        List<String> params = new ArrayList<>();
        params.add("user_name=" + userName);
        String json = invokeService.get(FPConstant.Url.BASE_URL_API_ACCOUNT, params);
        FPResponse<List<Account>> fpResponse = gson.fromJson(json, new TypeToken<FPResponse<List<Account>>>(){}.getType());
        if (fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            if (fpResponse.getData().size() == 0) {
                HttpSession session = request.getSession();
                session.setAttribute("customer-account-authenticate", "User name is not existed");
                response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "customer_login.jsp");
                return;
            }
            if (password.equals(fpResponse.getData().get(0).getPassword()) && fpResponse.getData().get(0).getRole().equals(FPConstant.AccountRole.ACCOUNT_ROLE_CUSTOMER)) {
                HttpSession session = request.getSession();
                session.setAttribute("customer-account-id", fpResponse.getData().get(0).getId());
                session.setAttribute("customer-account-user-name", fpResponse.getData().get(0).getUserName());
                session.setAttribute("customer-account-name", fpResponse.getData().get(0).getName());
                session.setAttribute("customer-account-email", fpResponse.getData().get(0).getEmail());
                session.setAttribute("customer-account-phone", fpResponse.getData().get(0).getPhone());
                session.setAttribute("account-role", fpResponse.getData().get(0).getRole());
                session.removeAttribute("customer-account-authenticate");
                response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "index.jsp");
                return;
            }
            else  {
                HttpSession session = request.getSession();
                session.setAttribute("customer-account-authenticate", "Password is wrong");
                response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "customer_login.jsp");
                return;
            }
        }
        else {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "server_error.jsp");
            return;
        }
    }
}
