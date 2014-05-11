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
package org.primefaces.showcase.view.data.datatable;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.showcase.domain.Sale;

@ManagedBean(name="dtGroupView")
@ViewScoped
public class GroupView implements Serializable {
    
    private final static String[] manufacturers;
    
    static {		
		manufacturers = new String[10];
		manufacturers[0] = "Apple";
		manufacturers[1] = "Samsung";
		manufacturers[2] = "Microsoft";
		manufacturers[3] = "Philips";
		manufacturers[4] = "Sony";
		manufacturers[5] = "LG";
		manufacturers[6] = "Sharp";
		manufacturers[7] = "Panasonic";
		manufacturers[8] = "HTC";
		manufacturers[9] = "Nokia";
	}
    
    private List<Sale> sales;
    
    @PostConstruct
    public void init() {
        sales = new ArrayList<Sale>();

        for(int i = 0; i < 10; i++) {
            sales.add(new Sale(manufacturers[i], getRandomAmount(), getRandomAmount(), getRandomPercentage(), getRandomPercentage()));
        }
    }

    public List<Sale> getSales() {
        return sales;
    }

    private int getRandomAmount() {
		return (int) (Math.random() * 100000);
	}

    private int getRandomPercentage() {
		return (int) (Math.random() * 100);
	}
    
    public String getLastYearTotal() {
        int total = 0;

        for(Sale sale : getSales()) {
            total += sale.getLastYearSale();
        }

        return new DecimalFormat("###,###.###").format(total);
    }

    public String getThisYearTotal() {
        int total = 0;

        for(Sale sale : getSales()) {
            total += sale.getThisYearSale();
        }

        return new DecimalFormat("###,###.###").format(total);
    }
}
