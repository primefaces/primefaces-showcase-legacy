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
package org.primefaces.showcase.mobile;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import java.io.Serializable;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class WeatherView implements Serializable {

	private String conditions;
	private String city;
	private String unit = "c";
    private Map<String,String> cities;
    
    private static final Logger logger = Logger.getLogger(WeatherView.class.getName());

    @PostConstruct
    public void init() {
        cities = new LinkedHashMap<String, String>();
        cities.put("Istanbul", "TUXX0014");
        cities.put("Barcelona", "SPXX0015");
        cities.put("London", "UKXX0085");
        cities.put("New York", "USNY0996");
        cities.put("Paris", "FRXX2071");
        cities.put("Rome", "ITXX0067");
    }

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
		
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

    public Map<String, String> getCities() {
        return cities;
    }

    public void retrieveConditions() {
        try {
			URL feedSource = new URL("http://weather.yahooapis.com/forecastrss?p=" + city + "&u=" + unit);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedSource));
			String value = ((SyndEntry) feed.getEntries().get(0)).getDescription().getValue();
			
			conditions = value.split("<a href")[0];
		} catch (Exception e) {
			logger.severe(e.getMessage());
            conditions = "Unable to retrieve weather forecast at the moment.";
		}
	}

    public String saveSettings() {
        conditions = null;
        return "pm:main";
    }
}
