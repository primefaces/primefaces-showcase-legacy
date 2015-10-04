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

import java.util.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.showcase.domain.Car;
import org.primefaces.showcase.service.CarService;

@ManagedBean(name = "dfView")
public class DFView {
    
    @ManagedProperty("#{carService}")
    private CarService service;
    
    private String selectedBrand;

    public void viewCars() {
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
		RequestContext.getCurrentInstance().openDialog("viewCars", options, null);
	}

	public void viewCarsCustomized() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("width", 640);
		options.put("height", 340);
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("headerElement", "customheader");

		RequestContext.getCurrentInstance().openDialog("viewCars", options, null);
	}

	public void chooseCar() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);

		Map<String, List<String>> params = getParams();

		RequestContext.getCurrentInstance().openDialog("selectCar", options, params);
	}

	private Map<String, List<String>> getParams() {
		if (selectedBrand == null) {
			return null;
		}

		Map<String, List<String>> params = new HashMap<String, List<String>>();
		List<String> brands = Arrays.asList(selectedBrand);
		params.put("params", brands);
		return params;
	}

	public void onCarChosen(SelectEvent event) {
		Car car = (Car) event.getObject();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Car Selected", "Id:" + car.getId());

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void showMessage() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"What we do in life", "Echoes in eternity.");

		RequestContext.getCurrentInstance().showMessageInDialog(message);
	}

	public List<String> getBrands() {
		return service.getBrands();
	}
	
	public String getSelectedBrand() {
		return selectedBrand;
	}

	public void setSelectedBrand(String brand) {
		this.selectedBrand = brand;
	}
	
    public void setService(CarService service) {
        this.service = service;
    }

}
