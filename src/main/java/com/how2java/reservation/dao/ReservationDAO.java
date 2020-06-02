package com.how2java.reservation.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.how2java.reservation.pojo.Reservation;
public interface ReservationDAO extends JpaRepository<Reservation,Integer>{

}
