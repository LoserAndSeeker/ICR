package ICR.com.ListView;

/**
* Room类，包含四个变量，一个构造函数，以及获取四个变量内容的函数
*/
public class Room2 {
    private String name;
    private String size;
    private String status;
    private String place;

    public Room2(String name,String size,String status,String place){//构造函数
        this.name = name;
        this.size = size;
        this.status = status;
        this.place = place;
    }

    public String getName(){
        return name;
    }
    public String getSize(){
        return size;
    }
    public String getStatus(){
        return status;
    }
    public String getPlace(){
        return place;
    }
    public void setStatus(String status){this.status = status;}
}

