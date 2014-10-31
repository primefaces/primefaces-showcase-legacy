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
package org.primefaces.showcase.view.data.gmap;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.ReverseGeocodeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
public class GeocodeView {
    
    private MapModel simpleGeoModel;
    private MapModel simpleRevGeoModel;
    
    @PostConstruct
    public void init() {
        simpleGeoModel = new DefaultMapModel();
        simpleRevGeoModel = new DefaultMapModel();
    }
    
    public void onGeocode(GeocodeEvent event) {
        List<LatLng> latLng = event.getLatlng();
        for(int i = 0; i < latLng.size(); i++) {
            LatLng coord = new LatLng(latLng.get(i).getLat(), latLng.get(i).getLng());
            simpleGeoModel.addOverlay(new Marker(coord, event.getAddress().get(0)));
        }
    }
    
    public void onReverseGeocode(ReverseGeocodeEvent event) {
        LatLng coord = new LatLng(event.getLatlng().get(0).getLat(), event.getLatlng().get(0).getLng());
        simpleRevGeoModel.addOverlay(new Marker(coord, event.getAddress().get(0)));
    }

    public MapModel getSimpleGeoModel() {
        return simpleGeoModel;
    }

    public MapModel getSimpleRevGeoModel() {
        return simpleRevGeoModel;
    }
    
}
