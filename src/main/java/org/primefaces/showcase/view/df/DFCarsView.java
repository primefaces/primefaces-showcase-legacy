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
package org.primefaces.showcase.view.df;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.service.CarService;

@ManagedBean(name = "dfCarsView")
@ViewScoped
public class DFCarsView implements Serializable {
    
	private static final long serialVersionUID = 1L;

	private List<Car> cars;
    
    private String selectedBrand;

    @ManagedProperty("#{carService}")
    private CarService service;

    @PostConstruct
    public void init() {
        Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        setBrand(params.get("params"));
        findCars();
    }
    
    private void findCars() {
    	if (selectedBrand == null) {
    		cars = service.createCars(9);
    	} else {
    		cars = service.findByBrand(selectedBrand);
    	}
	}

	private void setBrand(String[] brand) {
		if (brand == null) {
			return;
		}
		selectedBrand = brand[0];
	}

	public List<Car> getCars() {
        return cars;
    }

    public void setService(CarService service) {
        this.service = service;
    }
    
    public void selectCarFromDialog(Car car) {
        RequestContext.getCurrentInstance().closeDialog(car);
    }

	public String getSelectedBrand() {
		return selectedBrand;
	}
}
