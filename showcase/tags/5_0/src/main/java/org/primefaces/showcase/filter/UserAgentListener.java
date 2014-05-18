/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.showcase.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 * Filter to keep track of browsers of visitors, mapped to the demo page /push/chart.jsf
 */
public class UserAgentListener implements Filter {
        
    private ServletContext context;
    
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String agent = ((HttpServletRequest) req).getHeader("User-Agent");
        if(agent != null) {
            boolean match = false;
            Map<String,Integer> agents = (Map) context.getAttribute("browserStats");
                    
            for (Map.Entry<String, Integer> entry : agents.entrySet()) {
                String key = entry.getKey();
                if(agent.indexOf(key) != -1) {
                    Integer value = agents.get(key);
                    agents.put(key, ++value);
                    match = true;
                    break;
                }
            }
            
            if(match == false) {
                Integer value = agents.get("Other");
                agents.put("Other", ++value);
            }
            
            EventBus eventBus = EventBusFactory.getDefault().eventBus();
            eventBus.publish("/browser", agents);
        }

		chain.doFilter(req, resp);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		Map<String,Integer> agents = new LinkedHashMap<String, Integer>();
        agents.put("MSIE", 0);
        agents.put("Firefox", 0);
        agents.put("Chrome", 0);
        agents.put("Safari", 0);
        agents.put("Other", 0);
        
        context = filterConfig.getServletContext();
        context.setAttribute("browserStats", agents);
	}
	
	public void destroy() {
		
	}
}
