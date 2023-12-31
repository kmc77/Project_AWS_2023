package com.itda.ITDA;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//스프링 부트의 웹 MVC 를 구상하는 역할을 하는 WebMvcConfig 클래스
//뷰 컨트롤러 등록 및 정적 리소스들의 핸들러 등록을 해 탐색하는 역할을 한다
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Value("${my.savepath}")
	private String saveFolder;
	
	@Value("${app.image-upload-dir.savepath}")
	private String saveFolder1;
	
	@Value("${app.image-upload-dir2.savepath}")
	private String saveFolder2;

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS
	= {
			"classpath:/static/",
			"classpath:/templates/",
	};
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//url mapping "/"로 접속하면 "/main/protomain"로 이동
		registry.addViewController("/").setViewName("forward:/main/protomain");
	}
	
	//정적 리소스 핸들러 등록을 하고, 그 리소스들의 위치를 담은 상수 값을 매개변수로 가지는
	//메서드를 오버라이딩해 탐색하는 역할을 한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    //   /static/ 또는 /templates/ 경로의 resources에 정적 리소스들이 있다고 설정되어있다.
	    registry.addResourceHandler("/resources/**")
	        .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	    registry.addResourceHandler("/upload/**")
	        .addResourceLocations(saveFolder);
	    // src/main/resources/static/image/ 경로의 정적 리소스를 추가
	    registry.addResourceHandler("/MemberUpload/**")
	    .addResourceLocations(saveFolder1);
	    
	    registry.addResourceHandler("/Member/**")
	    .addResourceLocations(saveFolder2);
	}
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		//   /static/ 또는 /templates/ 경로의 resources에 정적 리소스들이 있다고 설정되어있다.
//		registry.addResourceHandler("/resources/**")
//		.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
//		registry.addResourceHandler("/upload/**")
//				.addResourceLocations(saveFolder);
//
//	}
	
}
