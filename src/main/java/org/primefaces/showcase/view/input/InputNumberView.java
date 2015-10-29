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
package org.primefaces.showcase.view.input;

import java.math.BigDecimal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class InputNumberView {

    private Double input1 = new Double(0);
    private Double input2 = new Double(0);
    private Double input3 = new Double(0);
    private Double input4 = new Double(0);
    private Double input5 = new Double(0);
    private Double input6 = new Double(0);
    private Double input7 = null;
    private BigDecimal input8 = new BigDecimal(0);


    public InputNumberView() {
        input1 = 0d;
        input2 = 0d;
        input3 = 0d;
        input4 = 0d;
        input5 = 251.31;
        input6 = 60d;
        input8 = new BigDecimal("1234.000000001");
    }

    public Double getInput1() {
        return input1;
    }

    public void setInput1(Double input1) {
        this.input1 = input1;
    }

    public Double getInput2() {
        return input2;
    }

    public void setInput2(Double input2) {
        this.input2 = input2;
    }

    public Double getInput3() {
        return input3;
    }

    public void setInput3(Double input3) {
        this.input3 = input3;
    }

    public Double getInput4() {
        return input4;
    }

    public void setInput4(Double input4) {
        this.input4 = input4;
    }

    public Double getInput5() {
        return input5;
    }

    public void setInput5(Double input5) {
        this.input5 = input5;
    }

    public Double getInput6() {
        return input6;
    }

    public void setInput6(Double input6) {
        this.input6 = input6;
    }

    public Double getInput7() {
        return input7;
    }

    public void setInput7(Double input7) {
        this.input7 = input7;
    }

    public BigDecimal getInput8() {
        return input8;
    }

    public void setInput8(BigDecimal input8) {
        this.input8 = input8;
    }
}
