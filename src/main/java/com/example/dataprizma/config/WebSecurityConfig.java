package com.example.dataprizma.config;//package com.example.firstProject.config;
//
//import com.example.firstProject.jwt.JwtAuthenticationEntryPoint;
//import com.example.firstProject.jwt.JwtRequestFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
////    private PasswordEncoder passwordEncoder;
////    @Autowired
////    public WebSecurityConfig(PasswordEncoder passwordEncoder){
////        this.passwordEncoder = passwordEncoder;
////    }
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
//////                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
////                .csrf().disable()
//////                .and()
////                .authorizeRequests()
////                .antMatchers( "/index.html", "/css/*", "/js/*").permitAll()
////                .antMatchers("/api/**").hasRole(USER.name())
////                .anyRequest()
////                .authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login").permitAll()
////                .defaultSuccessUrl("/index", true)
////                .passwordParameter("password")
////                .usernameParameter("username")
////                .and()
////                .rememberMe()
////                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
////                .key("somethingsecurd")
////                .and()
////                .logout()
////                .logoutUrl("logout")
////                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
////                .clearAuthentication(true)
////                .invalidateHttpSession(true)
////                .deleteCookies("JSESSIONID", "remember-me")
////                .logoutSuccessUrl("/login");
////    }
////
////    @Bean
////    @Override
////    protected UserDetailsService userDetailsService() {
////        UserDetails user = User.builder()
////                .username("user")
////                .password(passwordEncoder.encode("password"))
////                .roles(USER.name())
////                .build();
////        UserDetails admin = User.builder()
////                .username("admin")
////                .password(passwordEncoder.encode("password123"))
////                .authorities(ADMIN.getGrantedAuthorities())
////                .build();
////        UserDetails editor = User.builder()
////                .username("editor")
////                .password(passwordEncoder.encode("password1234"))
////                .authorities(ADMINTRAINEE.getGrantedAuthorities() )
////                .build();
////        return new InMemoryUserDetailsManager(
////                user,
////                admin,
////                editor
////        );
////    }
//
//
//
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Autowired
//    private UserDetailsService jwtUserDetailsService;
//
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // configure AuthenticationManager so that it knows from where to load
//        // user for matching credentials
//        // Use BCryptPasswordEncoder
//        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        // We don't need CSRF for this example
//        httpSecurity.csrf().disable()
//
//                // dont authenticate this particular request
//                .authorizeRequests().antMatchers("/api/v1/user/list", "/api/v1/login/user").hasRole("USER").
//                // all other requests need to be authenticated
//                        anyRequest().authenticated().and().
//                // make sure we use stateless session; session won't be used to
//                // store user's state.
//                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        // Add a filter to validate the tokens with every request
//        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//}
