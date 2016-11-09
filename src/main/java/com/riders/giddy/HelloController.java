package com.riders.giddy;

import com.riders.giddy.commons.models.SessionRoute;
import com.riders.giddy.commons.persistence.SessionRouteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    SessionRouteRepository repository;

    @RequestMapping("/")
    public String index() {

        /*repository.save(new SessionRoute());
        repository.save(new SessionRoute());
*/


        return String.valueOf(repository.findAll().size());

    }

}
