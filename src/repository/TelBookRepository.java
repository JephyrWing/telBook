package repository;

import db.DBConnect;
import dto.TelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelBookRepository {
    public int insertData(TelDto dto) {
        // 1. DB 연결
        Connection Conn = DBConnect.getConnection();
        PreparedStatement psmt = null;
        // 2. 쿼리 생성
        // 실행 결과를 담을 변수
        int result = 0;
        try {
            String sql = "INSERT INTO telbook (name, age, address, phone) VALUES (?,?,?,?)";
            psmt = Conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getTelNum());
            //insert, update, delete는 executeUpdate()로 결과를 받는다.
            //Select만 execute()로 결과 받는다.
            result = psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            System.out.println("INSERT 오류 : " + e.getMessage());
        }
        return result;
        // 3. 쿼리 실행
        // 4. 정리
    }
}
