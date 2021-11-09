package me.igorkudashev.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

//	@Override
//	public void onStartup(ServletContext aServletContext) throws ServletException {
//		super.onStartup(aServletContext);
//		registerHiddenFieldFilter(aServletContext);
//	}
//
//	private void registerHiddenFieldFilter(ServletContext aContext) {
//		aContext.addFilter("hiddenHttpMethodFilter",
//				new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
//	}

}
