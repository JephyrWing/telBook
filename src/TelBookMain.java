import db.DBConnect;
import exception.MyException;
import repository.TelBookRepository;
import service.TelBookService;
import view.UserView;

import java.sql.Connection;
import java.util.Scanner;

public class TelBookMain {
    public static void main(String[] args) throws MyException {
        Connection connection = DBConnect.getConnection();
        TelBookRepository repository = new TelBookRepository();
        TelBookService service = new TelBookService(repository);
        Scanner sc = new Scanner(System.in);
        //UserView 인스턴스 생성
        UserView userView = new UserView(sc, service);

        int input;
        while (true) {
            do {
                System.out.println("1.입력 2.수정 3.삭제 4.전체출력 5.ID검색 6.종료");
                System.out.println("▶ 메뉴 입력 : ");
                try {
                    input = sc.nextInt();
                } catch (Exception e) {
                    input = 0;
                }
            } while (input<1 || input >6);
            switch (input) {
                case 1:
                    userView.insert();
                    break;
                case 2:
                    userView.update();
                    break;
                case 3:
                    userView.delete();
                    break;
                case 4:
                    userView.searchAll();
                    break;
                case 5:
                    userView.searchByID();
                    break;
                case 6:
                    System.out.println("시스템을 종료합니다.");
                    return;
            }
        }
    }
}
