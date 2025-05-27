package main;

import com.google.gson.Gson;
import dao.UserPreferenceDAO;
import dto.UserPreference;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class InsertPreferenceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // JSON 파싱을 위한 BufferedReader
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();

        // JSON을 UserPreference 객체로 변환
        UserPreference pref = gson.fromJson(reader, UserPreference.class);

        // DB에 insert
        UserPreferenceDAO dao = new UserPreferenceDAO();
        dao.insert(pref);

        response.setContentType("text/plain");
        response.getWriter().write("등록 완료");
    }
}
