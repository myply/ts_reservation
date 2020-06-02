package com.how2java.reservation.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.how2java.reservation.dao.AdminDAO;
import com.how2java.reservation.dao.ReservationDAO;
import com.how2java.reservation.dao.TeacherDAO;
import com.how2java.reservation.pojo.Admin;
import com.how2java.reservation.pojo.Reservation;
import com.how2java.reservation.pojo.Teacher;
import com.how2java.reservation.pojo.User;
import com.how2java.reservation.util.Page4Navigator;
@Service
public class ReservationService {
	@Autowired ReservationDAO reservationDAO;
	public Page4Navigator<Reservation> list(int start, int size, int navigatePages) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size,sort);
        Page pageFromJPA =reservationDAO.findAll(pageable);

        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
}
