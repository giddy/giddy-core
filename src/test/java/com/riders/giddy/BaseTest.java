package com.riders.giddy;

import com.riders.giddy.api.v1.services.GiddyRouter;
import com.riders.giddy.commons.persistence.store.GraphStatsStore;

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
    private GraphStatsStore statsStore = new MockedGraphStore();

    @InjectMocks
    GiddyRouter router;
}
