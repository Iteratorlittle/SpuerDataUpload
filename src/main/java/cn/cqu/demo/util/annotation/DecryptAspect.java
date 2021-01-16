package cn.cqu.demo.util.annotation;

import cn.cqu.demo.util.RSAUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import javax.servlet.http.HttpSession;
import java.security.interfaces.RSAPrivateKey;

@Aspect
@Component
public class DecryptAspect {

    @Pointcut("@annotation(cn.cqu.demo.util.annotation.Decrypt)")
    public void Pointer(){

    }

    @Around("Pointer()")
    public Object processEncode(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] sc_process=joinPoint.getArgs();
        RequestAttributes requestAttribute= RequestContextHolder.getRequestAttributes();
        HttpSession session = (HttpSession) requestAttribute.resolveReference(RequestAttributes.REFERENCE_SESSION);
        RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute("privatekey");
        sc_process[0]=RSAUtils.decryptByPrivateKey((String)sc_process[0],privateKey);
        Object object=joinPoint.proceed(sc_process);
        return object;
    }
}