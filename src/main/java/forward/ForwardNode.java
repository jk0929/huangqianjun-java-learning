package forward;

/**
 * Created by Admin on 2018/12/30.
 */
public class ForwardNode {

    //节点id
    private String  nodeName;

    //节点转出消息个数
    private Double forward;

    //所有节点转出消息个数平均数
    private Double avgNum;

    public ForwardNode(){
        super();
    }

    public ForwardNode(String nodeName, Double forward, Double avgNum){
        this.nodeName = nodeName;
        this.forward = forward;
        this.avgNum = avgNum;
    }

    public String getNodeName() {
        return nodeName;
    }

    public Double getForward() {
        return forward;
    }

    public Double getAvgNum() {
        return avgNum;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setForward(Double forward) {
        this.forward = forward;
    }

    public void setAvgNum(Double avgNum) {
        this.avgNum = avgNum;
    }

    @Override
    public String toString(){
        return this.nodeName + ":" + this.forward + "," + this.avgNum;
    }
}
