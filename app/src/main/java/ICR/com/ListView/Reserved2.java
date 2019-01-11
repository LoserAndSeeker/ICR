package ICR.com.ListView;

/**
 * 已预约类，包括会议室名称，会议名称，会议id，会议日期，开始时间，结束时间
 */
public class Reserved2 {
    private String name;//会议室名称
    private String confer_name;//会议名
    private String confer_id;
    private String date;
    private String start;
    private String end;

    public Reserved2(String name,String confer_name,String confer_id,String date,String start, String end){//构造函数
        this.name = name;
        this.confer_name = confer_name;
        this.confer_id = confer_id;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public String getConfer_name() {
        return confer_name;
    }

    public String getConfer_id() {
        return confer_id;
    }

    public String getDate() {
        return date;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
