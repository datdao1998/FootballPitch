package api.servlet.pitch;

import api.model.entity.Pitch;
import api.service.PitchService;
import api.service.impl.PitchServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.constant.FPMessage;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "CreatAndGetPitchServlet")
public class SearchAndCreatePitchServlet extends AbstractServlet {

    private PitchService pitchService = new PitchServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Pitch pitch = gson.fromJson(readRequestBody(request), Pitch.class);
            response.setContentType("text/html;charset=UTF-8");
            Optional<Pitch> opt = pitchService.create(pitch);
            if (!opt.isPresent()) {
                response.getWriter().print(toErrorResponse(FPErrorCode.CREATE_FAIL, FPMessage.CREATE_FAIL));
                return;
            }

            response.getWriter().print(toResponse(opt));
        } catch (FPException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.CREATE_FAIL, e.getMessage()));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String numberOfPlayer = request.getParameter("num_of_player");
            List<Pitch> pitches = pitchService.search(name,description,numberOfPlayer);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(toResponse(pitches));
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().print(toErrorResponse(FPErrorCode.CREATE_FAIL, e.getMessage()));
        }


    }
}
