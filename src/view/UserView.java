package view;

import dto.TelDto;
import exception.InputValidation;
import exception.MyException;
import service.TelBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private final Scanner sc;
    private final TelBookService service;

    public UserView(Scanner sc, TelBookService service) {
        this.sc = sc;
        this.service = service;
    }

    public void insert() throws MyException {
        // 검증 클래스 생성
        InputValidation va = new InputValidation();
        System.out.println("==주소록 등록==");
        String name = "";
        int age = 0;
        String address = "";
        String telNum = "";

        while (true) {
            try {
                System.out.println("이름 : ");
                name = sc.next();
                va.nameCheck(name);
                break;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.println("나이 : ");
                age = sc.nextInt();
                va.ageCheck(age);
                break;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("주소 : ");
        address = sc.next();

        while (true) {
            try {
                System.out.println("전화번호(XXX-XXXX-XXXX) : ");
                telNum = sc.next();
                va.phoneCheck(telNum);
                break;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
        service.insert(name, age, address, telNum);


    }

    public void update() {
        System.out.println("==주소록 등록==");
    }

    public void delete() {
        System.out.println("==주소록 삭제==");
    }

    public void searchAll() {
        System.out.println("==주소록 불러오기==");
        List<TelDto> list = new ArrayList<>();
        list = service.getListAll();
        //리스트가 비어있는지 확인
        if (list.isEmpty()) {
            System.out.println("주소록이 비어있습니다.");
            return;
        }
        for (TelDto dto : list) {
            System.out.println(dto);
        }
        //stream을 이용해서 출력
        //list.forEach(x -> System.out.println(x));
    }

    public void searchByID() {
        System.out.println("==ID로 불러오기==");
        System.out.println("검색할 주소록 ID를 입력해주세요.");
        int id = sc.nextInt();
        List<TelDto> list = service.getListOne(id);
        if (list.isEmpty()) {
            System.out.println("해당 ID가 없습니다.");
        } else {
            list.forEach(x -> System.out.println(x));
        }
    }
}

