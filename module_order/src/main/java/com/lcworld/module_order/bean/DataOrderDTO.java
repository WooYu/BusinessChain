package com.lcworld.module_order.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DataOrderDTO implements Parcelable {

    private int address_id;//收货地址id
    private String admin_remark;//管理员备注
    private String bill_sn;//结算单号
    private int bill_status;//结算状态
    private double cash_back;//返现金额
    private String cancel_reason;//取消原因
    private String client_type;//订单来源
    private String comment_status;//评论状态
    private long complete_time;//完成时间
    private double  coupon_price;//优惠卷抵扣金额
    private DataConsigneeVo ConsigneeVO;
    private List<DataCouponVo> coupon_list;//已使用的优惠卷列表
    private long create_time;//创建时间
    private int disabled;//是否已经删除
    private double discount_price;//优惠金额
    private double full_minus;// 满减金额
    private List<DataCouponVo> gift_coupon;//赠送优惠卷列表
    private List<DataCouponVo> gift_coupon_list;//赠送优惠卷列表
    private List<DataGiftVo> gift_list;//赠品列表
    private int gift_point;//赠送积分
    private int goods_num;//商品数量
    private double goods_price;//商品总额
    private String  items_json;//货物列表json
    private int invalid;//是否失效：0:正常 1:已失效
    private int logi_id;//物流公司Id
    private String logi_name;//物流公司名称
    private int member_id;//会员id
    private String member_name;//会员姓名\买家账号
    private double need_pay_money;//应付金额
    private int need_receipt;//是否需要发票,0：否，1：是
    private DataOrderOperateAllowable order_operate_allowable_vo;
    private double order_price;//订单价格/订单总额
    private List<DataSKUVo> order_sku_list;//sku列表
    private String order_status;//订单状态
    private String pay_status;//付款状态
    private String order_status_text;//订单状态文字
    private double pay_money;//已支付金额
    private String pay_order_no;//支付方式返回的交易号
    private String payment_account;//付款账号
    private int payment_method_id;//支付方式id
    private String payment_name;//支付方式
    private String payment_method_name;//支付方式名称
    private String payment_plugin_id;//支付插件id
    private long payment_time;//支付时间
    private String payment_type;//支付方式
    private DataReceiptHistory receipt_history;//
    private DataPriceDetailVo PriceDetailVO;//价格明细
    private String receipt_content;// 内容
    private String receipt_title;//抬头
    private DataReceiptVo receipt_vo;//发票
    private DataReceiptVo receipt;//发票
    private String receive_time;//发货时间类型
    private String remark;//订单备注
    private int seller_id;//卖家id
    private String seller_name;//卖家店名
    private String service_status;//售后状态
    private String service_status_text;//售后状态文字
    private String ship_addr;//收货地址
    private String ship_city;//收货地址市
    private int ship_city_id;//收货地址市Id
    private String ship_county;//配送地区-城市
    private int ship_county_id;//配送地区-区(县)ID
    private String ship_mobile;//收货人手机
    private String ship_name;//收货人姓名
    private String ship_no;//发货单号
    private String ship_province;//收货省
    private int ship_province_id;//收货地址省Id
    private String ship_region;//收货地址区
    private int ship_region_id;//收货地址区Id
    private String ship_status;//配送状态
    private String ship_status_text;//发货状态文字
    private String ship_tel;//收货人电话
    private long ship_time;//发货时间
    private int ship_town;//收货地址街道
    private int ship_town_id;//收货地址街道Id
    private String ship_zip;//收货人邮编
    private int shipping_id;//配送方式
    private double shipping_price;//配送费
    private String shipping_type;//配送方式名称
    private int shipping_type_id;// 选中的配送方式id
    private String shipping_type_name;//选中的配送方式名称
    private long signing_time;//签收时间
    private List<DataSKUVo> sku_list;
    private String sn;// 订单编号
    private String the_sign;//签收人姓名
    private String trade_sn;//交易编号
    private     int use_point;//此订单使用的积分
    private int warehouse_id;//发货仓库id
    private double weight;//购物车重量


    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAdmin_remark() {
        return admin_remark;
    }

    public void setAdmin_remark(String admin_remark) {
        this.admin_remark = admin_remark;
    }

    public String getBill_sn() {
        return bill_sn;
    }

    public void setBill_sn(String bill_sn) {
        this.bill_sn = bill_sn;
    }

    public int getBill_status() {
        return bill_status;
    }

    public void setBill_status(int bill_status) {
        this.bill_status = bill_status;
    }

    public double getCash_back() {
        return cash_back;
    }

    public void setCash_back(double cash_back) {
        this.cash_back = cash_back;
    }

    public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }

    public String getClient_type() {
        return client_type;
    }

    public void setClient_type(String client_type) {
        this.client_type = client_type;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public long getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(long complete_time) {
        this.complete_time = complete_time;
    }

    public double getCoupon_price() {
        return coupon_price;
    }

    public void setCoupon_price(double coupon_price) {
        this.coupon_price = coupon_price;
    }

    public DataConsigneeVo getConsigneeVO() {
        return ConsigneeVO;
    }

    public void setConsigneeVO(DataConsigneeVo consigneeVO) {
        ConsigneeVO = consigneeVO;
    }

    public List<DataCouponVo> getCoupon_list() {
        return coupon_list;
    }

    public void setCoupon_list(List<DataCouponVo> coupon_list) {
        this.coupon_list = coupon_list;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }

    public double getFull_minus() {
        return full_minus;
    }

    public void setFull_minus(double full_minus) {
        this.full_minus = full_minus;
    }

    public List<DataCouponVo> getGift_coupon() {
        return gift_coupon;
    }

    public void setGift_coupon(List<DataCouponVo> gift_coupon) {
        this.gift_coupon = gift_coupon;
    }

    public List<DataCouponVo> getGift_coupon_list() {
        return gift_coupon_list;
    }

    public void setGift_coupon_list(List<DataCouponVo> gift_coupon_list) {
        this.gift_coupon_list = gift_coupon_list;
    }

    public List<DataGiftVo> getGift_list() {
        return gift_list;
    }

    public void setGift_list(List<DataGiftVo> gift_list) {
        this.gift_list = gift_list;
    }

    public int getGift_point() {
        return gift_point;
    }

    public void setGift_point(int gift_point) {
        this.gift_point = gift_point;
    }

    public int getGoods_num() {
        return goods_num;
    }

    public void setGoods_num(int goods_num) {
        this.goods_num = goods_num;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public String getItems_json() {
        return items_json;
    }

    public void setItems_json(String items_json) {
        this.items_json = items_json;
    }

    public int getInvalid() {
        return invalid;
    }

    public void setInvalid(int invalid) {
        this.invalid = invalid;
    }

    public int getLogi_id() {
        return logi_id;
    }

    public void setLogi_id(int logi_id) {
        this.logi_id = logi_id;
    }

    public String getLogi_name() {
        return logi_name;
    }

    public void setLogi_name(String logi_name) {
        this.logi_name = logi_name;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public double getNeed_pay_money() {
        return need_pay_money;
    }

    public void setNeed_pay_money(double need_pay_money) {
        this.need_pay_money = need_pay_money;
    }

    public int getNeed_receipt() {
        return need_receipt;
    }

    public void setNeed_receipt(int need_receipt) {
        this.need_receipt = need_receipt;
    }

    public DataOrderOperateAllowable getOrder_operate_allowable_vo() {
        return order_operate_allowable_vo;
    }

    public void setOrder_operate_allowable_vo(DataOrderOperateAllowable order_operate_allowable_vo) {
        this.order_operate_allowable_vo = order_operate_allowable_vo;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public List<DataSKUVo> getOrder_sku_list() {
        return order_sku_list;
    }

    public void setOrder_sku_list(List<DataSKUVo> order_sku_list) {
        this.order_sku_list = order_sku_list;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getOrder_status_text() {
        return order_status_text;
    }

    public void setOrder_status_text(String order_status_text) {
        this.order_status_text = order_status_text;
    }

    public double getPay_money() {
        return pay_money;
    }

    public void setPay_money(double pay_money) {
        this.pay_money = pay_money;
    }

    public String getPay_order_no() {
        return pay_order_no;
    }

    public void setPay_order_no(String pay_order_no) {
        this.pay_order_no = pay_order_no;
    }

    public String getPayment_account() {
        return payment_account;
    }

    public void setPayment_account(String payment_account) {
        this.payment_account = payment_account;
    }

    public int getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(int payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }

    public String getPayment_method_name() {
        return payment_method_name;
    }

    public void setPayment_method_name(String payment_method_name) {
        this.payment_method_name = payment_method_name;
    }

    public String getPayment_plugin_id() {
        return payment_plugin_id;
    }

    public void setPayment_plugin_id(String payment_plugin_id) {
        this.payment_plugin_id = payment_plugin_id;
    }

    public long getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(long payment_time) {
        this.payment_time = payment_time;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public DataReceiptHistory getReceipt_history() {
        return receipt_history;
    }

    public void setReceipt_history(DataReceiptHistory receipt_history) {
        this.receipt_history = receipt_history;
    }

    public DataPriceDetailVo getPriceDetailVO() {
        return PriceDetailVO;
    }

    public void setPriceDetailVO(DataPriceDetailVo priceDetailVO) {
        PriceDetailVO = priceDetailVO;
    }

    public String getReceipt_content() {
        return receipt_content;
    }

    public void setReceipt_content(String receipt_content) {
        this.receipt_content = receipt_content;
    }

    public String getReceipt_title() {
        return receipt_title;
    }

    public void setReceipt_title(String receipt_title) {
        this.receipt_title = receipt_title;
    }

    public DataReceiptVo getReceipt_vo() {
        return receipt_vo;
    }

    public void setReceipt_vo(DataReceiptVo receipt_vo) {
        this.receipt_vo = receipt_vo;
    }

    public DataReceiptVo getReceipt() {
        return receipt;
    }

    public void setReceipt(DataReceiptVo receipt) {
        this.receipt = receipt;
    }

    public String getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(String receive_time) {
        this.receive_time = receive_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getService_status_text() {
        return service_status_text;
    }

    public void setService_status_text(String service_status_text) {
        this.service_status_text = service_status_text;
    }

    public String getShip_addr() {
        return ship_addr;
    }

    public void setShip_addr(String ship_addr) {
        this.ship_addr = ship_addr;
    }

    public String getShip_city() {
        return ship_city;
    }

    public void setShip_city(String ship_city) {
        this.ship_city = ship_city;
    }

    public int getShip_city_id() {
        return ship_city_id;
    }

    public void setShip_city_id(int ship_city_id) {
        this.ship_city_id = ship_city_id;
    }

    public String getShip_county() {
        return ship_county;
    }

    public void setShip_county(String ship_county) {
        this.ship_county = ship_county;
    }

    public int getShip_county_id() {
        return ship_county_id;
    }

    public void setShip_county_id(int ship_county_id) {
        this.ship_county_id = ship_county_id;
    }

    public String getShip_mobile() {
        return ship_mobile;
    }

    public void setShip_mobile(String ship_mobile) {
        this.ship_mobile = ship_mobile;
    }

    public String getShip_name() {
        return ship_name;
    }

    public void setShip_name(String ship_name) {
        this.ship_name = ship_name;
    }

    public String getShip_no() {
        return ship_no;
    }

    public void setShip_no(String ship_no) {
        this.ship_no = ship_no;
    }

    public String getShip_province() {
        return ship_province;
    }

    public void setShip_province(String ship_province) {
        this.ship_province = ship_province;
    }

    public int getShip_province_id() {
        return ship_province_id;
    }

    public void setShip_province_id(int ship_province_id) {
        this.ship_province_id = ship_province_id;
    }

    public String getShip_region() {
        return ship_region;
    }

    public void setShip_region(String ship_region) {
        this.ship_region = ship_region;
    }

    public int getShip_region_id() {
        return ship_region_id;
    }

    public void setShip_region_id(int ship_region_id) {
        this.ship_region_id = ship_region_id;
    }

    public String getShip_status() {
        return ship_status;
    }

    public void setShip_status(String ship_status) {
        this.ship_status = ship_status;
    }

    public String getShip_status_text() {
        return ship_status_text;
    }

    public void setShip_status_text(String ship_status_text) {
        this.ship_status_text = ship_status_text;
    }

    public String getShip_tel() {
        return ship_tel;
    }

    public void setShip_tel(String ship_tel) {
        this.ship_tel = ship_tel;
    }

    public long getShip_time() {
        return ship_time;
    }

    public void setShip_time(long ship_time) {
        this.ship_time = ship_time;
    }

    public int getShip_town() {
        return ship_town;
    }

    public void setShip_town(int ship_town) {
        this.ship_town = ship_town;
    }

    public int getShip_town_id() {
        return ship_town_id;
    }

    public void setShip_town_id(int ship_town_id) {
        this.ship_town_id = ship_town_id;
    }

    public String getShip_zip() {
        return ship_zip;
    }

    public void setShip_zip(String ship_zip) {
        this.ship_zip = ship_zip;
    }

    public int getShipping_id() {
        return shipping_id;
    }

    public void setShipping_id(int shipping_id) {
        this.shipping_id = shipping_id;
    }

    public double getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(double shipping_price) {
        this.shipping_price = shipping_price;
    }

    public String getShipping_type() {
        return shipping_type;
    }

    public void setShipping_type(String shipping_type) {
        this.shipping_type = shipping_type;
    }

    public int getShipping_type_id() {
        return shipping_type_id;
    }

    public void setShipping_type_id(int shipping_type_id) {
        this.shipping_type_id = shipping_type_id;
    }

    public String getShipping_type_name() {
        return shipping_type_name;
    }

    public void setShipping_type_name(String shipping_type_name) {
        this.shipping_type_name = shipping_type_name;
    }

    public long getSigning_time() {
        return signing_time;
    }

    public void setSigning_time(long signing_time) {
        this.signing_time = signing_time;
    }

    public List<DataSKUVo> getSku_list() {
        return sku_list;
    }

    public void setSku_list(List<DataSKUVo> sku_list) {
        this.sku_list = sku_list;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getThe_sign() {
        return the_sign;
    }

    public void setThe_sign(String the_sign) {
        this.the_sign = the_sign;
    }

    public String getTrade_sn() {
        return trade_sn;
    }

    public void setTrade_sn(String trade_sn) {
        this.trade_sn = trade_sn;
    }

    public int getUse_point() {
        return use_point;
    }

    public void setUse_point(int use_point) {
        this.use_point = use_point;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.address_id);
        dest.writeString(this.admin_remark);
        dest.writeString(this.bill_sn);
        dest.writeInt(this.bill_status);
        dest.writeDouble(this.cash_back);
        dest.writeString(this.cancel_reason);
        dest.writeString(this.client_type);
        dest.writeString(this.comment_status);
        dest.writeLong(this.complete_time);
        dest.writeDouble(this.coupon_price);
        dest.writeParcelable(this.ConsigneeVO, flags);
        dest.writeTypedList(this.coupon_list);
        dest.writeLong(this.create_time);
        dest.writeInt(this.disabled);
        dest.writeDouble(this.discount_price);
        dest.writeDouble(this.full_minus);
        dest.writeTypedList(this.gift_coupon);
        dest.writeTypedList(this.gift_coupon_list);
        dest.writeTypedList(this.gift_list);
        dest.writeInt(this.gift_point);
        dest.writeInt(this.goods_num);
        dest.writeDouble(this.goods_price);
        dest.writeString(this.items_json);
        dest.writeInt(this.invalid);
        dest.writeInt(this.logi_id);
        dest.writeString(this.logi_name);
        dest.writeInt(this.member_id);
        dest.writeString(this.member_name);
        dest.writeDouble(this.need_pay_money);
        dest.writeInt(this.need_receipt);
        dest.writeParcelable(this.order_operate_allowable_vo, flags);
        dest.writeDouble(this.order_price);
        dest.writeTypedList(this.order_sku_list);
        dest.writeString(this.order_status);
        dest.writeString(this.pay_status);
        dest.writeString(this.order_status_text);
        dest.writeDouble(this.pay_money);
        dest.writeString(this.pay_order_no);
        dest.writeString(this.payment_account);
        dest.writeInt(this.payment_method_id);
        dest.writeString(this.payment_name);
        dest.writeString(this.payment_method_name);
        dest.writeString(this.payment_plugin_id);
        dest.writeLong(this.payment_time);
        dest.writeString(this.payment_type);
        dest.writeParcelable(this.receipt_history, flags);
        dest.writeParcelable(this.PriceDetailVO, flags);
        dest.writeString(this.receipt_content);
        dest.writeString(this.receipt_title);
        dest.writeParcelable(this.receipt_vo, flags);
        dest.writeParcelable(this.receipt, flags);
        dest.writeString(this.receive_time);
        dest.writeString(this.remark);
        dest.writeInt(this.seller_id);
        dest.writeString(this.seller_name);
        dest.writeString(this.service_status);
        dest.writeString(this.service_status_text);
        dest.writeString(this.ship_addr);
        dest.writeString(this.ship_city);
        dest.writeInt(this.ship_city_id);
        dest.writeString(this.ship_county);
        dest.writeInt(this.ship_county_id);
        dest.writeString(this.ship_mobile);
        dest.writeString(this.ship_name);
        dest.writeString(this.ship_no);
        dest.writeString(this.ship_province);
        dest.writeInt(this.ship_province_id);
        dest.writeString(this.ship_region);
        dest.writeInt(this.ship_region_id);
        dest.writeString(this.ship_status);
        dest.writeString(this.ship_status_text);
        dest.writeString(this.ship_tel);
        dest.writeLong(this.ship_time);
        dest.writeInt(this.ship_town);
        dest.writeInt(this.ship_town_id);
        dest.writeString(this.ship_zip);
        dest.writeInt(this.shipping_id);
        dest.writeDouble(this.shipping_price);
        dest.writeString(this.shipping_type);
        dest.writeInt(this.shipping_type_id);
        dest.writeString(this.shipping_type_name);
        dest.writeLong(this.signing_time);
        dest.writeTypedList(this.sku_list);
        dest.writeString(this.sn);
        dest.writeString(this.the_sign);
        dest.writeString(this.trade_sn);
        dest.writeInt(this.use_point);
        dest.writeInt(this.warehouse_id);
        dest.writeDouble(this.weight);
    }

    public DataOrderDTO() {
    }

    protected DataOrderDTO(Parcel in) {
        this.address_id = in.readInt();
        this.admin_remark = in.readString();
        this.bill_sn = in.readString();
        this.bill_status = in.readInt();
        this.cash_back = in.readDouble();
        this.cancel_reason = in.readString();
        this.client_type = in.readString();
        this.comment_status = in.readString();
        this.complete_time = in.readLong();
        this.coupon_price = in.readDouble();
        this.ConsigneeVO = in.readParcelable(DataConsigneeVo.class.getClassLoader());
        this.coupon_list = in.createTypedArrayList(DataCouponVo.CREATOR);
        this.create_time = in.readLong();
        this.disabled = in.readInt();
        this.discount_price = in.readDouble();
        this.full_minus = in.readDouble();
        this.gift_coupon = in.createTypedArrayList(DataCouponVo.CREATOR);
        this.gift_coupon_list = in.createTypedArrayList(DataCouponVo.CREATOR);
        this.gift_list = in.createTypedArrayList(DataGiftVo.CREATOR);
        this.gift_point = in.readInt();
        this.goods_num = in.readInt();
        this.goods_price = in.readDouble();
        this.items_json = in.readString();
        this.invalid = in.readInt();
        this.logi_id = in.readInt();
        this.logi_name = in.readString();
        this.member_id = in.readInt();
        this.member_name = in.readString();
        this.need_pay_money = in.readDouble();
        this.need_receipt = in.readInt();
        this.order_operate_allowable_vo = in.readParcelable(DataOrderOperateAllowable.class.getClassLoader());
        this.order_price = in.readDouble();
        this.order_sku_list = in.createTypedArrayList(DataSKUVo.CREATOR);
        this.order_status = in.readString();
        this.pay_status = in.readString();
        this.order_status_text = in.readString();
        this.pay_money = in.readDouble();
        this.pay_order_no = in.readString();
        this.payment_account = in.readString();
        this.payment_method_id = in.readInt();
        this.payment_name = in.readString();
        this.payment_method_name = in.readString();
        this.payment_plugin_id = in.readString();
        this.payment_time = in.readLong();
        this.payment_type = in.readString();
        this.receipt_history = in.readParcelable(DataReceiptHistory.class.getClassLoader());
        this.PriceDetailVO = in.readParcelable(DataPriceDetailVo.class.getClassLoader());
        this.receipt_content = in.readString();
        this.receipt_title = in.readString();
        this.receipt_vo = in.readParcelable(DataReceiptVo.class.getClassLoader());
        this.receipt = in.readParcelable(DataReceiptVo.class.getClassLoader());
        this.receive_time = in.readString();
        this.remark = in.readString();
        this.seller_id = in.readInt();
        this.seller_name = in.readString();
        this.service_status = in.readString();
        this.service_status_text = in.readString();
        this.ship_addr = in.readString();
        this.ship_city = in.readString();
        this.ship_city_id = in.readInt();
        this.ship_county = in.readString();
        this.ship_county_id = in.readInt();
        this.ship_mobile = in.readString();
        this.ship_name = in.readString();
        this.ship_no = in.readString();
        this.ship_province = in.readString();
        this.ship_province_id = in.readInt();
        this.ship_region = in.readString();
        this.ship_region_id = in.readInt();
        this.ship_status = in.readString();
        this.ship_status_text = in.readString();
        this.ship_tel = in.readString();
        this.ship_time = in.readLong();
        this.ship_town = in.readInt();
        this.ship_town_id = in.readInt();
        this.ship_zip = in.readString();
        this.shipping_id = in.readInt();
        this.shipping_price = in.readDouble();
        this.shipping_type = in.readString();
        this.shipping_type_id = in.readInt();
        this.shipping_type_name = in.readString();
        this.signing_time = in.readLong();
        this.sku_list = in.createTypedArrayList(DataSKUVo.CREATOR);
        this.sn = in.readString();
        this.the_sign = in.readString();
        this.trade_sn = in.readString();
        this.use_point = in.readInt();
        this.warehouse_id = in.readInt();
        this.weight = in.readDouble();
    }

    public static final Creator<DataOrderDTO> CREATOR = new Creator<DataOrderDTO>() {
        @Override
        public DataOrderDTO createFromParcel(Parcel source) {
            return new DataOrderDTO(source);
        }

        @Override
        public DataOrderDTO[] newArray(int size) {
            return new DataOrderDTO[size];
        }
    };
}
