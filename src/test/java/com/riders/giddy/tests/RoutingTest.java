package com.riders.giddy.tests;

import com.riders.giddy.api.v1.models.common.GiddyPath;
import com.riders.giddy.api.v1.models.common.GiddyPoint;
import com.riders.giddy.api.v1.models.score.StatNames;
import com.riders.giddy.api.v1.services.core.GiddyRouter;
import com.riders.giddy.api.v1.services.score.GiddyScoreServiceI;
import com.riders.giddy.mocks.MockedGraphStore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static com.riders.giddy.api.v1.services.core.utils.GiddyScoreHelper.buildStatsMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoutingTest {

    private GiddyRouter giddyRouter;

    private GiddyPoint start;
    private GiddyPoint finish;
    private float lowerBound;

    @Before
    public void setup() {
        GiddyScoreServiceI scoreService = new MockedGraphStore();
        giddyRouter = new GiddyRouter(scoreService);

        start = new GiddyPoint(46.7540808, 23.5849463);
        finish = new GiddyPoint(46.7649881, 23.619064);
        lowerBound = 0.2f;
    }

    @Test
    public void givenSameStartAndFinishPoints_whenComputedRoutesForDifferentGaugeScores_thenDifferentPathsAreResulted() {
        //Given
        Map<StatNames, Float> gaugeScore = buildStatsMap(1, 1, 1, 1, 1, 1);
        Map<StatNames, Float> differentGaugeScore = buildStatsMap(-1, -1, -1, -1, -1, -1);

        //When
        GiddyPath pathOne = giddyRouter.computeRoute(start, finish, gaugeScore, lowerBound);
        GiddyPath pathTwo = giddyRouter.computeRoute(start, finish, differentGaugeScore, lowerBound);

        //Then
        int sizePathOne = pathOne.getPath().size();
        int sizePathTwo = pathTwo.getPath().size();
        if (sizePathOne == sizePathTwo) {
            assertTrue(areDifferent(pathOne, pathTwo));
        }
    }

    @Test
    public void testRouteComputingIsDeterministic() {
        //Given
        Map<StatNames, Float> gaugeScore = buildStatsMap(1, 1, 1, 1, 1, 1);

        //When
        GiddyPath pathOne = giddyRouter.computeRoute(start, finish, gaugeScore, lowerBound);
        GiddyPath pathTwo = giddyRouter.computeRoute(start, finish, gaugeScore, lowerBound);

        //Then
        int sizePathOne = pathOne.getPath().size();
        int sizePathTwo = pathTwo.getPath().size();
        assertEquals(sizePathOne, sizePathTwo);
        assertFalse(areDifferent(pathOne, pathTwo));
    }

    private boolean areDifferent(GiddyPath pathOne, GiddyPath pathTwo) {
        for (int i = 0; i < pathOne.getPath().size(); i++) {
            if (!(pathOne.getPath().get(i).equals(pathTwo.getPath().get(i)))) {
                return true;
            }
        }
        return false;
    }
}
