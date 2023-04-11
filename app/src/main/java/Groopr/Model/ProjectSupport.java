package Groopr.Model;

import java.util.ArrayList;
import java.util.List;

public class ProjectSupport extends Project{


    private String projectID;

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public ProjectSupport() {
        this.setStudentList(new ArrayList<String>());
    }

    public ProjectSupport(String projectName, String moduleID, String moduleName,List<String> studentList, int maxNumberOfMember, String message, String skillNeeded, String teamLeaderID,ArrayList<String> applicationsList) {
        super(projectName, moduleID, studentList, maxNumberOfMember, message, skillNeeded, teamLeaderID, applicationsList);
    }

    public String countNumberOfMember(){
        return "Members: "+String.valueOf(this.getStudentList().size()) + "/" +String.valueOf(this.getMaxNumberOfMember());
    }
}
