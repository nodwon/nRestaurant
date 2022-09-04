package com.example.nrestaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{

    private final List<T> db = new ArrayList<>(); //데이터
    private int index =0; //주요키
    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst();
   //있을수도 없을수도 있는데이터를 리턴
    }

    @Override
    public T save(T entity) {
        var OptinalEntity = db.stream().filter(it ->it.getIndex() == entity.getIndex()).findFirst();
        if(OptinalEntity.isEmpty())// Db 에 이미 데이터가 있는경우
        {
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        }else {
            //db에 데이터가 없는경우
            var preIndex = OptinalEntity.get().getIndex(); // 사전에 있던 데이터 가져오기
            entity.setIndex(preIndex);

            deleteById(preIndex);
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it ->it.getIndex() == index).findFirst();
        if(optionalEntity.isPresent()){
            db.remove(optionalEntity.get());
        }
    }

    @Override
    public List<T> listAll() {
        return db;
    }
}
