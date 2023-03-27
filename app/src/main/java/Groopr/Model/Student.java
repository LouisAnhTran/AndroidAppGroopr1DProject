package Groopr.Model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentID;
    private String fullName;
    private String pillar;
    private String term;
    private String emailAddress;
    private String userName;
    private String password;
    private List<String> projectList;
    private String aboutYou;
    private String skillSet;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPillar() {
        return pillar;
    }

    public void setPillar(String pillar) {
        this.pillar = pillar;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<String> projectList) {
        this.projectList = projectList;
    }

    public String getAboutYou() {
        return aboutYou;
    }

    public void setAboutYou(String aboutYou) {
        this.aboutYou = aboutYou;
    }

    public String getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(String skillSet) {
        this.skillSet = skillSet;
    }

    public Student() {
        this.projectList=new ArrayList<String>();
    }

    public Student(String studentID, String fullName, String pillar, String term, String emailAddress, String userName, String password) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.pillar = pillar;
        this.term = term;
        this.emailAddress = emailAddress;
        this.userName = userName;
        this.password = password;
        this.projectList = new ArrayList<String>();
        this.aboutYou="";
        this.skillSet="";
        this.projectList.add("");
    }

    @NonNull
    @Override
    public String toString() {
        return this.getStudentID() + " " + this.getFullName();
    }
}
