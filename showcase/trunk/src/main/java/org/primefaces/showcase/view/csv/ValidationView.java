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
package org.primefaces.showcase.view.csv;

import java.util.Date;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ValidationView {
    
    private String text;
    private String description;
    private Integer integer;
    private Double doubleNumber;
    private Double money;
    private String regexText;
    private Date date;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInteger() {
        return integer;
    }
    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Double getDoubleNumber() {
        return doubleNumber;
    }
    public void setDoubleNumber(Double doubleNumber) {
        this.doubleNumber = doubleNumber;
    }

    public Double getMoney() {
        return money;
    }
    public void setMoney(Double money) {
        this.money = money;
    }

    public String getRegexText() {
        return regexText;
    }
    public void setRegexText(String regexText) {
        this.regexText = regexText;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    } 
}
