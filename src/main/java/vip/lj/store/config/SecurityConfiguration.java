package vip.lj.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vip.lj.store.security.JwtAuthenticationFilter;
import vip.lj.store.security.handler.CustomAccessDeniedHandler;
import vip.lj.store.security.handler.CustomAuthenticationEntryPoint;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//添加此注解才可以在控制器方法上配置权限
public class SecurityConfiguration {
  final AuthenticationConfiguration authenticationConfiguration;
  JwtAuthenticationFilter filter;
  CustomAccessDeniedHandler accessDeniedHandler;
  CustomAuthenticationEntryPoint entryPoint;

  @Autowired
  public SecurityConfiguration(AuthenticationConfiguration authenticationConfiguration, JwtAuthenticationFilter filter, CustomAccessDeniedHandler accessDeniedHandler, CustomAuthenticationEntryPoint entryPoint) {
    this.authenticationConfiguration = authenticationConfiguration;
    this.filter = filter;
    this.accessDeniedHandler = accessDeniedHandler;
    this.entryPoint = entryPoint;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //关闭跨域--允许跨域
    http.csrf().disable();
    //和登录注册有关的页面和请求，页面相关静态资源（images css js...）
    String[] urls = {
      "/users/login",
      "/users/reg",
      "/products/hot",
      "/**/*.js",
      "/**/*.css",
      "/**/*.html",
      "/**/*.png",
      "/**/*.jpg",
      "/**/*.jpeg",
      "/**/*.gif",
      "/bootstrap3/**/*",
      "/favicon.ico",
    };
    //authorizeRequests()对请求进行认证和授权
//        antMatchers()匹配某些路径 取值可以是字符串数组表示的URL 每个URL必须使用/作为第一个字符
    //permitAll() 许可所有(不需要认证授权就可以访问)，前提是必须先匹配路径
    //anyRequest()任何请求(除了以前配置的请求)
    //authenticated()已经认证的（要求已经登录的）
    http.authorizeRequests()
      .antMatchers(urls).permitAll()
      .anyRequest().authenticated();

    //等需要登录时启动表单登录
    http.formLogin();
    //注册过滤器 在用户名密码验证过滤器之前做的
    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    //注册未认证处理器
    http.exceptionHandling().authenticationEntryPoint(entryPoint);
    //注册权限不足处理器
    http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    return http.build();
  }
}
