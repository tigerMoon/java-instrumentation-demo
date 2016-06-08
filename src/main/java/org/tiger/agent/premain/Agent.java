package org.tiger.agent.premain;

import java.lang.instrument.Instrumentation;

/**
 * Created by tiger on 16-6-7.
 */
public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        // registers the transformer
        inst.addTransformer(new SleepingClassFileTransformer());
    }
}
