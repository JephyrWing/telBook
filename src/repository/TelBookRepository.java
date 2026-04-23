package repository;

import dto.TelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TelBookRepository {
    private final Connection conn;

    public TelBookRepository(Connection conn) {
        this.conn = conn;
    }

    public int insertData(TelDto dto) {
        // 1. DB 연결
        PreparedStatement psmt = null;
        // 2. 쿼리 생성
        // 실행 결과를 담을 변수
        int result = 0;
        try {
            //JAVA에서 쿼리 쓸 때 약속 : 함수들은 대문자로 쓴다.
            String sql = "INSERT INTO telbook (name, age, address, phone) VALUES (?,?,?,?)";
            psmt = conn.prepareStatement(sql);
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

    public List<TelDto> findAll() {
        List<TelDto> dtoList = new ArrayList<>();
        // 쿼리 실행 도구
        PreparedStatement psmt = null;
        // 검색 결과 레코드 셋을 담을 통
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM telbook ORDER BY name";
            psmt = conn.prepareStatement(sql);
            // 실행 0> 결과는 rs가 받는다
            rs = psmt.executeQuery();
            // 받은 결과를 DTO list에 차곡차곡 담는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNum(rs.getString("phone"));
                //System.out.println(dto);
                dtoList.add(dto);
            }
            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Find All Error : " + e.getMessage());
        }
        return dtoList;
    }

    public List<TelDto> findById(int id) {
        List<TelDto> dtoList = new ArrayList<>();
        // 쿼리 실행 도구
        PreparedStatement psmt = null;
        // 검색 결과 레코드 셋을 담을 통
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM telbook WHERE id = " + id;
            psmt = conn.prepareStatement(sql);
            // 실행 0> 결과는 rs가 받는다
            rs = psmt.executeQuery();
            // 받은 결과를 DTO list에 차곡차곡 담는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNum(rs.getString("phone"));
                //System.out.println(dto);
                dtoList.add(dto);
            }
            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Find All Error : " + e.getMessage());
        }
        return dtoList;
    }

    public int delete(int id) {
        PreparedStatement psmt = null;
        // 2. 쿼리 생성
        // 실행 결과를 담을 변수
        int result = 0;
        try {
            //JAVA에서 쿼리 쓸 때 약속 : 함수들은 대문자로 쓴다.
            String sql = "DELETE FROM telbook WHERE id = " + id;
            psmt = conn.prepareStatement(sql);
            result = psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            System.out.println("DELETE 오류 : " + e.getMessage());
        }
        return result;
    }

    public int updateData(int id, TelDto dto) {
        PreparedStatement psmt = null;
        // 2. 쿼리 생성
        // 실행 결과를 담을 변수
        int result = 0;
        try {
            //JAVA에서 쿼리 쓸 때 약속 : 함수들은 대문자로 쓴다.
            String sql = """
                    UPDATE telbook \
                    SET name = ?, \
                    age = ?, \
                    address = ?, \
                    phone = ? \
                    WHERE id = ?""";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getTelNum());
            psmt.setInt(5, id);
            //insert, update, delete는 executeUpdate()로 결과를 받는다.
            //Select만 execute()로 결과 받는다.
            result = psmt.executeUpdate();
            psmt.close();
        } catch (Exception e) {
            System.out.println("Update 오류 : " + e.getMessage());
        }
        return result;
    }

    public List<TelDto> findByAddress(String address) {
        List<TelDto> dtoList = new ArrayList<>();
        // 쿼리 실행 도구
        PreparedStatement psmt = null;
        // 검색 결과 레코드 셋을 담을 통
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM telbook WHERE address like ?";
            String likestate = "%" + address + "%";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, likestate);
            // 실행 0> 결과는 rs가 받는다
            rs = psmt.executeQuery();
            // 받은 결과를 DTO list에 차곡차곡 담는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNum(rs.getString("phone"));
                //System.out.println(dto);
                dtoList.add(dto);
            }
            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Find All Error : " + e.getMessage());
        }
        return dtoList;
    }

    public List<TelDto> findByName(String name) {
        List<TelDto> dtoList = new ArrayList<>();
        // 쿼리 실행 도구
        PreparedStatement psmt = null;
        // 검색 결과 레코드 셋을 담을 통
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM telbook WHERE name like ?";
            String likestate = "%"+name+"%";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, likestate);
            //System.out.println(psmt);
            // 실행 0> 결과는 rs가 받는다
            rs = psmt.executeQuery();
            // 받은 결과를 DTO list에 차곡차곡 담는다.
            while (rs.next()) {
                TelDto dto = new TelDto();
                dto.setId(rs.getLong("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setTelNum(rs.getString("phone"));
                //System.out.println(dto);
                dtoList.add(dto);
            }
            psmt.close();
            rs.close();
        } catch (Exception e) {
            System.out.println("Find All Error : " + e.getMessage());
        }
        return dtoList;
    }
}