package com.lcworld.module_order.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品属性信息
 */
public class DataSKUVo implements Parcelable {

    /**
     * cost : 0
     * enable_quantity : 0
     * goods_type : string
     * price : 0
     * quantity : 0
     * sn : string
     * spec_list : [{"spec_id":0,"spec_image":"string","spec_name":"string","spec_type":0,"spec_value":"string","spec_value_id":0}]
     * template_id : 0
     * version : 0
     * weight : 0
     */

    //Sku
    private int cat_id;//商品所属的分类id
    private int checked;//是否选中，要去结算
    private String error_message;//购物车商品错误消息
    private int goods_id;//商品id
    private String goods_image;//商品图片
    private String goods_type;//商品类型NORMAL普通POINT积分SHANGBI商币
    private double goods_weight;//商品重量
    private int enable_quantity;//可用库存
    private int invalid;//是否失效：0:正常 1:已失效
    private int is_free_freight;//是否免运费,1：商家承担运费（免运费） 0：买家承担运费
    private int is_ship;//是否可配送 1可配送（有货）0 不可配送（无货）
    private long last_modify;//最后修改时间
    private String name;//商品名称
    private int not_join_promotion;//不参与活动
    private int num;//购买数量
    private double original_price;//商品原价
    private int point;//使用积分
    private int purchase_num;//优惠数量数量
    private double purchase_price;//购买时的成交价
    private int seller_id;//卖家id
    private String seller_name;//卖家姓名
    private String service_status;//售后状态
    private int sku_id;//产品id
    private String sku_sn;//产品sn
    private int snapshot_id;//快照ID
    private double subtotal;//小计
    private int template_id;//运费模板id

    //GoodsSkuVO
    private double cost;//成本价格
    private double price;//商品价格
    private int quantity;//库存
    private String sn;//商品编号
    private double weight;//重量
    private int version;

    private DataPromotonRule rule;
    private List<DataSpecValueVo> spec_list;
    private List<DataCartPromotionVo> group_list;//已参与的组合活动工具列表
    private List<String> promotion_tags;//此商品需要提示给顾客的优惠标签
    private List<DataCartPromotionVo> single_list;


    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type;
    }

    public double getGoods_weight() {
        return goods_weight;
    }

    public void setGoods_weight(double goods_weight) {
        this.goods_weight = goods_weight;
    }

    public int getEnable_quantity() {
        return enable_quantity;
    }

    public void setEnable_quantity(int enable_quantity) {
        this.enable_quantity = enable_quantity;
    }

    public int getInvalid() {
        return invalid;
    }

    public void setInvalid(int invalid) {
        this.invalid = invalid;
    }

    public int getIs_free_freight() {
        return is_free_freight;
    }

    public void setIs_free_freight(int is_free_freight) {
        this.is_free_freight = is_free_freight;
    }

    public int getIs_ship() {
        return is_ship;
    }

    public void setIs_ship(int is_ship) {
        this.is_ship = is_ship;
    }

    public long getLast_modify() {
        return last_modify;
    }

    public void setLast_modify(long last_modify) {
        this.last_modify = last_modify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNot_join_promotion() {
        return not_join_promotion;
    }

    public void setNot_join_promotion(int not_join_promotion) {
        this.not_join_promotion = not_join_promotion;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPurchase_num() {
        return purchase_num;
    }

    public void setPurchase_num(int purchase_num) {
        this.purchase_num = purchase_num;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }

    public String getService_status() {
        return service_status;
    }

    public void setService_status(String service_status) {
        this.service_status = service_status;
    }

    public int getSku_id() {
        return sku_id;
    }

    public void setSku_id(int sku_id) {
        this.sku_id = sku_id;
    }

    public String getSku_sn() {
        return sku_sn;
    }

    public void setSku_sn(String sku_sn) {
        this.sku_sn = sku_sn;
    }

    public int getSnapshot_id() {
        return snapshot_id;
    }

    public void setSnapshot_id(int snapshot_id) {
        this.snapshot_id = snapshot_id;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(int template_id) {
        this.template_id = template_id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DataPromotonRule getRule() {
        return rule;
    }

    public void setRule(DataPromotonRule rule) {
        this.rule = rule;
    }

    public List<DataSpecValueVo> getSpec_list() {
        return spec_list;
    }

    public void setSpec_list(List<DataSpecValueVo> spec_list) {
        this.spec_list = spec_list;
    }

    public List<DataCartPromotionVo> getGroup_list() {
        return group_list;
    }

    public void setGroup_list(List<DataCartPromotionVo> group_list) {
        this.group_list = group_list;
    }

    public List<String> getPromotion_tags() {
        return promotion_tags;
    }

    public void setPromotion_tags(List<String> promotion_tags) {
        this.promotion_tags = promotion_tags;
    }

    public List<DataCartPromotionVo> getSingle_list() {
        return single_list;
    }

    public void setSingle_list(List<DataCartPromotionVo> single_list) {
        this.single_list = single_list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cat_id);
        dest.writeInt(this.checked);
        dest.writeString(this.error_message);
        dest.writeInt(this.goods_id);
        dest.writeString(this.goods_image);
        dest.writeString(this.goods_type);
        dest.writeDouble(this.goods_weight);
        dest.writeInt(this.enable_quantity);
        dest.writeInt(this.invalid);
        dest.writeInt(this.is_free_freight);
        dest.writeInt(this.is_ship);
        dest.writeLong(this.last_modify);
        dest.writeString(this.name);
        dest.writeInt(this.not_join_promotion);
        dest.writeInt(this.num);
        dest.writeDouble(this.original_price);
        dest.writeInt(this.point);
        dest.writeInt(this.purchase_num);
        dest.writeDouble(this.purchase_price);
        dest.writeInt(this.seller_id);
        dest.writeString(this.seller_name);
        dest.writeString(this.service_status);
        dest.writeInt(this.sku_id);
        dest.writeString(this.sku_sn);
        dest.writeInt(this.snapshot_id);
        dest.writeDouble(this.subtotal);
        dest.writeInt(this.template_id);
        dest.writeDouble(this.cost);
        dest.writeDouble(this.price);
        dest.writeInt(this.quantity);
        dest.writeString(this.sn);
        dest.writeDouble(this.weight);
        dest.writeInt(this.version);
        dest.writeParcelable(this.rule, flags);
        dest.writeList(this.spec_list);
        dest.writeList(this.group_list);
        dest.writeStringList(this.promotion_tags);
        dest.writeList(this.single_list);
    }

    public DataSKUVo() {
    }

    protected DataSKUVo(Parcel in) {
        this.cat_id = in.readInt();
        this.checked = in.readInt();
        this.error_message = in.readString();
        this.goods_id = in.readInt();
        this.goods_image = in.readString();
        this.goods_type = in.readString();
        this.goods_weight = in.readDouble();
        this.enable_quantity = in.readInt();
        this.invalid = in.readInt();
        this.is_free_freight = in.readInt();
        this.is_ship = in.readInt();
        this.last_modify = in.readLong();
        this.name = in.readString();
        this.not_join_promotion = in.readInt();
        this.num = in.readInt();
        this.original_price = in.readDouble();
        this.point = in.readInt();
        this.purchase_num = in.readInt();
        this.purchase_price = in.readDouble();
        this.seller_id = in.readInt();
        this.seller_name = in.readString();
        this.service_status = in.readString();
        this.sku_id = in.readInt();
        this.sku_sn = in.readString();
        this.snapshot_id = in.readInt();
        this.subtotal = in.readDouble();
        this.template_id = in.readInt();
        this.cost = in.readDouble();
        this.price = in.readDouble();
        this.quantity = in.readInt();
        this.sn = in.readString();
        this.weight = in.readDouble();
        this.version = in.readInt();
        this.rule = in.readParcelable(DataPromotonRule.class.getClassLoader());
        this.spec_list = new ArrayList<DataSpecValueVo>();
        in.readList(this.spec_list, DataSpecValueVo.class.getClassLoader());
        this.group_list = new ArrayList<DataCartPromotionVo>();
        in.readList(this.group_list, DataCartPromotionVo.class.getClassLoader());
        this.promotion_tags = in.createStringArrayList();
        this.single_list = new ArrayList<DataCartPromotionVo>();
        in.readList(this.single_list, DataCartPromotionVo.class.getClassLoader());
    }

    public static final Parcelable.Creator<DataSKUVo> CREATOR = new Parcelable.Creator<DataSKUVo>() {
        @Override
        public DataSKUVo createFromParcel(Parcel source) {
            return new DataSKUVo(source);
        }

        @Override
        public DataSKUVo[] newArray(int size) {
            return new DataSKUVo[size];
        }
    };
}
