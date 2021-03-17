package com.example.securitypt3;

import java.io.Console;
import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.user.domain.User;

/**
 * SpringSecurityを利用するための設定クラス
 * ログイン処理でのパラメータ、画面遷移や認証処理でのデータアクセス先を設定する
 */
/**@Configuration
@EnableWebSecurity //1
public class WebSecurityCnf extends WebSecurityConfigurerAdapter { //2
    /**
     * 認可設定を無視するリクエストを設定
     * 静的リソース(image,javascript,css)を認可処理の対象から除外する
     */
   /** @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        		.antMatchers("/css/**", "/js/**", "/images/**"); //3
    }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    		.anyRequest().authenticated() //1
    		.and()
    		.formLogin()
    		.loginPage("/login")
    		.permitAll();
        }
}*/

@Configuration
public class WebSecurityCnf extends WebSecurityConfigurerAdapter {

    //private static final Logger log = LogManager.getLogger();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Note:
        // Use this to enable the tomcat basic authentication (tomcat popup rather than spring login page)
        // Note that the CSRf token is disabled for all requests
        //log.info("Disabling CSRF, enabling basic authentication...");
        http
        .authorizeRequests()
            .antMatchers("/**").authenticated() // These urls are allowed by any authenticated user
        .and()
            .httpBasic();
        http.csrf().disable();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //log.info("Setting in-memory security using the user input...");

        Integer id = null;
        String password = null;

        System.out.println("\nPlease set the admin credentials for this web application (will be required when browsing to the web application)");
        Console console = System.console();

        // Read the credentials from the user console:
        // Note:
        // Console supports password masking, but is not supported in IDEs such as eclipse;
        // thus if in IDE (where console == null) use scanner instead:
        if (console == null) {
            // Use scanner:
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("User ID: ");
                id = Integer.parseInt(scanner.nextLine());
                System.out.print("Password: ");
                password = scanner.nextLine();
                System.out.print("Confirm Password: ");
                String inputPasswordConfirm = scanner.nextLine();

                if (id == null) {
                    System.out.println("Error: user must be set - please try again");
                } else if (password.isEmpty()) {
                    System.out.println("Error: password must be set - please try again");
                //} else (!password.equals(inputPasswordConfirm))
                    //System.out.println("Error: password and password confirm do not match - please try again");{
                    //log.info("Setting the in-memory security using the provided credentials...");
                    break;
                }
                System.out.println("");
            }
            scanner.close();
        } else {
            // Use Console
            while (true) {
                id = Integer.parseInt(console.readLine("Id: "));
                char[] passwordChars = console.readPassword("Password: ");
                password = String.valueOf(passwordChars);
                char[] passwordConfirmChars = console.readPassword("Confirm Password: ");
                String passwordConfirm = String.valueOf(passwordConfirmChars);

                if (id == null) {
                    System.out.println("Error: Username must be set - please try again");
                } else if (password.isEmpty()) {
                    System.out.println("Error: Password must be set - please try again");
                } else if (!password.equals(passwordConfirm)) {
                    System.out.println("Error: Password and Password Confirm do not match - please try again");
                } else {
                    //log.info("Setting the in-memory security using the provided credentials...");
                    break;
                }
                System.out.println("");
            }
        }

        // Set the inMemoryAuthentication object with the given credentials:
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        if (id != null && password != null) {
            String encodedPassword = passwordEncoder().encode(password);
            manager.createUser(User.withId(id).password(encodedPassword).build());
        }
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}