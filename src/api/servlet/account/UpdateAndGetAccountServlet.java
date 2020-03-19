package api.servlet.account;

import api.model.entity.Account;
import api.service.AccountService;
import api.service.impl.AccountServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UpdateAndGetAccountServlet extends AbstractServlet {

    private AccountService service = new AccountServiceImpl();

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Account account = gson.fromJson(readRequestBody(request), Account.class);
            Integer id = Integer.parseInt(getPath(request));
            Optional<Account> opt = service.update(id, account);
            if (!opt.isPresent()) {
                response.getWriter().print(toErrorResponse(FPErrorCode.UPDATE_FAIL, FPMessage.UPDATE_FAIL));
                return;
            }
            Account account1 = opt.get();
            account1.setPassword(null);
            response.getWriter().print(toResponse(account1));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.UPDATE_FAIL, e.getMessage()));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            Integer id = Integer.parseInt(getPath(request));
            Optional<Account> opt = service.findById(id);
            if (!opt.isPresent()) {
                response.getWriter().print(toErrorResponse(FPErrorCode.GET_FAIL, FPMessage.GET_FAIL));
                return;
            }
            response.getWriter().print(toResponse(opt));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.GET_FAIL, e.getMessage()));
        }
    }
}
