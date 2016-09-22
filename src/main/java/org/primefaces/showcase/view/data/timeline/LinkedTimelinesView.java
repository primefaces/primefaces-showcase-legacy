/*
 * Copyright 2009-2016 PrimeTek.
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
package org.primefaces.showcase.view.data.timeline;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.component.timeline.TimelineUpdater;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

@ManagedBean(name = "linkedTimelinesView")
@ViewScoped
public class LinkedTimelinesView implements Serializable {

    private TimelineModel modelFirst;  // model of the first timeline  
    private TimelineModel modelSecond; // model of the second timeline  
    private boolean aSelected;         // flag if the project A is selected (for test of select() call on the 2. model)  

    @PostConstruct
    public void init() {
        createFirstTimeline();
        createSecondTimeline();
    }

    private void createFirstTimeline() {
        modelFirst = new TimelineModel();

        Calendar cal = Calendar.getInstance();
        cal.set(2015, Calendar.AUGUST, 22, 17, 30, 0);
        modelFirst.add(new TimelineEvent(new Task("Mail from boss", "images/timeline/mail.png", false), cal.getTime()));

        cal.set(2015, Calendar.AUGUST, 23, 23, 0, 0);
        modelFirst.add(new TimelineEvent(new Task("Call back my boss", "images/timeline/callback.png", false), cal.getTime()));

        cal.set(2015, Calendar.AUGUST, 24, 21, 45, 0);
        modelFirst.add(new TimelineEvent(new Task("Travel to Spain", "images/timeline/location.png", false), cal.getTime()));

        cal.set(2015, Calendar.AUGUST, 26, 0, 0, 0);
        Date startWork = cal.getTime();
        cal.set(2015, Calendar.SEPTEMBER, 2, 0, 0, 0);
        Date endWork = cal.getTime();
        modelFirst.add(new TimelineEvent(new Task("Do homework", "images/timeline/homework.png", true), startWork, endWork));

        cal.set(2015, Calendar.AUGUST, 28, 0, 0, 0);
        modelFirst.add(new TimelineEvent(new Task("Create memo", "images/timeline/memo.png", false), cal.getTime()));

        cal.set(2015, Calendar.AUGUST, 31, 0, 0, 0);
        Date startReport = cal.getTime();
        cal.set(2015, Calendar.SEPTEMBER, 3, 0, 0, 0);
        Date endReport = cal.getTime();
        modelFirst.add(new TimelineEvent(new Task("Create report", "images/timeline/report.png", true), startReport, endReport));
    }

    private void createSecondTimeline() {
        modelSecond = new TimelineModel();

        Calendar cal = Calendar.getInstance();

        cal.set(2015, Calendar.AUGUST, 23, 0, 0, 0);
        Date startProject = cal.getTime();
        cal.set(2015, Calendar.AUGUST, 30, 0, 0, 0);
        Date endProject = cal.getTime();
        modelSecond.add(new TimelineEvent("Project A", startProject, endProject));

        cal.set(2015, Calendar.AUGUST, 27, 0, 0, 0);
        startProject = cal.getTime();
        cal.set(2015, Calendar.AUGUST, 31, 0, 0, 0);
        endProject = cal.getTime();
        modelSecond.add(new TimelineEvent("Project B", startProject, endProject));
    }

    public void onSelect(TimelineSelectEvent e) {
        // get a thread-safe TimelineUpdater instance for the second timeline  
        TimelineUpdater timelineUpdater = TimelineUpdater.getCurrentInstance(":form:timelineSecond");

        if (aSelected) {
            // select project B visually (index in the event's list is 1)  
            timelineUpdater.select(1);
        } else {
            // select project A visually (index in the event's list is 0)  
            timelineUpdater.select(0);
        }

        aSelected = !aSelected;

        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Selected project: " + (aSelected ? "A" : "B"), null);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public TimelineModel getModelFirst() {
        return modelFirst;
    }

    public TimelineModel getModelSecond() {
        return modelSecond;
    }

    public class Task implements Serializable {

        private String title;
        private String imagePath;
        private boolean period;

        public Task(String title, String imagePath, boolean period) {
            this.title = title;
            this.imagePath = imagePath;
            this.period = period;
        }

        public String getTitle() {
            return title;
        }

        public String getImagePath() {
            return imagePath;
        }

        public boolean isPeriod() {
            return period;
        }
    }
}
