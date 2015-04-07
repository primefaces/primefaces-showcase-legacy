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
package org.primefaces.showcase.push.browser;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@RequestScoped
public class BrowserStatsView {
    
    private PieChartModel pieModel;
    
    @ManagedProperty("#{browserStats}")
    private Map agents;
    
    @PostConstruct
    public void init() {
        pieModel = new PieChartModel();
        pieModel.setData(agents);
        pieModel.setTitle("Browser Stats");
        pieModel.setShowDataLabels(true);
        pieModel.setLegendPosition("w");
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }    

    public Map getAgents() {
        return agents;
    }

    public void setAgents(Map<String, Integer> agents) {
        this.agents = agents;
    }
}
