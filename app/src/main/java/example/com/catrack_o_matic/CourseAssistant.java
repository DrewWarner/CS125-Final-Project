package example.com.catrack_o_matic;

public class CourseAssistant implements Comparable {
    private String name;
    private String email;
    private String imageURL;
    private int shiftStart;


    CourseAssistant(String setName, String setEmail, String setImageURL, int setShiftStart) {
        name = setName;
        email = setEmail;
        imageURL = setImageURL;
        shiftStart = setShiftStart;
    }

    public String getName() {
        return name;
    }

    public String getNetID() {
        return email;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getShiftStart() {
        return shiftStart;
    }

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
