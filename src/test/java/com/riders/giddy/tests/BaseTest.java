package com.riders.giddy.tests;

import com.riders.giddy.api.v1.services.GiddyRouter;
import com.riders.giddy.commons.persistence.store.GiddyScoreServiceI;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {

    @Spy
    private GiddyScoreServiceI statsStore = new MockedGraphStore();

    @InjectMocks
    GiddyRouter router;
}
