package Groopr.Model;

import java.util.ArrayList;
import java.util.List;

public class ProjectSupport extends Project{
    private String projectName;

    private String projectID;
    private String moduleID;
    private String moduleName;
    private List<String> studentList;
    private int maxNumberOfMember;
    private String message;
    private String skillNeeded;

    private ArrayList<String> applicationsList;

    private String teamLeaderId;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }



    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

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


    public ArrayList<String> getApplicationsList() {
        return applicationsList;
    }

    public void setApplicationsList(ArrayList<String> applicationsList) {
        this.applicationsList = applicationsList;
    }

    public ProjectSupport() {
        this.studentList=new ArrayList<String>();
    }

    public ProjectSupport(String projectName, String moduleID, String moduleName,List<String> studentList, int maxNumberOfMember, String message, String skillNeeded, String teamLeaderID,ArrayList<String> applicationsList) {
        this.projectName = projectName;
        this.moduleID = moduleID.substring(0, 7);
        this.moduleName = moduleID;
        this.studentList = studentList;
        this.maxNumberOfMember = maxNumberOfMember;
        this.message = message;
        this.skillNeeded = skillNeeded;
        this.teamLeaderId=teamLeaderID;
        this.applicationsList=applicationsList;
    }
}
