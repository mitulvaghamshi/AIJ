package me.mitul.aij.model;

public class Branch {
    private int branchId;
    private int collegeNumber;
    private String branchName;

    public Branch() {
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int p) {
        branchId = p;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String p) {
        branchName = p;
    }

    public int getCollegeNumber() {
        return collegeNumber;
    }

    public void setCollegeNumber(int p) {
        collegeNumber = p;
    }
}
