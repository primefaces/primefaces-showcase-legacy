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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.showcase.domain.NewsEntry;
import org.primefaces.showcase.domain.NewsGroup;

@ManagedBean
@ApplicationScoped
public class NewsView implements Serializable {

	private List<NewsGroup> groups;
	private NewsEntry selectedEntry;
    private NewsGroup selectedGroup;
    private Map<String,String> feeds;

    @PostConstruct
	public void init() {
		feeds = new HashMap<String, String>();
		feeds.put("Top Stories", "http://feeds.reuters.com/reuters/topNews");
		feeds.put("World", "http://feeds.reuters.com/Reuters/worldNews");
		feeds.put("Technology", "http://feeds.reuters.com/reuters/technologyNews");
		feeds.put("Sports", "http://feeds.reuters.com/reuters/sportsNews");
		feeds.put("Entertainment", " 	http://feeds.reuters.com/reuters/entertainment");
		feeds.put("Business", "http://feeds.reuters.com/reuters/businessNews");
		feeds.put("Politics", "http://feeds.reuters.com/Reuters/PoliticsNews");
        
        groups = fetchNews();
	}

	public List<NewsGroup> getGroups() {
		return groups;
	}

    public NewsEntry getSelectedEntry() {
        return selectedEntry;
    }
    public void setSelectedEntry(NewsEntry selectedEntry) {
        this.selectedEntry = selectedEntry;
    }

    public NewsGroup getSelectedGroup() {
        return selectedGroup;
    }
    public void setSelectedGroup(NewsGroup selectedGroup) {
        this.selectedGroup = selectedGroup;
    }
    
    public List<NewsGroup> fetchNews() {
		List<NewsGroup> news = new ArrayList<NewsGroup>();
		try {
			for(String key : feeds.keySet()) {
				URL feedSource = new URL(feeds.get(key));
				SyndFeedInput input = new SyndFeedInput();
				SyndFeed feed = input.build(new XmlReader(feedSource));
				List<NewsEntry> entries = new ArrayList<NewsEntry>();
				
				int i = 0;
				for(Object f : feed.getEntries()) {
					SyndEntry entry = (SyndEntry) f;
                    String title = entry.getTitle();
                    title = title.length() <= 25 ? title : title.substring(0, 25);
					entries.add(new NewsEntry(i, title + "...", entry.getDescription().getValue()));
					i++;
				}
				
				news.add(new NewsGroup(key, entries)); 
			}
		
		} catch(Exception exception) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please try again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
		return news;
	}
}