package Groopr.Model;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String projectName;
    private String moduleID;
    private List<String> studentList;
    private int maxNumberOfMember;
    private String message;
    private String skillNeeded;

    private String teamLeaderId;

    public String getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(String teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public List<String> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<String> studentList) {
        this.studentList = studentList;
    }

    public int getMaxNumberOfMember() {
        return maxNumberOfMember;
    }

    public void setMaxNumberOfMember(int maxNumberOfMember) {
        this.maxNumberOfMember = maxNumberOfMember;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSkillNeeded() {
        return skillNeeded;
    }

    public void setSkillNeeded(String skillNeeded) {
        this.skillNeeded = skillNeeded;
    }

    public Project() {
        this.studentList=new ArrayList<String>();
    }

    public Project(String projectName, String moduleID, List<String> studentList, int maxNumberOfMember, String message, String skillNeeded,String teamLeaderID) {
        this.projectName = projectName;
        this.moduleID = moduleID;
        this.studentList = studentList;
        this.maxNumberOfMember = maxNumberOfMember;
        this.message = message;
        this.skillNeeded = skillNeeded;
        this.teamLeaderId=teamLeaderID;
    }
}
