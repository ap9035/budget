package bo;

import java.math.BigDecimal;

public class Budget {



    public Budget() {
    }

    public Budget(String yearMonth, int aMount) {
        this.yearMonth = yearMonth;
        this.aMount = aMount;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public int getaMount() {
        return aMount;
    }

    public void setaMount(int aMount) {
        this.aMount = aMount;
    }

    private String yearMonth;
    private int aMount;
}
