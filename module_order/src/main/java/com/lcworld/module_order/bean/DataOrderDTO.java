package com.lcworld.module_order.bean;

import java.util.List;

public class DataOrderDTO {

    private int address_id;//收货地址id
    private String admin_remark;//管理员备注
    private String cancel_reason;//取消原因
    private String client_type;//订单来源
    private String comment_status;//评论状态
    private DataConsigneeVo ConsigneeVO;
    private List<DataCouponVo> coupon_list;//已使用的优惠卷列表
    private long create_time;//创建时间
    private int disabled;//是否已经删除
    private List<DataCouponVo> gift_coupon_list;//赠送优惠卷列表
    private List<DataGiftVo> gift_list;//赠品列表
    private int gift_point;//赠送积分
    private int goods_num;//商品数量
    private int invalid;//是否失效：0:正常 1:已失效
    private int logi_id;//物流公司Id
    private String logi_name;//物流公司名称
    private int member_id;//会员id
    private String member_name;//会员姓名
    private double need_pay_money;//应付金额
    private int need_receipt;//是否需要发票
    private double order_price;//订单价格
    private String order_status;//订单状态
    private String pay_status;//付款状态
    private String payment_account;//付款账号
    private int payment_method_id;//支付方式id
    private String payment_method_name;//支付方式名称
    private String payment_plugin_id;//支付插件id
    private String payment_type;//支付方式
    private DataPriceDetailVo PriceDetailVO;//价格明细
    private String receipt_content;// 内容
    private String receipt_title;//抬头
    private DataReceiptVo receipt_vo;//发票
    private String receive_time;//发货时间类型
    private String remark;//订单备注
    private int seller_id;//卖家id
    private String seller_name;//卖家店名
    private String service_status;//售后状态
    private String ship_city;//收货地址市
    private int ship_city_id;//收货地址市Id
    private String ship_name;//收货人姓名
    private String ship_no;//发货单号
    private String ship_province;//收货省
    private int ship_province_id;//收货地址省Id
    private String ship_region;//收货地址区
    private int ship_region_id;//收货地址区Id
    private String ship_status;//配送状态
    private long ship_time;//发货时间
    private int ship_town;//收货地址街道
    private int ship_town_id;//收货地址街道Id
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

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
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

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
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
}
