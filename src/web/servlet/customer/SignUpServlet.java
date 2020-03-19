package web.servlet.customer;

import api.model.entity.Account;
import com.google.gson.reflect.TypeToken;
import common.constant.FPConstant;
import common.constant.FPErrorCode;
import common.dto.response.FPResponse;
import common.util.FPUtils;
import web.servlet.AbstractServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends AbstractServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("input-sign-up-name") == null) {
            response.sendRedirect(FPConstant.Url.BASE_WEB_URL + "sign_up.jsp");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("input-sign-up-name");
        String email = request.getParameter("input-sign-up-email");
        String phone = request.getParameter("input-sign-up-phone");
        String userName = request.getParameter("input-sign-up-user-name");
        userName = userName.toLowerCase();
        String password = request.getParameter("input-sign-up-password");
        password = FPUtils.SHA1Encrypt(password);
        Account account = new Account();
        account.setName(name);
        account.setEmail(email);
        account.setPhone(phone);
        account.setUserName(userName);
        account.setPassword(password);
        account.setRole(FPConstant.AccountRole.ACCOUNT_ROLE_CUSTOMER);

        String jsonResponse = invokeService.post(FPConstant.Url.BASE_URL_API_ACCOUNT, account);
        FPResponse<Account> fpResponse = gson.fromJson(jsonResponse, new TypeToken<FPResponse<Account>>(){}.getType());
        if (!fpResponse.getCode().equals(FPErrorCode.SUCCESS)) {
            response.sendRedirect("sign_up_fail.jsp");
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("sign_up_success.jsp");
        request.setAttribute("userNameSignUpSuccess", userName);
        dispatcher.forward(request, response);
    }

}
