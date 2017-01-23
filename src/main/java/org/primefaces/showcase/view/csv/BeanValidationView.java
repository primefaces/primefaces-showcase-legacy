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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean
public class BeanValidationView {
    
    @Size(min=2,max=5)
    private String name;
    
    @Min(10) @Max(20)
    private Integer age;
    
    @DecimalMax(value= "99.9", message = "Shold not exceed 99.9")
    private Double amount;
    
    @Digits(integer=3,fraction=2)
    private Double amount2;
    
    @AssertTrue
    private boolean checked;

    @Past
    private Date pastDate;
    
    @Future
    private Date futureDate;
    
    @Pattern(regexp="^[-+]?\\d+$")
    private String pattern;
    
    @NotNull
    private Boolean bool;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount2() {
        return amount2;
    }
    public void setAmount2(Double amount2) {
        this.amount2 = amount2;
    }

    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Date getPastDate() {
        return pastDate;
    }
    public void setPastDate(Date pastDate) {
        this.pastDate = pastDate;
    }

    public Date getFutureDate() {
        return futureDate;
    }
    public void setFutureDate(Date futureDate) {
        this.futureDate = futureDate;
    }

    public String getPattern() {
        return pattern;
    }
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Boolean getBool() {
        return bool;
    }
    public void setBool(Boolean bool) {
        this.bool = bool;
    }

}
