package com.userprofile.app.init;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.userprofile.config.HibernateConfig;
import com.userprofile.config.SpringConfig;
import com.userprofile.security.BrowserFilter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

@Override
 protected Class<?>[] getRootConfigClasses() {
 return new Class[] { SpringConfig.class, HibernateConfig.class};
 }

 @Override
 protected Class<?>[] getServletConfigClasses() {
 return null;
 }

 @Override
 protected String[] getServletMappings() {
 return new String[] { "/" };
 }
 
 @Override
	protected Filter[] getServletFilters() {
	 return new Filter[] {
		      new BrowserFilter()
		    };
	}
}
