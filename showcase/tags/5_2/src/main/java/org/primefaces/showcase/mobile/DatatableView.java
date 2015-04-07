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

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.mobile.event.SwipeEvent;
import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.service.CarService;

@ManagedBean(name="dtMobileView")
@ViewScoped
public class DatatableView implements Serializable {
    
    private List<Car> cars1;
    private List<Car> cars2;
    private List<Car> cars3;
    private List<Car> selectedCars;
   
    @ManagedProperty("#{carService}")
    private CarService service;
    
    @PostConstruct
    public void init() {
        cars1 = service.createCars(10);
        cars2 = service.createCars(10);
        cars3 = service.createCars(50);
    }

    public List<Car> getCars1() {
        return cars1;
    }

    public List<Car> getCars2() {
        return cars2;
    }

    public List<Car> getCars3() {
        return cars3;
    }
    
    public void setService(CarService service) {
        this.service = service;
    }

    public List<Car> getSelectedCars() {
        return selectedCars;
    }

    public void setSelectedCars(List<Car> selectedCars) {
        this.selectedCars = selectedCars;
    }
        
    public void onRowSwipeRight(SwipeEvent event) {
        Car car = ((Car) event.getData());
        cars3.remove(car);
        
        if(selectedCars != null && !selectedCars.isEmpty()) {
            selectedCars.remove(car);
        }
    }
    
    public void onRowSwipeLeft(SwipeEvent event) {
        FacesMessage msg = new FacesMessage("Swiped Left", ((Car) event.getData()).getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}