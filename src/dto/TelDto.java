package dto;

public class TelDto {
    private Long id; // 숫자 뒤에 L 붙여서 입력
    private String name;
    private int age;
    private  String address;
    private  String telNum;

    public TelDto(Long id, String name, int age, String address, String telNum) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.telNum = telNum;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNum() {
        return telNum;
    }
    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    @Override
    public String toString() {
        return "TelDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", telNum='" + telNum + '\'' +
                '}';
    }
}
