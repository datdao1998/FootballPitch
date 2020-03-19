package api.servlet.type;

import api.model.entity.Type;
import api.service.TypeService;
import api.service.impl.TypeServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchTypeServlet extends AbstractServlet {

    private TypeService typeService = new TypeServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String numOfPlayer = request.getParameter("num_of_player");
            List<Type> types = new ArrayList<>();
            if (numOfPlayer == null) types = typeService.search(null);
            else types = typeService.search(Integer.parseInt(numOfPlayer));
            response.getWriter().print(toResponse(types));
        } catch (Exception e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.SEARCH_FAIL, e.getMessage()));
        }
    }
}
