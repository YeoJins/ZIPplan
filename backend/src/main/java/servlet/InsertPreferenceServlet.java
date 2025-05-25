package servlet;

import dao.UserPreferenceDAO;
import dto.UserPreferenceDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/insertPreference")
public class InsertPreferenceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        int userId = Integer.parseInt(req.getParameter("userId"));
        int depositMin = Integer.parseInt(req.getParameter("depositMin"));
        int depositMax = Integer.parseInt(req.getParameter("depositMax"));
        int rentMin = Integer.parseInt(req.getParameter("rentMin"));
        int rentMax = Integer.parseInt(req.getParameter("rentMax"));
        int regionId = Integer.parseInt(req.getParameter("regionId"));

        UserPreferenceDTO pref = new UserPreferenceDTO();
        pref.setUserId(userId);
        pref.setDepositMin(depositMin);
        pref.setDepositMax(depositMax);
        pref.setRentMin(rentMin);
        pref.setRentMax(rentMax);
        pref.setRegionId(regionId);

        UserPreferenceDAO dao = new UserPreferenceDAO();
        dao.insert(pref);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("{\"message\":\"등록 성공\"}");
    }
}
