/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-4-26
 */
package com.rop.sample;

import com.rop.AbstractInterceptor;
import com.rop.RequestContext;
import com.rop.sample.response.InterceptorResponse;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *    该拦截器仅对method为“user.add”进行拦截，你可以在{@link #isMatch(com.rop.RequestContext)}方法中定义拦截器的匹配规则。
 *  你可以通过{@link com.rop.RequestContext#getServiceMethodDefinition()}获取服务方法的注解信息，通过这些信息进行拦截匹配规则
 *  定义。
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */

@Component
public class ReservedUserNameInterceptor extends AbstractInterceptor {

    @Override
    public void beforeService(RequestContext methodContext) {
        System.out.println("beforeService ...");

        if ("jhonson".equals(methodContext.getParamValue("userName"))) {
            InterceptorResponse response = new InterceptorResponse();
            response.setTestField("the userName can't be jhonson!");
            methodContext.setRopResponse(response);
        }
    }

    @Override
    public void beforeResponse(RequestContext methodContext) {
        System.out.println("beforeResponse ...");
    }

    @Override
    public boolean isMatch(RequestContext methodContext) {
        return "user.add".equals(methodContext.getMethod());
    }
}
