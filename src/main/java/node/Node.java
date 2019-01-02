package node;

/**
 * Created by Admin on 2018/12/20.
 */
public class Node {

    private String id;

    private Integer successNum;

    private Integer totalNum;

    public Node(){
        super();
    }

    public Node(String id,Integer successNum, Integer totalNum){
        this.id = id;
        this.successNum = successNum;
        this.totalNum = totalNum;
    }

    public String getId() {
        return id;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString(){
        return id + ":" + totalNum ;
    }
}
