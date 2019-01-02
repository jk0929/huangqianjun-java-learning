package forwardNodes;

/**
 * Created by Admin on 2018/6/15.
 */
public class Info {

    //节点id
    private String nodeId;
    //节点转出消息个数
    private Integer toNum;
    //节点转入消息个数
    private Integer fromNum;

    private Boolean isTrue;

    public Info(){
        super();
    }

    public Info(String nodeId,Integer toNum,Integer fromNum){
        this.nodeId = nodeId;
        this.toNum = toNum;
        this.fromNum = fromNum;
        this.isTrue = true;
    }

    public Info(String nodeId,Integer toNum){
        this.nodeId = nodeId;
        this.toNum = toNum;
        this.fromNum = 0;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getToNum() {
        return toNum;
    }

    public void setToNum(Integer toNum) {
        this.toNum = toNum;
    }

    public Integer getFromNum() {
        return fromNum;
    }

    public void setFromNum(Integer fromNum) {
        this.fromNum = fromNum;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }


    @Override
    public String toString(){
        //节点id（转出个数，转入个数）
        return this.nodeId+"("+this.toNum+","+this.fromNum+")";
    }
}
