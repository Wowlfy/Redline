package com.groupe2.redline.mappers;

import com.groupe2.redline.dto.ReservationDTO;
import com.groupe2.redline.entities.Reservation;
import com.groupe2.redline.exceptions.CreneauIndisponibleException;
import com.groupe2.redline.repositories.ReservationRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class ReservationMapper {
    private final ReservationRepository reservationRepository;

    public ReservationMapper(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservationFromDto(ReservationDTO reservationDTO) throws CreneauIndisponibleException {
        Reservation newReservation = new Reservation();
        newReservation.setDate(removeTime(reservationDTO.getDate()));
        newReservation.setCreneau(reservationDTO.getCreneau());

        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Example<Reservation> example = Example.of(newReservation, exampleMatcher);
        if (reservationRepository.exists(example)) throw new CreneauIndisponibleException();
        return newReservation;
    }

    private static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
