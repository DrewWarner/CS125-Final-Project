package example.com.catrack_o_matic;

public class CourseAssistant implements Comparable {
    private String name;
    private String netID;
    private String imageURL;
    private int shiftStart;


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

    public String getImageURL() { return imageURL; }

    public int compareTo(Object other) {
        if (!(other instanceof CourseAssistant)) {
            return -1;
        }
        CourseAssistant otherAsCA = (CourseAssistant) other;
        if (this.shiftStart > otherAsCA.shiftStart) {
            return 1;
        }
        if (this.shiftStart < otherAsCA.shiftStart) {
            return -1;
        }
        return 0;
    }
}
