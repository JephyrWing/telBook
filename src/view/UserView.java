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
    // 검증 클래스 생성
    InputValidation va = new InputValidation();

    public UserView(Scanner sc, TelBookService service) {
        this.sc = sc;
        this.service = service;
    }

    public void insert() throws MyException {
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
        System.out.println("==주소록 업데이트==");
        System.out.println("수정할 ID : ");
        int id = sc.nextInt();
        // 해당 아이디가 존재하는 지 확인
        // 메서드의 리턴타입을 쉽게 얻는 법
        // 단축키 : ctrl + alt + v
        List<TelDto> exists = service.getListOne(id);
        if (exists.isEmpty()) {
            System.out.println("해당 ID가 없습니다.");
            return;
        }
        //ID가 존재하는 경우의 처리
        TelDto oldData = exists.get(0);

        String name = "";
        int age = 0;
        String address = "";
        String telNum = "";

        while (true) {
            try {
                System.out.println("수정 전 이름 : ");
                System.out.println(oldData.getName());
                System.out.println("수정할 이름 : ");
                name = sc.next();
                va.nameCheck(name);
                break;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            try {
                System.out.println("수정 전 나이 : ");
                System.out.println(oldData.getAge());
                System.out.println("수정할 나이 : ");
                age = sc.nextInt();
                va.ageCheck(age);
                break;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("수정 전 주소 : ");
        System.out.println(oldData.getAddress());
        System.out.println("수정할 주소 : ");
        address = sc.next();

        while (true) {
            try {
                System.out.print("수정 전 전화번호 : ");
                System.out.println(oldData.getTelNum());
                System.out.println("수정할 전화번호(XXX-XXXX-XXXX) : ");
                telNum = sc.next();
                va.phoneCheck(telNum);
                break;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
        service.update(id, name, age, address, telNum);
    }

    public void delete() {
        System.out.println("==주소록 삭제==");
        System.out.println("삭제할 ID : ");
        int id = sc.nextInt();
        int result = service.delete(id);
        System.out.println(result == 1?
                "ID: " + id + "삭제 완료!"
                : "삭제 실패 : ID를 확인하세요");
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

    public void search() {
        int selnum = 0;
        List<TelDto> searchlist;
        while (true) {
            try {
                System.out.println("검색할 키워드를 선택하세요. 1. 이름 2. 주소");
                selnum = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
        String keyword = "";
        switch (selnum) {
            case 1 -> {
                System.out.println("검색할 이름 키워드를 입력해 주세요");
                keyword = sc.next();
                searchlist = service.getListByName(keyword);
                if (searchlist.isEmpty()) {
                    System.out.println("검색 결과가 없습니다.");
                    return;
                }
                for (TelDto dto : searchlist) {
                    System.out.println(dto);
                }
            }
            case 2 -> {
                System.out.println("검색할 주소 키워드를 입력해 주세요");
                keyword = sc.next();
                searchlist = service.getListByAddress(keyword);
                if (searchlist.isEmpty()) {
                    System.out.println("검색 결과가 없습니다.");
                    return;
                }
                for (TelDto dto : searchlist) {
                    System.out.println(dto);
                }
            }
            default -> {
                System.out.println("1번과 2번 중에 골라주세요");
                search();
            }
        }
    }
}

