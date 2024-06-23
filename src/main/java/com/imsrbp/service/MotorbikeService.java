package com.imsrbp.service;

import java.util.List;

import com.imsrbp.entity.Motorbike;

public interface MotorbikeService {
    boolean addMotorbike(Motorbike motorbike);
    boolean deleteMotorbike(Long id);
    List<Motorbike> showMotorbikes();
    Motorbike findMotorbikeById(Long id);
    Motorbike findMotorbikeByModel(String model);
    void deleteAllMotorbikes();
    boolean updateMotorbike(Motorbike motorbike);
}
