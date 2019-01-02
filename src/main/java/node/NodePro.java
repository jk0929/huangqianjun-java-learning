package node;

/**
 * Created by Admin on 2018/12/27.
 */
public class NodePro {

    private String id;

    private Double deliveryPro;

    public NodePro(){
        super();
    }

    public NodePro(String id, Double deliveryPro){
        this.id = id;
        this.deliveryPro = deliveryPro;
    }

    public String getId() {
        return id;
    }

    public Double getDeliveryPro() {
        return deliveryPro;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryPro(Double deliveryPro) {
        this.deliveryPro = deliveryPro;
    }

    @Override
    public String toString(){
        return this.id+":"+this.deliveryPro;
    }
}
