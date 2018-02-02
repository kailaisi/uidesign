package cn.aspect;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.concurrent.TimeUnit;

/**
 * 描述:
 * <p/>作者：wjx
 * <p/>创建时间: 2017/12/22 10:20
 */
@Aspect
public class TimeLogAspect {
    @Pointcut("execution(@cn.aspect.TimeLog * *(..))")
    public void methodAnnotated(){

    }
    @Pointcut("execution(@cn.aspect.TimeLog *.new(..))")//构造器切入点
    public void constructorAnnotated() {
    }
    @Around("methodAnnotated()||constructorAnnotated()")
    public Object annotionPoint(ProceedingJoinPoint joinPoint) throws Throwable{
        Signature signature = joinPoint.getSignature();
        String simpleName = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        long start = System.nanoTime();
        Object result = joinPoint.proceed();
        StringBuilder builder = new StringBuilder();
        builder.append(methodName+":");
        for (Object obj:joinPoint.getArgs()){
            if (obj instanceof String)builder.append((String) obj);
            else if(obj instanceof Class) builder.append(((Class) obj).getSimpleName());
        }
        String key = builder.toString();
        Log.e("TimeLog", simpleName+"."+key+joinPoint.getArgs().toString()+"-->:["+
                (TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start)) + "ms]");
        return result;
    }
}
