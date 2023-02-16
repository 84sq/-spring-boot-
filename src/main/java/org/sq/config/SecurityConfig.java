package org.sq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity  // 开启MVC security安全支持
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login","/","/confirmManage").permitAll()
                .antMatchers("/css/**", "/img/**", "/js/**").permitAll()
                .antMatchers("/indexPage", "/displayPage", "/search","/successManage").permitAll()
                .antMatchers("/admit/**").hasRole("admit")
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login").loginProcessingUrl("/indexPage")
                .usernameParameter("userName").passwordParameter("password")
                .successForwardUrl("/indexPage")
                .failureUrl("/login?error");

        http.rememberMe().rememberMeParameter("rememberMe")
                .tokenValiditySeconds(400);

        http.csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  密码需要设置编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //  使用JDBC进行身份认证
        String userSQL = "select userName,password,phone from t_user where userName = ?";
        String authoritySQL = "select a.userName,b.authority from t_user a,user_authority b,t_user_authority c "
                + "where c.user_id = a.id and c.authority_id = b.id and a.userName = ?";
        auth.jdbcAuthentication().passwordEncoder(encoder)
                .dataSource(dataSource)
                .usersByUsernameQuery(userSQL)
                .authoritiesByUsernameQuery(authoritySQL);
    }
}

