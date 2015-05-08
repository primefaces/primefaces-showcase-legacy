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
package org.primefaces.showcase.view.test;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class AccordionPanalTestView {

    private List<TabModel> tabs;

    @PostConstruct
    public void init() {
        tabs = new ArrayList<TabModel>();
        tabs.add(new TabModel("Title 1", new Row("Row 1a", true), new Row("Row 1b", false)));
        tabs.add(new TabModel("Title 2", new Row("Row 2a", true), new Row("Row 2b", false)));
    }

    public List<TabModel> getTabs() {
        return tabs;
    }


    public void handleSubmit() {
        StringBuilder sb = new StringBuilder();
        for (TabModel tab : tabs) {
            sb.append(tab.getName()).append(":\n");
            for(Row row : tab.getRows()) {
                sb.append("  ").append(row.getName()).append(": ").append(row.isCheckbox()).append("\n");
            }
        }
        FacesMessage message = new FacesMessage(sb.toString(), sb.toString());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
