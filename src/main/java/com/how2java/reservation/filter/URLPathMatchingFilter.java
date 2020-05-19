package com.how2java.reservation.filter;
 
import java.util.Arrays;
import java.util.Set;
 
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
 
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
 
import com.how2java.reservation.service.PermissionService;
import com.how2java.reservation.util.SpringContextUtils;
 
public class URLPathMatchingFilter extends PathMatchingFilter {
    @Autowired
    PermissionService permissionService;
 
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        if(null==permissionService)
            permissionService = SpringContextUtils.getContext().getBean(PermissionService.class);
         
        String requestURI = getPathWithinApplication(request);
        System.out.println("requestURI:" + requestURI);
 
        Subject subject = SecurityUtils.getSubject();
        // 如果没有登录，就跳转到登录页面
        if (!subject.isAuthenticated()) {
        	System.out.println("验证失败，if语句运行到此" );
            WebUtils.issueRedirect(request, response, "/login");
//        	WebUtils.issueRedirect(request, response, "/admin/login");
//            WebUtils.issueRedirect(request, response, "/admin/listUser");
            return false;
        }
 
        // 看看这个路径权限里有没有维护，如果没有维护，一律放行(也可以改为一律不放行)
        System.out.println("permissionService:"+permissionService);
        boolean needInterceptor = permissionService.needInterceptor(requestURI);
        if (!needInterceptor) {
            return true;
        } else {
            boolean hasPermission = false;
            String userName = subject.getPrincipal().toString();
            Set<String> permissionUrls = permissionService.listPermissionURLs(userName);
            for (String url : permissionUrls) {
                // 这就表示当前用户有这个权限
                if (url.equals(requestURI)) {
                    hasPermission = true;
                    break;
                }
            }
 
            if (hasPermission)
                return true;
            else {
                UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");
 
                subject.getSession().setAttribute("ex", ex);
 
                WebUtils.issueRedirect(request, response, "/unauthorized");
                return false;
            }
 
        }
 
    }
}