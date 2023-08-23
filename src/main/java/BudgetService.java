import bo.Budget;
import repo.BudgetRepo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

public class BudgetService {

    BudgetRepo budgetRepo = new BudgetRepo ();

    public Double totalAmount(LocalDate startDate, LocalDate endDate){
        if (startDate.isAfter (endDate)){
            return (double)0;
        }
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate);
        List<String> datelist = new ArrayList<> ();
        datelist.add (startDate.format (DateTimeFormatter.ofPattern ("yyyyMMdd")));
        for (int i = 1; i <= daysDifference; i++) {
            datelist.add (startDate.plusDays (i).format (DateTimeFormatter.ofPattern ("yyyyMMdd")));
        }
        List<Budget> budgetList = budgetRepo.getAll();

        String sYM = startDate.format (DateTimeFormatter.ofPattern ("yyyyMM"));
        String eYM = endDate.format (DateTimeFormatter.ofPattern ("yyyyMM"));
        Map<String, Double> collect = budgetList.stream ().filter (budget -> {
            String yearMonth = budget.getYearMonth ();
            return Integer.parseInt (sYM) <= Integer.parseInt (yearMonth) && Integer.parseInt (eYM) >= Integer.parseInt (yearMonth);
        }).flatMap (budget -> {
            HashMap<String, Double> map = new LinkedHashMap<> ();
            int days = LocalDate.parse (budget.getYearMonth () + "01", DateTimeFormatter.ofPattern ("yyyyMMdd")).lengthOfMonth ();
            Double avg = (double) (budget.getaMount () / days);
            LocalDate firstDateOfMonth = LocalDate.parse (budget.getYearMonth () + "01", DateTimeFormatter.ofPattern ("yyyyMMdd"));
            map.put (firstDateOfMonth.format (DateTimeFormatter.ofPattern ("yyyyMMdd")), avg);
            for (int i = 1; i < days; i++) {
                map.put (firstDateOfMonth.plusDays (i).format (DateTimeFormatter.ofPattern ("yyyyMMdd")), avg);
            }
            return map.entrySet ().stream ();
        }).collect (Collectors.toMap (Map.Entry::getKey, Map.Entry::getValue));
        Double sum = (double) 0;
        for (String date:datelist){
            sum += collect.getOrDefault (date, (double)0);
        }
        return sum;
    }
}
