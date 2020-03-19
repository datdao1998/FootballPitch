package api.servlet.pitch;

import api.model.entity.Pitch;
import api.service.PitchService;
import api.service.impl.PitchServiceImpl;
import api.servlet.AbstractServlet;
import common.constant.FPErrorCode;
import common.exception.FPException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SearchAvailablePitchServlet")
public class SearchAvailablePitchServlet extends AbstractServlet {

    PitchService pitchService = new PitchServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numOfPlayer = request.getParameter("num_of_player");
        String timeIn = request.getParameter("time_in");
        ArrayList<Pitch> pitches = null;
        try {
            pitches = (ArrayList<Pitch>) pitchService.searchAvailablePitch(timeIn,numOfPlayer);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().print(toResponse(pitches));
        } catch (FPException.DateFormatException e) {
            response.getWriter().print(toErrorResponse(FPErrorCode.GET_FAIL, e.getMessage()));
        }
    }
}
