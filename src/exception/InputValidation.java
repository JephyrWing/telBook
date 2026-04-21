package exception;

import java.util.regex.Pattern;

public class InputValidation {
    //이름 검증 : 한글만 허용(공백 허용 안함)
    public void nameCheck(String name) throws MyException {
        //정규 표현식 Regular Expression
        boolean check = Pattern.matches("^[ㄱ-ㅎ가-힣]*$", name);
        if (!check) {
            throw new MyException("※이름은 한글로 입력해주세요");
        }
    }
    //나이 검증 : 0~120
    public void ageCheck(int age) throws MyException {
        if (age < 0 || age > 120) {
            throw new MyException("※ 나이는 0세부터 120세까지 입니다.");
        }
    }
    //전화번호 검증 - 형식 체크(XXX-XXXX-XXXX)만 입력 가능
    //"-" 반드시 필요
    public void phoneCheck(String phone)throws MyException {
        boolean check = Pattern.matches("^\\d{2,3}-\\d{3,4}-\\d{4}$", phone);
        if (!check) {
            throw new MyException("※서식대로 입력해주세요");
        }
    }
}
