package com.imsrbp.dao.daoImpl;

import com.imsrbp.dao.MotorbikeDao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.imsrbp.entity.Motorbike;

import com.imsrbp.sessionFactory.SessionFactoryImpl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class MotorbikeDaoImpl implements MotorbikeDao {

    @Override
    public boolean addMotorbike(Motorbike motorbike) {
        boolean isAdded = false;
        Transaction tx = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(motorbike);
            tx.commit();
            session.close();
            isAdded = true;
        }
        catch (NoClassDefFoundError e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }

    @Override
    public boolean updateMotorbike(Motorbike motorbike) {
        boolean isUpdated = false;
        Transaction tx = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(motorbike);
            tx.commit();
            session.close();
            isUpdated = true;
        }
        catch (NoClassDefFoundError e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Exception: " + e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteMotorbike(Long id) {
        boolean isDeleted = false;
        Transaction tx = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Motorbike motobike = session.load(Motorbike.class, id);
            tx = session.beginTransaction();
            session.delete(motobike);
            tx.commit();
            session.close();
            isDeleted = true;
        }
        catch (NoClassDefFoundError e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Exception: " + e);
        }
        return isDeleted;
    }

    @Override
    public List<Motorbike> showMotorbikes() {
        List<Motorbike> motobikes = (List<Motorbike>) SessionFactoryImpl.getSessionFactory().openSession().createQuery("FROM Motorbike").list();
        return motobikes;
    }

    @Override
    public Motorbike findMotorbikeById(Long id) {
        Motorbike motobike = null;
        Transaction tx = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Motorbike> cr = cb.createQuery(Motorbike.class);
            Root<Motorbike> root = cr.from(Motorbike.class);
            cr.select(root).where(cb.equal(root.get("motorbikeId"), id));
            Optional<Motorbike> opt = session.createQuery(cr).uniqueResultOptional();
            motobike = opt.isEmpty() ? null : opt.get();
            tx.commit();
            session.close();
        }
        catch (NoClassDefFoundError e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Exception: " + e);
        }
        return motobike;
    }

    @Override
    public Motorbike findMotorbikeByModel(String model) {
        Motorbike motobike = null;
        Transaction tx = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Motorbike> cr = cb.createQuery(Motorbike.class);
            Root<Motorbike> root = cr.from(Motorbike.class);
            cr.select(root).where(cb.equal(root.get("model"), model));
            Optional<Motorbike> opt = session.createQuery(cr).uniqueResultOptional();
            motobike = opt.isEmpty() ? null : opt.get();
            tx.commit();
            session.close();
        }
        catch (NoClassDefFoundError e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Exception: " + e);
        }
        return motobike;
    }

    @Override
    public void deleteAllMotorbikes() {
        Transaction tx = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.createQuery("DELETE FROM Motorbike").executeUpdate();
            tx.commit();
            session.close();
        }
        catch (NoClassDefFoundError e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Exception: " + e);
        }
    }
}
