package student;

public class Student {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private String address;
    private String phone;
    private String faculty;
    private int course;
    private String group;

    public Student(int id,String lastName,String firstName,String middleName,String birthDate,
                   String address,String phone, String faculty,int course, String group){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.faculty = faculty;
        this.course = course;
        this.group = group;
    }

    public Student(){
        this.id = 123456;
        this.lastName = "lastName";
        this.firstName = "firstName";
        this.middleName = "middleName";
        this.birthDate = "birthDate";
        this.address = "address";
        this.phone = "phone";
        this.faculty = "faculty";
        this.course = 1;
        this.group = "group";
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getLastName(){return lastName;}
    public void setLastName(String lastName){this.lastName = lastName;}

    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getMiddleName(){return middleName;}
    public void setMiddleName(String middleName){this.middleName = middleName;}

    public String getBirthDate(){return birthDate;}
    public void setBirthDate(String birthDate){this.birthDate = birthDate;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}

    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}

    public String getFaculty(){return faculty;}
    public void setFaculty(String faculty){this.faculty = faculty;}

    public int getCourse(){return course;}
    public void setCourse(int course){this.course = course;}

    public String getGroup(){return group;}
    public void setGroup(String group){this.group = group;}


    public String toString() {
        return "Student{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", faculty='" + faculty + '\'' +
                ", course=" + course +
                ", group='" + group + '\'' +
                '}';
    }

}
