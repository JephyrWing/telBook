import java.util.Scanner;

public class TelBookMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("시스템을 종료합니다.");
                    return;
            }
        }
    }
}
