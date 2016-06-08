package org.tiger.agent.premain;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by tiger on 16-6-7.
 */
public class SleepingClassFileTransformer implements ClassFileTransformer {



    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        String jvmClassFullName = "org/tiger/agent/premain/Sleeping";
        String classFullName = "org.tiger.agent.premain.Sleeping";

        byte[] byteCode = classfileBuffer;
        if(className.equals(jvmClassFullName)){
            try {
                ClassPool pool=ClassPool.getDefault();
                CtClass ctClass = pool.getCtClass(classFullName);
                CtMethod ctMethod = ctClass.getDeclaredMethod("randomSleep");
                ctMethod.addLocalVariable("elapsedTime",ctClass.longType);
                ctMethod.insertBefore("elapsedTime = System.currentTimeMillis();");
                ctMethod.insertAfter("{elapsedTime = System.currentTimeMillis() - elapsedTime;" + "System.out.println(\"Method Executed in ms: \" + elapsedTime);}");
                byteCode=ctClass.toBytecode();
                ctClass.detach();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteCode;
    }


}
