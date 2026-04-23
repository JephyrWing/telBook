package service;

import dto.TelDto;
import repository.TelBookRepository;

import java.util.List;

public class TelBookService {
    private final TelBookRepository repository;

    public TelBookService(TelBookRepository repository) {
        this.repository = repository;
    }

    public void insert(String name, int age, String address, String telNum) {
        TelDto dto = new TelDto(0L, name, age, address, telNum);
        int result = repository.insertData(dto);
        if(result > 0) {
            System.out.println("정상적으로 저장되었습니다.");
        }
    }

    public List<TelDto> getListAll() {
        return repository.findAll();
    }

    public List<TelDto> getListOne(int id) {
        return repository.findById(id);
    }

    public int delete(int id) {
        return repository.delete(id);
    }

    public void update(int id, String name, int age, String address, String telNum) {
        TelDto dto = new TelDto((long)id, name, age, address, telNum);
        int result = repository.updateData(id, dto);
        if(result > 0) {
            System.out.println("정상적으로 저장되었습니다.");
        }
    }

    public List<TelDto> getListByName(String name) {
        return repository.findByName(name);
    }

    public List<TelDto> getListByAddress(String address) {
        return repository.findByAddress(address);
    }
}
