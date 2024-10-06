package org.elambda;

import java.math.BigDecimal;

public class BancoResponse {

    private BigDecimal cuota;

    private BigDecimal rate;

    private Integer term;

    private BigDecimal cuotaWithAccount;

    private BigDecimal rateWithAccount;

    private Integer termWithAccount;

    public BigDecimal getCuota() {
        return cuota;
    }

    public void setCuota(BigDecimal cuota) {
        this.cuota = cuota;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getCuotaWithAccount() {
        return cuotaWithAccount;
    }

    public void setCuotaWithAccount(BigDecimal cuotaWithAccount) {
        this.cuotaWithAccount = cuotaWithAccount;
    }

    public BigDecimal getRateWithAccount() {
        return rateWithAccount;
    }

    public void setRateWithAccount(BigDecimal rateWithAccount) {
        this.rateWithAccount = rateWithAccount;
    }

    public Integer getTermWithAccount() {
        return termWithAccount;
    }

    public void setTermWithAccount(Integer termWithAccount) {
        this.termWithAccount = termWithAccount;
    }
}
