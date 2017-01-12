package com.riders.giddy;

import com.riders.giddy.commons.persistence.PathRepository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class SessionRouteSaveTest extends BaseTest {


    @Autowired
    private PathRepository pathRepository;

    /*  @Autowired
      private MongoTemplate template;

      @Before
      public void setUp() {
          template.indexOps(Path.class).ensureIndex(new GeospatialIndex("point"));
      }
  */
    @Test
    public void testSessionRoutePersistence() {


    }


}
