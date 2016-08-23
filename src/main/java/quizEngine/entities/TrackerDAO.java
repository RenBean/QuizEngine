package quizEngine.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ari on 8/17/16.
 */
//created TrackerDAO to keep track of user email crud

@Transactional
public interface TrackerDAO extends CrudRepository<Tracker, Long> {

        /**
         * This method will find an Tracker instance in the database by its email.
         * Note that this method is not implemented and its working code will be
         * automatically generated from its signature by Spring Data JPA.
         */
        Tracker findByEmail(String email);



}