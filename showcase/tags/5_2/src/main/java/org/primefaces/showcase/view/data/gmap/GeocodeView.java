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
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

@ManagedBean
public class GeocodeView {
    
    private MapModel geoModel;
    private MapModel revGeoModel;
    private String centerGeoMap = "41.850033, -87.6500523";
    private String centerRevGeoMap = "41.850033, -87.6500523";
    
    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
        revGeoModel = new DefaultMapModel();
    }
    
    public void onGeocode(GeocodeEvent event) {
        List<GeocodeResult> results = event.getResults();
        
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            centerGeoMap = center.getLat() + "," + center.getLng();
            
            for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
            }
        }
    }
    
    public void onReverseGeocode(ReverseGeocodeEvent event) {
        List<String> addresses = event.getAddresses();
        LatLng coord = event.getLatlng();
        
        if (addresses != null && !addresses.isEmpty()) {
            centerRevGeoMap = coord.getLat() + "," + coord.getLng();
            revGeoModel.addOverlay(new Marker(coord, addresses.get(0)));
        }
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public MapModel getRevGeoModel() {
        return revGeoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public String getCenterRevGeoMap() {
        return centerRevGeoMap;
    }
}
