package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 프로젝트 시작할 때 DB연결을 구성
// 딱 한 번만 연결함.
// 종료 시 연결 해제
// 연결을 static으로 생성
// 이렇게 딱 한 번만 연결하는 것을 Singleton 패턴이라고 한다.

public class DBConnect {
    private static Connection dbConn;

    // DB 연결
    public static Connection getConnection() {
        if (dbConn == null) {
            try {
                String dbDriver = "com.mysql.cj.jdbc.Driver";
                String dbUrl = "jdbc:mysql://localhost:3306/teldb";
                String dbUser = "root";
                String dbPassword = "1111";
                // 드라이버 클래스를 메모리로 가져온다.
                Class.forName(dbDriver);
                //연결 생성
                dbConn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                System.out.println("DB 연결 성공");
            } catch (ClassNotFoundException e) {
                System.out.println("드라이버가 없음");
                e.printStackTrace(); //DB에서 가져온 오류 출력
            } catch (SQLException e) {
                System.out.println("DB 연결 실패");
                e.printStackTrace();
            }
        }
        return dbConn;
    }

    // DB 종료 시 처리
    public static void closeConnection() {
        if (dbConn != null) {
            try {
                if (!dbConn.isClosed()) {
                    dbConn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            dbConn = null;
        }
    }
}
