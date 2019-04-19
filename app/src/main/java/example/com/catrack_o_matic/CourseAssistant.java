package example.com.catrack_o_matic;

public class CourseAssistant {
    private String name;

    private String netID;


    CourseAssistant(String setNetID) {
        netID = setNetID;
    }

    CourseAssistant(String setName, String setNetID) {
        name = setName;
        netID = setNetID;
    }

    public String getName() {
        return name;
    }

    public String getNetID() {
        return netID;
    }
}
