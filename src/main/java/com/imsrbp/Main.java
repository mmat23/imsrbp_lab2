package com.imsrbp;

import com.imsrbp.menu.Menu;
import com.imsrbp.service.MotorbikeService;
import com.imsrbp.service.serviceImpl.MotorbikeServiceImpl;

public class Main {
    public MotorbikeService service = new MotorbikeServiceImpl();
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.index();
    }
}

