package com.app.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.helper.JWTUtil;
import com.app.service.CustomUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	
	@Autowired
	private JWTUtil jwtUt;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

            //get jwt
		    //bearer
		    //validate
		
		String requestTokenHeader = request.getHeader("Authorization");
		String userName = null;
		String jwtTocken = null;
	
		
		//null checking and formatting
		if(requestTokenHeader!=null&&requestTokenHeader.startsWith("Bearer "))
		{
			jwtTocken=requestTokenHeader.substring(7);
			try {
				
				userName = this.jwtUt.extractUsername(jwtTocken);
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
			 UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userName);
			//security cheking
			if(userName!=null&&SecurityContextHolder.getContext().getAuthentication()==null)
			{
				
			
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
			}
			else
			{
				System.out.println("tocken is not validated...");
			}
			
			
		}
		
		response.addHeader("Access-Control-Allow-Headers",
                "Access-Control-Allow-Origin, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        if (response.getHeader("Access-Control-Allow-Origin") == null)
            response.addHeader("Access-Control-Allow-Origin", "*");
		filterChain.doFilter(request, response);
		
	}

	
}
