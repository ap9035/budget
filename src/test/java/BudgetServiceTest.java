
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class BudgetServiceTest {
    @Test
    public void testIllegalDate(){
        BudgetService budgetService = new BudgetService ();
        LocalDate startDate = LocalDate.parse ("20230809", DateTimeFormatter.ofPattern ("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse ("20230805", DateTimeFormatter.ofPattern ("yyyyMMdd"));
        Double result = budgetService.totalAmount (startDate, endDate);
        Assert.assertEquals ((double) result, (double)0, (double) 0);
    }

    @Test
    public void oneDay(){
        BudgetService budgetService = new BudgetService ();
        LocalDate startDate = LocalDate.parse ("20230805", DateTimeFormatter.ofPattern ("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse ("20230806", DateTimeFormatter.ofPattern ("yyyyMMdd"));
        Double result = budgetService.totalAmount (startDate, endDate);
        Assert.assertEquals ((double) result, (double)2000, (double) 0);
    }

    @Test
    public void partOfMonth(){
        BudgetService budgetService = new BudgetService ();
        LocalDate startDate = LocalDate.parse ("20230805", DateTimeFormatter.ofPattern ("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse ("20230809", DateTimeFormatter.ofPattern ("yyyyMMdd"));
        Double result = budgetService.totalAmount (startDate, endDate);
        Assert.assertEquals ((double) result, (double)5000, (double) 0);
    }

    @Test
    public void crossMonth(){
        BudgetService budgetService = new BudgetService ();
        LocalDate startDate = LocalDate.parse ("20230831", DateTimeFormatter.ofPattern ("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse ("20230902", DateTimeFormatter.ofPattern ("yyyyMMdd"));
        Double result = budgetService.totalAmount (startDate, endDate);
        Assert.assertEquals ((double) result, (double)3000, (double) 0);
    }

    @Test
    public void noData(){
        BudgetService budgetService = new BudgetService ();
        LocalDate startDate = LocalDate.parse ("20231001", DateTimeFormatter.ofPattern ("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse ("20231201", DateTimeFormatter.ofPattern ("yyyyMMdd"));
        Double result = budgetService.totalAmount (startDate, endDate);
        Assert.assertEquals ((double) result, (double)0, (double) 0);
    }
}
