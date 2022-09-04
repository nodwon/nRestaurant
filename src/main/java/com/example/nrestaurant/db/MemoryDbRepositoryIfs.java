package com.example.nrestaurant.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T>{

    Optional<T> findById(int index);
    T save(T entity); //저장
    void deleteById(int index); // 삭제
    List<T> listAll(); // 전체확인
}
