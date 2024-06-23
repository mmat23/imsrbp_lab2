package com.imsrbp.dao;

import java.util.List;

import com.imsrbp.entity.Motorbike;

public interface MotorbikeDao {
    boolean addMotorbike(Motorbike motorbike);
    boolean updateMotorbike(Motorbike motorbike);
    boolean deleteMotorbike(Long id);
    List<Motorbike> showMotorbikes();
    Motorbike findMotorbikeById(Long id);
    Motorbike findMotorbikeByModel(String model);
    void deleteAllMotorbikes();
}
