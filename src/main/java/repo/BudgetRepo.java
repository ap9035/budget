package repo;

import bo.Budget;

import java.util.List;

public class BudgetRepo {
    public List<Budget> getAll(){
        return List.of (new Budget ("202308", 31000), new Budget ("202309", 30000));
    }
}
