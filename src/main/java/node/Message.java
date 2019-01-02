package node;

/**
 * Created by Admin on 2018/12/20.
 * 消息类
 */
public class Message {

    private String id;

    private String fromId;

    private String toId;

    private Boolean isSucc;

    public Message(){
        super();
    }

    public Message(String id, String fromId, String toId, Boolean isSucc){
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.isSucc = isSucc;
    }

    public String getId() {
        return id;
    }

    public String getFromId() {
        return fromId;
    }

    public String getToId() {
        return toId;
    }

    public Boolean getSucc() {
        return isSucc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public void setSucc(Boolean succ) {
        isSucc = succ;
    }

    @Override
    public String toString(){
        //节点id:fromId-->toId，isSucc
        return this.id + ":" + this.fromId + "-->" + this.toId + ","+this.isSucc;
    }
}
