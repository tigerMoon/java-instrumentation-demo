package org.tiger.test;

import org.junit.Test;
import org.tiger.agent.Sleeping;

/**
 * Created by tiger on 16-6-7.
 */
public class AgentTest {

    @Test
    public void shouldInstantiateSleepingInstance() throws InterruptedException {

        Sleeping sleeping = new Sleeping();
        sleeping.randomSleep();
    }
}
