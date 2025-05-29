package main.java.util;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import main.java.dao.AnalysisDAO;
import main.java.dao.UserDAO;
import main.java.dao.RecommendedRoomDAO;
import main.java.dao.MatchedRoomsDAO;
import main.java.dto.RegionAverageDTO;
import main.java.dto.TopBuildingsDTO;
import main.java.dto.UserDTO;
import main.java.dao.UserPreferenceDAO;
import main.java.dto.UserPreferenceDTO;
import main.java.dto.RoomDTO;
import main.java.dto.OLAPResultDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.List;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/average-rent", new AvgRentHandler());
        server.createContext("/api/top-buildings", new TopBuildingsHandler());
        server.createContext("/api/signup", new SignupHandler());
        server.createContext("/api/login", new LoginHandler());
        server.createContext("/api/preference", new PreferenceHandler());
        server.createContext("/api/recommend-room", new RecommendedRoomHandler());
        server.createContext("/api/matched-rooms", new MatchedRoomsHandler());
        server.createContext("/api/olap-result", new OLAPResultHandler());



        server.setExecutor(null);
        server.start();
        System.out.println("✅ Server running on http://localhost:8080");
    }

    // ✅ CORS 허용 함수 (모든 핸들러에서 호출)
    private static void enableCORS(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
    }

    // ✅ 평균 월세 처리
    static class AvgRentHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            enableCORS(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            List<RegionAverageDTO> data = new AnalysisDAO().getRegionAverageList();
            String responseJson = new Gson().toJson(data);

            byte[] responseBytes = responseJson.getBytes("UTF-8");
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseBytes);
            os.close();
        }
    }

    // ✅ Top 건물 리스트 처리
    static class TopBuildingsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            enableCORS(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            List<TopBuildingsDTO> data = new AnalysisDAO().getTopBuildings();
            String responseJson = new Gson().toJson(data);

            byte[] responseBytes = responseJson.getBytes("UTF-8");
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseBytes);
            os.close();
        }
    }

    // ✅ 회원가입 처리
    static class SignupHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            enableCORS(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                UserDTO user = new Gson().fromJson(isr, UserDTO.class);

                boolean result = new UserDAO().registerUser(user);

                String json = new Gson().toJson(result);
                byte[] response = json.getBytes("utf-8");
                exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
                exchange.sendResponseHeaders(200, response.length);
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }

    // ✅ 로그인 처리
    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // ✅ CORS 설정
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");

            // ✅ OPTIONS 요청에 대한 응답 (CORS preflight)
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1); // No content
                return;
            }

            // ✅ 실제 로그인 처리
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                JsonObject req = new Gson().fromJson(isr, JsonObject.class);

                String loginId = req.get("loginId").getAsString();
                String password = req.get("password").getAsString();

                UserDAO dao = new UserDAO();
                UserDTO user = dao.getUserByCredentials(loginId, password);

                String json;
                if (user != null) {
                    json = new Gson().toJson(user); // {"loginId":"abc", "userName":"홍길동", ...}
                } else {
                    JsonObject fail = new JsonObject();
                    fail.addProperty("error", "Invalid credentials");
                    json = fail.toString();
                }

                byte[] response = json.getBytes("utf-8");
                exchange.sendResponseHeaders(200, response.length);
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }

    
    static class PreferenceHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            enableCORS(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }


            UserPreferenceDAO dao = new UserPreferenceDAO();
            InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            Gson gson = new Gson();
            UserPreferenceDTO pref = gson.fromJson(isr, UserPreferenceDTO.class);

            boolean success = false;

            switch (exchange.getRequestMethod()) {
                case "POST":  // Insert
                    dao.insert(pref);
                    success = true;
                    break;
                case "PUT":   // Update
                    dao.update(pref);
                    success = true;
                    break;
                case "DELETE": // Delete (userId 기준)
                    dao.delete(pref.getUserId());
                    success = true;
                    break;
                default:
                    exchange.sendResponseHeaders(405, -1);
                    return;
            }

            String responseJson = gson.toJson(success);
            byte[] response = responseJson.getBytes("utf-8");
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        }
    }
    
 // ✅ 추천 방 조회 처리
    static class RecommendedRoomHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            enableCORS(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                // 1. 요청 바디에서 userId 받아오기
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                JsonObject req = new Gson().fromJson(isr, JsonObject.class);
                int userId = req.get("userId").getAsInt();

                // 2. 추천 방 조회
                RecommendedRoomDAO dao = new RecommendedRoomDAO();
                RoomDTO recommendedRoom = dao.getRecommendedRoom(userId);

                // 3. 결과 JSON으로 변환
                String json;
                if (recommendedRoom != null) {
                    json = new Gson().toJson(recommendedRoom);
                } else {
                    JsonObject error = new JsonObject();
                    error.addProperty("error", "No room found for this user.");
                    json = error.toString();
                }

                // 4. 응답 전송
                byte[] response = json.getBytes("utf-8");
                exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
                exchange.sendResponseHeaders(200, response.length);
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();

            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }

 // ✅ 유저의 조건에 맞는 여러 개의 매물 조회
    static class MatchedRoomsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            enableCORS(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                // 1. userId 받기
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                JsonObject req = new Gson().fromJson(isr, JsonObject.class);
                int userId = req.get("userId").getAsInt();

                // 2. 매칭된 방 목록 불러오기
                MatchedRoomsDAO dao = new MatchedRoomsDAO();
                List<RoomDTO> matchedRooms = dao.getMatchedRooms(userId);

                // 3. JSON 응답
                String json = new Gson().toJson(matchedRooms);
                byte[] response = json.getBytes("utf-8");

                exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
                exchange.sendResponseHeaders(200, response.length);
                OutputStream os = exchange.getResponseBody();
                os.write(response);
                os.close();
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }
    }
    
 // ✅ OLAP 분석 결과 핸들러
    static class OLAPResultHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            enableCORS(exchange);

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            // DAO에서 결과 가져오기
            List<OLAPResultDTO> data = new AnalysisDAO().getMultiDimensionalRentStats(); // 이 메서드는 DAO에 따로 구현 필요

            // JSON으로 변환
            String responseJson = new Gson().toJson(data);

            byte[] responseBytes = responseJson.getBytes("UTF-8");
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=utf-8");
            exchange.sendResponseHeaders(200, responseBytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(responseBytes);
            os.close();
        }
    }



}
