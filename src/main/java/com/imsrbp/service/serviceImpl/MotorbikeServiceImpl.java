package com.imsrbp.service.serviceImpl;

import com.imsrbp.dao.MotorbikeDao;
import com.imsrbp.dao.daoImpl.MotorbikeDaoImpl;

// import exception.ShowException;
import org.hibernate.HibernateError;

import com.imsrbp.entity.Motorbike;

import com.imsrbp.service.MotorbikeService;

import java.util.List;

public class MotorbikeServiceImpl implements MotorbikeService {

    MotorbikeDao motorbikeDao = new MotorbikeDaoImpl();

    public MotorbikeServiceImpl() {}

    @Override
    public boolean addMotorbike(Motorbike motorbike) {
        boolean isAdded = false;
        try {
            if (motorbikeDao.addMotorbike(motorbike))
                isAdded = true;
        }
        catch (HibernateError e) {
            // ShowException.showNotice(e);
        }
        return isAdded;
    }

    @Override
    public boolean deleteMotorbike(Long id) {
        boolean isDeleted = false;
        try {
            if (motorbikeDao.deleteMotorbike(id))
                isDeleted = true;
        }
        catch (HibernateError e) {
            // ShowException.showNotice(e);
        }
        return isDeleted;
    }

    @Override
    public List<Motorbike> showMotorbikes() {
        List<Motorbike> motorbike = null;
        try {
            motorbike = motorbikeDao.showMotorbikes();
        }
        catch (HibernateError e) {
            // ShowException.showNotice(e);
        }
        return motorbike;
    }

    @Override
    public Motorbike findMotorbikeById(Long id) {
        Motorbike motorbike = null;
        try {
            motorbike = motorbikeDao.findMotorbikeById(id);
        }
        catch (HibernateError e) {
            // ShowException.showNotice(e);
        }
        return motorbike;
    }

    @Override
    public Motorbike findMotorbikeByModel(String model) {
        Motorbike motorbike = null;
        try {
            motorbike = motorbikeDao.findMotorbikeByModel(model);
        }
        catch (HibernateError e) {
            // ShowException.showNotice(e);
        }
        return motorbike;
    }

    @Override
    public void deleteAllMotorbikes() {
        try {
            motorbikeDao.deleteAllMotorbikes();
        }
        catch (HibernateError e) {
            // ShowException.showNotice(e);
        }
    }

    @Override
    public boolean updateMotorbike(Motorbike motorbike) {
        boolean isUpdated = false;
        try {
            if (motorbikeDao.updateMotorbike(motorbike))
                isUpdated = true;
        }
        catch (HibernateError e) {
            // ShowException.showNotice(e);
        }
        return isUpdated;
    }
}
