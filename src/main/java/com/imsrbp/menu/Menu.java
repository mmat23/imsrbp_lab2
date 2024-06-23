package com.imsrbp.menu;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imsrbp.entity.Motorbike;
import com.imsrbp.service.MotorbikeService;
import com.imsrbp.service.serviceImpl.MotorbikeServiceImpl;

public class Menu {
    public static Scanner in = new Scanner(System.in);
    public static MotorbikeService motorbikeService = new MotorbikeServiceImpl();

    public void index() {
        String choice = "0";
        while (Integer.parseInt(choice) != 6) {
            String s = "Меню пользователя\n" +
                    "1. Добавить мотоцикл\n" +
                    "2. Просмотреть все мотоциклы\n" +
                    "3. Загрузить модели мотоцикла из файла mein_motorbikes.txt\n" +
                    "4. Найти мотоцикл по модели\n" +
                    "5. Редактировать данные о мотоцикле\n" +
                    "6. Удалить мотоцикл\n" +
                    "7. Выход\n" +
                    "Выбор: ";
            System.out.print(s);
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    addMotorbike();
                    break;
                case "2":
                    showMotorbikes();
                    break;
                case "3":
                    rewriteMotorbikes();
                    break;
                case "4":
                    findMotorbikeByModel();
                    break;
                case "5":
                    editMotorbike();
                    break;
                case "6":
                    deleteMotorbike();
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Проверьте корректность ввода!");
                    choice = "0";
                    break;
            }
        }
    }

    public void showMotorbikes(){
        System.out.format("%10s|%15s|%15s|%10s\n", "Модель", "Бренд", "Тип двигателя", "Стоимость");
        List<Motorbike> motorbikes = motorbikeService.showMotorbikes();
        for (Motorbike motor : motorbikes) {
            System.out.format("%10s|%15s|%15s|%10d\n", motor.getModel(), motor.getBrand(),motor.getEngineType(), motor.getCost());
        }
    }

    public Motorbike findMotorbikeByModel(){
        System.out.print("Введите модель мотоцикла: ");
        String model = in.nextLine();
        Motorbike motorbike = motorbikeService.findMotorbikeByModel(model);
        if (motorbike != null) {
            System.out.println(motorbike);
        }
        else {
            System.out.println("Такого мотоцикла нет!");
        }
        return motorbike;
    }

    public void deleteMotorbike() {
        System.out.println("---Удаление мотоцикла---");
        showMotorbikes();
        System.out.print("Введите модель мотоцикла: ");
        String model = in.nextLine();
        Motorbike motorbike = motorbikeService.findMotorbikeByModel(model);
        if (motorbike != null) {
            if (motorbikeService.deleteMotorbike(motorbike.getMotorbikeId())) {
                System.out.println("---Удаление выполнено!---");
            }
        }
    }

    public void rewriteMotorbikes() {
        System.out.println("---Перезапись мотоциклов---");
        
        List<Motorbike> motorbikes = new ArrayList<Motorbike>();
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Motorbike>>() {}.getType();
            motorbikes = gson.fromJson(Files.readString(Path.of("mein_motorbikes.json")), listType);
        } catch (Exception e) {
            System.out.println("---При чтении файла возникла ошибка !!---");
            return;
        }
        
        System.out.println("---Удаление всех мотоциклов---");
        motorbikeService.deleteAllMotorbikes();

        System.out.println("---Запись всех мотоциклов---");
        
        for (Motorbike mb : motorbikes) {
            motorbikeService.addMotorbike(mb);
        }

        System.out.println("---Перезапись выполнена успешно!---");
    }

    public Motorbike editMotorbike() {
        System.out.println("---Изменение мотоцикла---");
        showMotorbikes();
        System.out.print("Выберите модель мотоцикла для изменения: ");
        String model = in.nextLine();
        Motorbike motorbike = motorbikeService.findMotorbikeByModel(model);
        if (motorbike != null) {
            changeDataFromMotorbike(motorbike);
            if (motorbikeService.updateMotorbike(motorbike)) {
                System.out.println("---Изменение выполнено!---");
            }
        }
        else {
            System.out.println("Такого мотоцикла нет!");
        }
        return motorbike;
    }

    public Motorbike changeDataFromMotorbike(Motorbike motorbike) {
        System.out.print("Введите модель мотоцикла: ");
        String model = in.nextLine();

        System.out.print("Введите бренд мотоцикла: ");
        String brand = in.nextLine();
        
        System.out.print("Введите тип двигателя (gas, patrol, electro, disel): ");
        String engineType = in.nextLine();

        System.out.print("Введите стоимость мотоцикла в $ (только целые числа): ");
        Long cost = Long.parseLong(in.nextLine());
        
        motorbike.setModel(model);
        motorbike.setBrand(brand);
        motorbike.setEngineType(engineType);
        motorbike.setCost(cost);
        return motorbike;
    }

    public Motorbike addMotorbike() {
        System.out.print("Введите модель нового мотоцикла: ");
        String model = in.nextLine();

        System.out.print("Введите бренд нового мотоцикла: ");
        String brand = in.nextLine();
        
        System.out.print("Введите тип двигателя (gas, patrol, electro, disel): ");
        String engineType = in.nextLine();

        System.out.print("Введите стоимость нового мотоцикла в $ (только целые числа): ");
        Long cost = Long.parseLong(in.nextLine());
        
        Motorbike motorbike = motorbikeService.findMotorbikeByModel(model);

        if (motorbike != null) {
            System.out.println("---Мотоцикл с такой моделью уже есть!---");
        }
        else {
            motorbike = new Motorbike(model, brand, engineType, cost);
            System.out.println("---Мотоцикл успешно добавлен!---");
        }

        return motorbike;
    }
}
