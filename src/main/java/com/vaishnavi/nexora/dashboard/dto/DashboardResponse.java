package com.vaishnavi.nexora.dashboard.dto;


public class DashboardResponse {


    private long totalNotes;

    private long favoriteNotes;

    private double totalExpenses;

    private double monthlyExpense;



    public DashboardResponse() {

    }



    public DashboardResponse(
            long totalNotes,
            long favoriteNotes,
            double totalExpenses,
            double monthlyExpense
    ) {

        this.totalNotes = totalNotes;
        this.favoriteNotes = favoriteNotes;
        this.totalExpenses = totalExpenses;
        this.monthlyExpense = monthlyExpense;

    }





    public long getTotalNotes() {
        return totalNotes;
    }


    public void setTotalNotes(long totalNotes) {
        this.totalNotes = totalNotes;
    }





    public long getFavoriteNotes() {
        return favoriteNotes;
    }


    public void setFavoriteNotes(long favoriteNotes) {
        this.favoriteNotes = favoriteNotes;
    }





    public double getTotalExpenses() {
        return totalExpenses;
    }


    public void setTotalExpenses(double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }





    public double getMonthlyExpense() {
        return monthlyExpense;
    }


    public void setMonthlyExpense(double monthlyExpense) {
        this.monthlyExpense = monthlyExpense;
    }

}