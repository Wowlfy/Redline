ALTER TABLE reservation
    ADD CONSTRAINT reservation_unique_salle_date_creneau
        UNIQUE (id_salle, date, creneau);