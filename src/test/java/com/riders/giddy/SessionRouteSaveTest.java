package com.riders.giddy;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.riders.giddy.commons.models.Descriptor;
import com.riders.giddy.commons.models.Location;
import com.riders.giddy.commons.models.Path;
import com.riders.giddy.commons.models.SessionRoute;
import com.riders.giddy.commons.persistence.PathRepository;
import com.riders.giddy.commons.persistence.SessionRouteRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessionRouteSaveTest {

    @Autowired
    SessionRouteRepository sessionRouteRepository;

    @Autowired
    PathRepository pathRepository;

    @Autowired
    MongoTemplate template;

    @Before
    public void setUp() {
        template.indexOps(Path.class).ensureIndex(new GeospatialIndex("point"));
    }

    @Test
    public void testSessionRoutePersistence() {

        //Given
        sessionRouteRepository.deleteAll();
        SessionRoute sessionRoute = new SessionRoute();

        //When
        Descriptor desc = new Descriptor();
        Double val = 0.3;
        desc.setFast(val);
        sessionRoute.setDescriptor(desc);
        sessionRouteRepository.save(sessionRoute);

        desc = new Descriptor();
        desc.setSafe(0.5);
        sessionRoute = new SessionRoute();
        sessionRoute.setDescriptor(desc);
        sessionRouteRepository.save(sessionRoute);

        //Then
        List<SessionRoute> sessionRoutes = sessionRouteRepository.findAll();
        Assert.assertEquals(2, sessionRoutes.size());
    }
}
