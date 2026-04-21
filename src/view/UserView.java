package view;

import exception.InputValidation;
import exception.MyException;

import java.util.Scanner;

public class UserView {
    private final Scanner sc;

    public UserView(Scanner sc) {
        this.sc = sc;
    }

    public void insert() throws MyException {
        // 검증 클래스 생성
        InputValidation va = new InputValidation();
        System.out.println("==주소록 등록==");
        while (true) {
            try {
                System.out.println("이름 : ");
                String name = sc.next();
                va.nameCheck(name);
                break;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.println("나이 : ");
                int age = sc.nextInt();
                va.ageCheck(age);
                break;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("주소 : ");
        String address = sc.next();

        while (true) {
            try {
                System.out.println("전화번호(XXX-XXXX-XXXX) : ");
                String telNum = sc.next();
                va.phoneCheck(telNum);
                break;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }


    }

    public void update() {
        System.out.println("==주소록 등록==");
    }

    public void delete() {
        System.out.println("==전화 번호 삭제==");
    }

    public void searchAll() {
        System.out.println("==전화 번호 등록==");
    }

    public void searchByID() {
        System.out.println("==전화 번호 등록==");
    }
}
