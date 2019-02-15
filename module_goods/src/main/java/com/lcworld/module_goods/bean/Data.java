package com.lcworld.module_goods.bean;

import java.util.List;

public class Data {


    /**
     * consignee : {"address":"string","city":"string","city_id":0,"consignee_id":0,"county":"string","county_id":0,"mobile":"string","name":"string","province":"string","province_id":0,"telephone":"string","town":"string","town_id":0}
     * coupon_list : [{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"}]
     * gift_list : [{"gift_id":0,"gift_name":"string","gift_num":0,"seller_id":0}]
     * member_id : 0
     * member_name : string
     * order_list : [{"address_id":0,"admin_remark":"string","cancel_reason":"string","client_type":"string","comment_status":"string","consignee":{"address":"string","city":"string","city_id":0,"consignee_id":0,"county":"string","county_id":0,"mobile":"string","name":"string","province":"string","province_id":0,"telephone":"string","town":"string","town_id":0},"coupon_list":[{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"}],"create_time":0,"disabled":0,"gift_coupon_list":[{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"}],"gift_list":[{"actual_store":0,"create_time":0,"disabled":0,"enable_store":0,"gift_img":"string","gift_name":"string","gift_price":0,"gift_type":0,"goods_id":0,"seller_id":0}],"gift_point":0,"goods_num":0,"invalid":0,"logi_id":0,"logi_name":"string","member_id":0,"member_name":"string","need_pay_money":0,"need_receipt":0,"order_price":0,"order_status":"string","pay_status":"string","payment_account":"string","payment_method_id":0,"payment_method_name":"string","payment_plugin_id":"string","payment_type":"string","price":{"cash_back":0,"coupon_price":0,"discount_price":0,"exchange_point":0,"freight_price":0,"full_minus":0,"goods_price":0,"is_free_freight":0,"original_price":0,"total_price":0},"receipt_content":"string","receipt_title":"string","receipt_vo":{"receipt_content":"string","receipt_title":"string","tax_no":"string","type":0},"receive_time":"string","remark":"string","seller_id":0,"seller_name":"string","service_status":"string","ship_city":"string","ship_city_id":0,"ship_name":"string","ship_no":"string","ship_province":"string","ship_province_id":0,"ship_region":"string","ship_region_id":0,"ship_status":"string","ship_time":0,"ship_town":"string","ship_town_id":0,"shipping_id":0,"shipping_price":0,"shipping_type":"string","shipping_type_id":0,"shipping_type_name":"string","signing_time":0,"sku_list":[{"cat_id":0,"checked":0,"enable_quantity":0,"error_message":"string","goods_id":0,"goods_image":"string","goods_type":"string","goods_weight":0,"group_list":[{"activity_id":0,"end_time":0,"is_check":0,"promotion_type":"string","remian_quantity":0,"start_time":0,"title":"string"}],"invalid":0,"is_free_freight":0,"is_ship":0,"last_modify":0,"name":"string","not_join_promotion":0,"num":0,"original_price":0,"point":0,"promotion_tags":["string"],"purchase_num":0,"purchase_price":0,"rule":{"coupon_gift":{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"},"free_shipping":true,"goods_gift":{"actual_store":0,"create_time":0,"disabled":0,"enable_store":0,"gift_img":"string","gift_name":"string","gift_price":0,"gift_type":0,"goods_id":0,"seller_id":0},"invalid":true,"invalid_reason":"string","point_gift":0,"reduced_price":0,"reduced_total_price":0,"tag":"string","target":"CART","tips":"string","use_coupon":{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"},"use_point":0},"seller_id":0,"seller_name":"string","service_status":"string","single_list":[{"activity_id":0,"end_time":0,"is_check":0,"promotion_type":"string","remian_quantity":0,"start_time":0,"title":"string"}],"sku_id":0,"sku_sn":"string","snapshot_id":0,"spec_list":[{"spec_id":0,"spec_image":"string","spec_name":"string","spec_type":0,"spec_value":"string","spec_value_id":0}],"subtotal":0,"template_id":0}],"sn":"string","the_sign":"string","trade_sn":"string","warehouse_id":0,"weight":0}]
     * payment_type : string
     * price_detail : {"cash_back":0,"coupon_price":0,"discount_price":0,"exchange_point":0,"freight_price":0,"full_minus":0,"goods_price":0,"is_free_freight":0,"original_price":0,"total_price":0}
     * trade_sn : string
     */

    private ConsigneeBean consignee;
    private int member_id;
    private String member_name;
    private String payment_type;
    private PriceDetailBean price_detail;
    private String trade_sn;
    private List<CouponListBean> coupon_list;
    private List<GiftListBean> gift_list;
    private List<OrderListBean> order_list;

    public ConsigneeBean getConsignee() {
        return consignee;
    }

    public void setConsignee(ConsigneeBean consignee) {
        this.consignee = consignee;
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

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public PriceDetailBean getPrice_detail() {
        return price_detail;
    }

    public void setPrice_detail(PriceDetailBean price_detail) {
        this.price_detail = price_detail;
    }

    public String getTrade_sn() {
        return trade_sn;
    }

    public void setTrade_sn(String trade_sn) {
        this.trade_sn = trade_sn;
    }

    public List<CouponListBean> getCoupon_list() {
        return coupon_list;
    }

    public void setCoupon_list(List<CouponListBean> coupon_list) {
        this.coupon_list = coupon_list;
    }

    public List<GiftListBean> getGift_list() {
        return gift_list;
    }

    public void setGift_list(List<GiftListBean> gift_list) {
        this.gift_list = gift_list;
    }

    public List<OrderListBean> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<OrderListBean> order_list) {
        this.order_list = order_list;
    }

    public static class ConsigneeBean {
        /**
         * address : string
         * city : string
         * city_id : 0
         * consignee_id : 0
         * county : string
         * county_id : 0
         * mobile : string
         * name : string
         * province : string
         * province_id : 0
         * telephone : string
         * town : string
         * town_id : 0
         */

        private String address;
        private String city;
        private int city_id;
        private int consignee_id;
        private String county;
        private int county_id;
        private String mobile;
        private String name;
        private String province;
        private int province_id;
        private String telephone;
        private String town;
        private int town_id;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getConsignee_id() {
            return consignee_id;
        }

        public void setConsignee_id(int consignee_id) {
            this.consignee_id = consignee_id;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public int getCounty_id() {
            return county_id;
        }

        public void setCounty_id(int county_id) {
            this.county_id = county_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public int getTown_id() {
            return town_id;
        }

        public void setTown_id(int town_id) {
            this.town_id = town_id;
        }
    }

    public static class PriceDetailBean {
        /**
         * cash_back : 0
         * coupon_price : 0
         * discount_price : 0
         * exchange_point : 0
         * freight_price : 0
         * full_minus : 0
         * goods_price : 0
         * is_free_freight : 0
         * original_price : 0
         * total_price : 0
         */

        private int cash_back;
        private int coupon_price;
        private int discount_price;
        private int exchange_point;
        private int freight_price;
        private int full_minus;
        private int goods_price;
        private int is_free_freight;
        private int original_price;
        private int total_price;

        public int getCash_back() {
            return cash_back;
        }

        public void setCash_back(int cash_back) {
            this.cash_back = cash_back;
        }

        public int getCoupon_price() {
            return coupon_price;
        }

        public void setCoupon_price(int coupon_price) {
            this.coupon_price = coupon_price;
        }

        public int getDiscount_price() {
            return discount_price;
        }

        public void setDiscount_price(int discount_price) {
            this.discount_price = discount_price;
        }

        public int getExchange_point() {
            return exchange_point;
        }

        public void setExchange_point(int exchange_point) {
            this.exchange_point = exchange_point;
        }

        public int getFreight_price() {
            return freight_price;
        }

        public void setFreight_price(int freight_price) {
            this.freight_price = freight_price;
        }

        public int getFull_minus() {
            return full_minus;
        }

        public void setFull_minus(int full_minus) {
            this.full_minus = full_minus;
        }

        public int getGoods_price() {
            return goods_price;
        }

        public void setGoods_price(int goods_price) {
            this.goods_price = goods_price;
        }

        public int getIs_free_freight() {
            return is_free_freight;
        }

        public void setIs_free_freight(int is_free_freight) {
            this.is_free_freight = is_free_freight;
        }

        public int getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(int original_price) {
            this.original_price = original_price;
        }

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }
    }

    public static class CouponListBean {
        /**
         * amount : 0
         * coupon_id : 0
         * coupon_threshold_price : 0
         * enable : 0
         * end_time : 0
         * member_coupon_id : 0
         * selected : 0
         * seller_id : 0
         * use_term : string
         */

        private int amount;
        private int coupon_id;
        private int coupon_threshold_price;
        private int enable;
        private int end_time;
        private int member_coupon_id;
        private int selected;
        private int seller_id;
        private String use_term;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(int coupon_id) {
            this.coupon_id = coupon_id;
        }

        public int getCoupon_threshold_price() {
            return coupon_threshold_price;
        }

        public void setCoupon_threshold_price(int coupon_threshold_price) {
            this.coupon_threshold_price = coupon_threshold_price;
        }

        public int getEnable() {
            return enable;
        }

        public void setEnable(int enable) {
            this.enable = enable;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }

        public int getMember_coupon_id() {
            return member_coupon_id;
        }

        public void setMember_coupon_id(int member_coupon_id) {
            this.member_coupon_id = member_coupon_id;
        }

        public int getSelected() {
            return selected;
        }

        public void setSelected(int selected) {
            this.selected = selected;
        }

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }

        public String getUse_term() {
            return use_term;
        }

        public void setUse_term(String use_term) {
            this.use_term = use_term;
        }
    }

    public static class GiftListBean {
        /**
         * gift_id : 0
         * gift_name : string
         * gift_num : 0
         * seller_id : 0
         */

        private int gift_id;
        private String gift_name;
        private int gift_num;
        private int seller_id;

        public int getGift_id() {
            return gift_id;
        }

        public void setGift_id(int gift_id) {
            this.gift_id = gift_id;
        }

        public String getGift_name() {
            return gift_name;
        }

        public void setGift_name(String gift_name) {
            this.gift_name = gift_name;
        }

        public int getGift_num() {
            return gift_num;
        }

        public void setGift_num(int gift_num) {
            this.gift_num = gift_num;
        }

        public int getSeller_id() {
            return seller_id;
        }

        public void setSeller_id(int seller_id) {
            this.seller_id = seller_id;
        }
    }

    public static class OrderListBean {
        /**
         * address_id : 0
         * admin_remark : string
         * cancel_reason : string
         * client_type : string
         * comment_status : string
         * consignee : {"address":"string","city":"string","city_id":0,"consignee_id":0,"county":"string","county_id":0,"mobile":"string","name":"string","province":"string","province_id":0,"telephone":"string","town":"string","town_id":0}
         * coupon_list : [{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"}]
         * create_time : 0
         * disabled : 0
         * gift_coupon_list : [{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"}]
         * gift_list : [{"actual_store":0,"create_time":0,"disabled":0,"enable_store":0,"gift_img":"string","gift_name":"string","gift_price":0,"gift_type":0,"goods_id":0,"seller_id":0}]
         * gift_point : 0
         * goods_num : 0
         * invalid : 0
         * logi_id : 0
         * logi_name : string
         * member_id : 0
         * member_name : string
         * need_pay_money : 0
         * need_receipt : 0
         * order_price : 0
         * order_status : string
         * pay_status : string
         * payment_account : string
         * payment_method_id : 0
         * payment_method_name : string
         * payment_plugin_id : string
         * payment_type : string
         * price : {"cash_back":0,"coupon_price":0,"discount_price":0,"exchange_point":0,"freight_price":0,"full_minus":0,"goods_price":0,"is_free_freight":0,"original_price":0,"total_price":0}
         * receipt_content : string
         * receipt_title : string
         * receipt_vo : {"receipt_content":"string","receipt_title":"string","tax_no":"string","type":0}
         * receive_time : string
         * remark : string
         * seller_id : 0
         * seller_name : string
         * service_status : string
         * ship_city : string
         * ship_city_id : 0
         * ship_name : string
         * ship_no : string
         * ship_province : string
         * ship_province_id : 0
         * ship_region : string
         * ship_region_id : 0
         * ship_status : string
         * ship_time : 0
         * ship_town : string
         * ship_town_id : 0
         * shipping_id : 0
         * shipping_price : 0
         * shipping_type : string
         * shipping_type_id : 0
         * shipping_type_name : string
         * signing_time : 0
         * sku_list : [{"cat_id":0,"checked":0,"enable_quantity":0,"error_message":"string","goods_id":0,"goods_image":"string","goods_type":"string","goods_weight":0,"group_list":[{"activity_id":0,"end_time":0,"is_check":0,"promotion_type":"string","remian_quantity":0,"start_time":0,"title":"string"}],"invalid":0,"is_free_freight":0,"is_ship":0,"last_modify":0,"name":"string","not_join_promotion":0,"num":0,"original_price":0,"point":0,"promotion_tags":["string"],"purchase_num":0,"purchase_price":0,"rule":{"coupon_gift":{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"},"free_shipping":true,"goods_gift":{"actual_store":0,"create_time":0,"disabled":0,"enable_store":0,"gift_img":"string","gift_name":"string","gift_price":0,"gift_type":0,"goods_id":0,"seller_id":0},"invalid":true,"invalid_reason":"string","point_gift":0,"reduced_price":0,"reduced_total_price":0,"tag":"string","target":"CART","tips":"string","use_coupon":{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"},"use_point":0},"seller_id":0,"seller_name":"string","service_status":"string","single_list":[{"activity_id":0,"end_time":0,"is_check":0,"promotion_type":"string","remian_quantity":0,"start_time":0,"title":"string"}],"sku_id":0,"sku_sn":"string","snapshot_id":0,"spec_list":[{"spec_id":0,"spec_image":"string","spec_name":"string","spec_type":0,"spec_value":"string","spec_value_id":0}],"subtotal":0,"template_id":0}]
         * sn : string
         * the_sign : string
         * trade_sn : string
         * warehouse_id : 0
         * weight : 0
         */

        private int address_id;
        private String admin_remark;
        private String cancel_reason;
        private String client_type;
        private String comment_status;
        private ConsigneeBeanX consignee;
        private int create_time;
        private int disabled;
        private int gift_point;
        private int goods_num;
        private int invalid;
        private int logi_id;
        private String logi_name;
        private int member_id;
        private String member_name;
        private int need_pay_money;
        private int need_receipt;
        private int order_price;
        private String order_status;
        private String pay_status;
        private String payment_account;
        private int payment_method_id;
        private String payment_method_name;
        private String payment_plugin_id;
        private String payment_type;
        private PriceBean price;
        private String receipt_content;
        private String receipt_title;
        private ReceiptVoBean receipt_vo;
        private String receive_time;
        private String remark;
        private int seller_id;
        private String seller_name;
        private String service_status;
        private String ship_city;
        private int ship_city_id;
        private String ship_name;
        private String ship_no;
        private String ship_province;
        private int ship_province_id;
        private String ship_region;
        private int ship_region_id;
        private String ship_status;
        private int ship_time;
        private String ship_town;
        private int ship_town_id;
        private int shipping_id;
        private int shipping_price;
        private String shipping_type;
        private int shipping_type_id;
        private String shipping_type_name;
        private int signing_time;
        private String sn;
        private String the_sign;
        private String trade_sn;
        private int warehouse_id;
        private int weight;
        private List<CouponListBeanX> coupon_list;
        private List<GiftCouponListBean> gift_coupon_list;
        private List<GiftListBeanX> gift_list;
        private List<SkuListBean> sku_list;

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

        public ConsigneeBeanX getConsignee() {
            return consignee;
        }

        public void setConsignee(ConsigneeBeanX consignee) {
            this.consignee = consignee;
        }

        public int getCreate_time() {
            return create_time;
        }

        public void setCreate_time(int create_time) {
            this.create_time = create_time;
        }

        public int getDisabled() {
            return disabled;
        }

        public void setDisabled(int disabled) {
            this.disabled = disabled;
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

        public int getNeed_pay_money() {
            return need_pay_money;
        }

        public void setNeed_pay_money(int need_pay_money) {
            this.need_pay_money = need_pay_money;
        }

        public int getNeed_receipt() {
            return need_receipt;
        }

        public void setNeed_receipt(int need_receipt) {
            this.need_receipt = need_receipt;
        }

        public int getOrder_price() {
            return order_price;
        }

        public void setOrder_price(int order_price) {
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

        public PriceBean getPrice() {
            return price;
        }

        public void setPrice(PriceBean price) {
            this.price = price;
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

        public ReceiptVoBean getReceipt_vo() {
            return receipt_vo;
        }

        public void setReceipt_vo(ReceiptVoBean receipt_vo) {
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

        public int getShip_time() {
            return ship_time;
        }

        public void setShip_time(int ship_time) {
            this.ship_time = ship_time;
        }

        public String getShip_town() {
            return ship_town;
        }

        public void setShip_town(String ship_town) {
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

        public int getShipping_price() {
            return shipping_price;
        }

        public void setShipping_price(int shipping_price) {
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

        public int getSigning_time() {
            return signing_time;
        }

        public void setSigning_time(int signing_time) {
            this.signing_time = signing_time;
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

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public List<CouponListBeanX> getCoupon_list() {
            return coupon_list;
        }

        public void setCoupon_list(List<CouponListBeanX> coupon_list) {
            this.coupon_list = coupon_list;
        }

        public List<GiftCouponListBean> getGift_coupon_list() {
            return gift_coupon_list;
        }

        public void setGift_coupon_list(List<GiftCouponListBean> gift_coupon_list) {
            this.gift_coupon_list = gift_coupon_list;
        }

        public List<GiftListBeanX> getGift_list() {
            return gift_list;
        }

        public void setGift_list(List<GiftListBeanX> gift_list) {
            this.gift_list = gift_list;
        }

        public List<SkuListBean> getSku_list() {
            return sku_list;
        }

        public void setSku_list(List<SkuListBean> sku_list) {
            this.sku_list = sku_list;
        }

        public static class ConsigneeBeanX {
            /**
             * address : string
             * city : string
             * city_id : 0
             * consignee_id : 0
             * county : string
             * county_id : 0
             * mobile : string
             * name : string
             * province : string
             * province_id : 0
             * telephone : string
             * town : string
             * town_id : 0
             */

            private String address;
            private String city;
            private int city_id;
            private int consignee_id;
            private String county;
            private int county_id;
            private String mobile;
            private String name;
            private String province;
            private int province_id;
            private String telephone;
            private String town;
            private int town_id;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public int getCity_id() {
                return city_id;
            }

            public void setCity_id(int city_id) {
                this.city_id = city_id;
            }

            public int getConsignee_id() {
                return consignee_id;
            }

            public void setConsignee_id(int consignee_id) {
                this.consignee_id = consignee_id;
            }

            public String getCounty() {
                return county;
            }

            public void setCounty(String county) {
                this.county = county;
            }

            public int getCounty_id() {
                return county_id;
            }

            public void setCounty_id(int county_id) {
                this.county_id = county_id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public int getProvince_id() {
                return province_id;
            }

            public void setProvince_id(int province_id) {
                this.province_id = province_id;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }

            public int getTown_id() {
                return town_id;
            }

            public void setTown_id(int town_id) {
                this.town_id = town_id;
            }
        }

        public static class PriceBean {
            /**
             * cash_back : 0
             * coupon_price : 0
             * discount_price : 0
             * exchange_point : 0
             * freight_price : 0
             * full_minus : 0
             * goods_price : 0
             * is_free_freight : 0
             * original_price : 0
             * total_price : 0
             */

            private int cash_back;
            private int coupon_price;
            private int discount_price;
            private int exchange_point;
            private int freight_price;
            private int full_minus;
            private int goods_price;
            private int is_free_freight;
            private int original_price;
            private int total_price;

            public int getCash_back() {
                return cash_back;
            }

            public void setCash_back(int cash_back) {
                this.cash_back = cash_back;
            }

            public int getCoupon_price() {
                return coupon_price;
            }

            public void setCoupon_price(int coupon_price) {
                this.coupon_price = coupon_price;
            }

            public int getDiscount_price() {
                return discount_price;
            }

            public void setDiscount_price(int discount_price) {
                this.discount_price = discount_price;
            }

            public int getExchange_point() {
                return exchange_point;
            }

            public void setExchange_point(int exchange_point) {
                this.exchange_point = exchange_point;
            }

            public int getFreight_price() {
                return freight_price;
            }

            public void setFreight_price(int freight_price) {
                this.freight_price = freight_price;
            }

            public int getFull_minus() {
                return full_minus;
            }

            public void setFull_minus(int full_minus) {
                this.full_minus = full_minus;
            }

            public int getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(int goods_price) {
                this.goods_price = goods_price;
            }

            public int getIs_free_freight() {
                return is_free_freight;
            }

            public void setIs_free_freight(int is_free_freight) {
                this.is_free_freight = is_free_freight;
            }

            public int getOriginal_price() {
                return original_price;
            }

            public void setOriginal_price(int original_price) {
                this.original_price = original_price;
            }

            public int getTotal_price() {
                return total_price;
            }

            public void setTotal_price(int total_price) {
                this.total_price = total_price;
            }
        }

        public static class ReceiptVoBean {
            /**
             * receipt_content : string
             * receipt_title : string
             * tax_no : string
             * type : 0
             */

            private String receipt_content;
            private String receipt_title;
            private String tax_no;
            private int type;

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

            public String getTax_no() {
                return tax_no;
            }

            public void setTax_no(String tax_no) {
                this.tax_no = tax_no;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class CouponListBeanX {
            /**
             * amount : 0
             * coupon_id : 0
             * coupon_threshold_price : 0
             * enable : 0
             * end_time : 0
             * member_coupon_id : 0
             * selected : 0
             * seller_id : 0
             * use_term : string
             */

            private int amount;
            private int coupon_id;
            private int coupon_threshold_price;
            private int enable;
            private int end_time;
            private int member_coupon_id;
            private int selected;
            private int seller_id;
            private String use_term;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(int coupon_id) {
                this.coupon_id = coupon_id;
            }

            public int getCoupon_threshold_price() {
                return coupon_threshold_price;
            }

            public void setCoupon_threshold_price(int coupon_threshold_price) {
                this.coupon_threshold_price = coupon_threshold_price;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getMember_coupon_id() {
                return member_coupon_id;
            }

            public void setMember_coupon_id(int member_coupon_id) {
                this.member_coupon_id = member_coupon_id;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public String getUse_term() {
                return use_term;
            }

            public void setUse_term(String use_term) {
                this.use_term = use_term;
            }
        }

        public static class GiftCouponListBean {
            /**
             * amount : 0
             * coupon_id : 0
             * coupon_threshold_price : 0
             * enable : 0
             * end_time : 0
             * member_coupon_id : 0
             * selected : 0
             * seller_id : 0
             * use_term : string
             */

            private int amount;
            private int coupon_id;
            private int coupon_threshold_price;
            private int enable;
            private int end_time;
            private int member_coupon_id;
            private int selected;
            private int seller_id;
            private String use_term;

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public int getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(int coupon_id) {
                this.coupon_id = coupon_id;
            }

            public int getCoupon_threshold_price() {
                return coupon_threshold_price;
            }

            public void setCoupon_threshold_price(int coupon_threshold_price) {
                this.coupon_threshold_price = coupon_threshold_price;
            }

            public int getEnable() {
                return enable;
            }

            public void setEnable(int enable) {
                this.enable = enable;
            }

            public int getEnd_time() {
                return end_time;
            }

            public void setEnd_time(int end_time) {
                this.end_time = end_time;
            }

            public int getMember_coupon_id() {
                return member_coupon_id;
            }

            public void setMember_coupon_id(int member_coupon_id) {
                this.member_coupon_id = member_coupon_id;
            }

            public int getSelected() {
                return selected;
            }

            public void setSelected(int selected) {
                this.selected = selected;
            }

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public String getUse_term() {
                return use_term;
            }

            public void setUse_term(String use_term) {
                this.use_term = use_term;
            }
        }

        public static class GiftListBeanX {
            /**
             * actual_store : 0
             * create_time : 0
             * disabled : 0
             * enable_store : 0
             * gift_img : string
             * gift_name : string
             * gift_price : 0
             * gift_type : 0
             * goods_id : 0
             * seller_id : 0
             */

            private int actual_store;
            private int create_time;
            private int disabled;
            private int enable_store;
            private String gift_img;
            private String gift_name;
            private int gift_price;
            private int gift_type;
            private int goods_id;
            private int seller_id;

            public int getActual_store() {
                return actual_store;
            }

            public void setActual_store(int actual_store) {
                this.actual_store = actual_store;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getDisabled() {
                return disabled;
            }

            public void setDisabled(int disabled) {
                this.disabled = disabled;
            }

            public int getEnable_store() {
                return enable_store;
            }

            public void setEnable_store(int enable_store) {
                this.enable_store = enable_store;
            }

            public String getGift_img() {
                return gift_img;
            }

            public void setGift_img(String gift_img) {
                this.gift_img = gift_img;
            }

            public String getGift_name() {
                return gift_name;
            }

            public void setGift_name(String gift_name) {
                this.gift_name = gift_name;
            }

            public int getGift_price() {
                return gift_price;
            }

            public void setGift_price(int gift_price) {
                this.gift_price = gift_price;
            }

            public int getGift_type() {
                return gift_type;
            }

            public void setGift_type(int gift_type) {
                this.gift_type = gift_type;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }
        }

        public static class SkuListBean {
            /**
             * cat_id : 0
             * checked : 0
             * enable_quantity : 0
             * error_message : string
             * goods_id : 0
             * goods_image : string
             * goods_type : string
             * goods_weight : 0
             * group_list : [{"activity_id":0,"end_time":0,"is_check":0,"promotion_type":"string","remian_quantity":0,"start_time":0,"title":"string"}]
             * invalid : 0
             * is_free_freight : 0
             * is_ship : 0
             * last_modify : 0
             * name : string
             * not_join_promotion : 0
             * num : 0
             * original_price : 0
             * point : 0
             * promotion_tags : ["string"]
             * purchase_num : 0
             * purchase_price : 0
             * rule : {"coupon_gift":{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"},"free_shipping":true,"goods_gift":{"actual_store":0,"create_time":0,"disabled":0,"enable_store":0,"gift_img":"string","gift_name":"string","gift_price":0,"gift_type":0,"goods_id":0,"seller_id":0},"invalid":true,"invalid_reason":"string","point_gift":0,"reduced_price":0,"reduced_total_price":0,"tag":"string","target":"CART","tips":"string","use_coupon":{"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"},"use_point":0}
             * seller_id : 0
             * seller_name : string
             * service_status : string
             * single_list : [{"activity_id":0,"end_time":0,"is_check":0,"promotion_type":"string","remian_quantity":0,"start_time":0,"title":"string"}]
             * sku_id : 0
             * sku_sn : string
             * snapshot_id : 0
             * spec_list : [{"spec_id":0,"spec_image":"string","spec_name":"string","spec_type":0,"spec_value":"string","spec_value_id":0}]
             * subtotal : 0
             * template_id : 0
             */

            private int cat_id;
            private int checked;
            private int enable_quantity;
            private String error_message;
            private int goods_id;
            private String goods_image;
            private String goods_type;
            private int goods_weight;
            private int invalid;
            private int is_free_freight;
            private int is_ship;
            private int last_modify;
            private String name;
            private int not_join_promotion;
            private int num;
            private int original_price;
            private int point;
            private int purchase_num;
            private int purchase_price;
            private RuleBean rule;
            private int seller_id;
            private String seller_name;
            private String service_status;
            private int sku_id;
            private String sku_sn;
            private int snapshot_id;
            private int subtotal;
            private int template_id;
            private List<GroupListBean> group_list;
            private List<String> promotion_tags;
            private List<SingleListBean> single_list;
            private List<SpecListBean> spec_list;

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

            public int getEnable_quantity() {
                return enable_quantity;
            }

            public void setEnable_quantity(int enable_quantity) {
                this.enable_quantity = enable_quantity;
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

            public int getGoods_weight() {
                return goods_weight;
            }

            public void setGoods_weight(int goods_weight) {
                this.goods_weight = goods_weight;
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

            public int getLast_modify() {
                return last_modify;
            }

            public void setLast_modify(int last_modify) {
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

            public int getOriginal_price() {
                return original_price;
            }

            public void setOriginal_price(int original_price) {
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

            public int getPurchase_price() {
                return purchase_price;
            }

            public void setPurchase_price(int purchase_price) {
                this.purchase_price = purchase_price;
            }

            public RuleBean getRule() {
                return rule;
            }

            public void setRule(RuleBean rule) {
                this.rule = rule;
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

            public int getSubtotal() {
                return subtotal;
            }

            public void setSubtotal(int subtotal) {
                this.subtotal = subtotal;
            }

            public int getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(int template_id) {
                this.template_id = template_id;
            }

            public List<GroupListBean> getGroup_list() {
                return group_list;
            }

            public void setGroup_list(List<GroupListBean> group_list) {
                this.group_list = group_list;
            }

            public List<String> getPromotion_tags() {
                return promotion_tags;
            }

            public void setPromotion_tags(List<String> promotion_tags) {
                this.promotion_tags = promotion_tags;
            }

            public List<SingleListBean> getSingle_list() {
                return single_list;
            }

            public void setSingle_list(List<SingleListBean> single_list) {
                this.single_list = single_list;
            }

            public List<SpecListBean> getSpec_list() {
                return spec_list;
            }

            public void setSpec_list(List<SpecListBean> spec_list) {
                this.spec_list = spec_list;
            }

            public static class RuleBean {
                /**
                 * coupon_gift : {"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"}
                 * free_shipping : true
                 * goods_gift : {"actual_store":0,"create_time":0,"disabled":0,"enable_store":0,"gift_img":"string","gift_name":"string","gift_price":0,"gift_type":0,"goods_id":0,"seller_id":0}
                 * invalid : true
                 * invalid_reason : string
                 * point_gift : 0
                 * reduced_price : 0
                 * reduced_total_price : 0
                 * tag : string
                 * target : CART
                 * tips : string
                 * use_coupon : {"amount":0,"coupon_id":0,"coupon_threshold_price":0,"enable":0,"end_time":0,"member_coupon_id":0,"selected":0,"seller_id":0,"use_term":"string"}
                 * use_point : 0
                 */

                private CouponGiftBean coupon_gift;
                private boolean free_shipping;
                private GoodsGiftBean goods_gift;
                private boolean invalid;
                private String invalid_reason;
                private int point_gift;
                private int reduced_price;
                private int reduced_total_price;
                private String tag;
                private String target;
                private String tips;
                private UseCouponBean use_coupon;
                private int use_point;

                public CouponGiftBean getCoupon_gift() {
                    return coupon_gift;
                }

                public void setCoupon_gift(CouponGiftBean coupon_gift) {
                    this.coupon_gift = coupon_gift;
                }

                public boolean isFree_shipping() {
                    return free_shipping;
                }

                public void setFree_shipping(boolean free_shipping) {
                    this.free_shipping = free_shipping;
                }

                public GoodsGiftBean getGoods_gift() {
                    return goods_gift;
                }

                public void setGoods_gift(GoodsGiftBean goods_gift) {
                    this.goods_gift = goods_gift;
                }

                public boolean isInvalid() {
                    return invalid;
                }

                public void setInvalid(boolean invalid) {
                    this.invalid = invalid;
                }

                public String getInvalid_reason() {
                    return invalid_reason;
                }

                public void setInvalid_reason(String invalid_reason) {
                    this.invalid_reason = invalid_reason;
                }

                public int getPoint_gift() {
                    return point_gift;
                }

                public void setPoint_gift(int point_gift) {
                    this.point_gift = point_gift;
                }

                public int getReduced_price() {
                    return reduced_price;
                }

                public void setReduced_price(int reduced_price) {
                    this.reduced_price = reduced_price;
                }

                public int getReduced_total_price() {
                    return reduced_total_price;
                }

                public void setReduced_total_price(int reduced_total_price) {
                    this.reduced_total_price = reduced_total_price;
                }

                public String getTag() {
                    return tag;
                }

                public void setTag(String tag) {
                    this.tag = tag;
                }

                public String getTarget() {
                    return target;
                }

                public void setTarget(String target) {
                    this.target = target;
                }

                public String getTips() {
                    return tips;
                }

                public void setTips(String tips) {
                    this.tips = tips;
                }

                public UseCouponBean getUse_coupon() {
                    return use_coupon;
                }

                public void setUse_coupon(UseCouponBean use_coupon) {
                    this.use_coupon = use_coupon;
                }

                public int getUse_point() {
                    return use_point;
                }

                public void setUse_point(int use_point) {
                    this.use_point = use_point;
                }

                public static class CouponGiftBean {
                    /**
                     * amount : 0
                     * coupon_id : 0
                     * coupon_threshold_price : 0
                     * enable : 0
                     * end_time : 0
                     * member_coupon_id : 0
                     * selected : 0
                     * seller_id : 0
                     * use_term : string
                     */

                    private int amount;
                    private int coupon_id;
                    private int coupon_threshold_price;
                    private int enable;
                    private int end_time;
                    private int member_coupon_id;
                    private int selected;
                    private int seller_id;
                    private String use_term;

                    public int getAmount() {
                        return amount;
                    }

                    public void setAmount(int amount) {
                        this.amount = amount;
                    }

                    public int getCoupon_id() {
                        return coupon_id;
                    }

                    public void setCoupon_id(int coupon_id) {
                        this.coupon_id = coupon_id;
                    }

                    public int getCoupon_threshold_price() {
                        return coupon_threshold_price;
                    }

                    public void setCoupon_threshold_price(int coupon_threshold_price) {
                        this.coupon_threshold_price = coupon_threshold_price;
                    }

                    public int getEnable() {
                        return enable;
                    }

                    public void setEnable(int enable) {
                        this.enable = enable;
                    }

                    public int getEnd_time() {
                        return end_time;
                    }

                    public void setEnd_time(int end_time) {
                        this.end_time = end_time;
                    }

                    public int getMember_coupon_id() {
                        return member_coupon_id;
                    }

                    public void setMember_coupon_id(int member_coupon_id) {
                        this.member_coupon_id = member_coupon_id;
                    }

                    public int getSelected() {
                        return selected;
                    }

                    public void setSelected(int selected) {
                        this.selected = selected;
                    }

                    public int getSeller_id() {
                        return seller_id;
                    }

                    public void setSeller_id(int seller_id) {
                        this.seller_id = seller_id;
                    }

                    public String getUse_term() {
                        return use_term;
                    }

                    public void setUse_term(String use_term) {
                        this.use_term = use_term;
                    }
                }

                public static class GoodsGiftBean {
                    /**
                     * actual_store : 0
                     * create_time : 0
                     * disabled : 0
                     * enable_store : 0
                     * gift_img : string
                     * gift_name : string
                     * gift_price : 0
                     * gift_type : 0
                     * goods_id : 0
                     * seller_id : 0
                     */

                    private int actual_store;
                    private int create_time;
                    private int disabled;
                    private int enable_store;
                    private String gift_img;
                    private String gift_name;
                    private int gift_price;
                    private int gift_type;
                    private int goods_id;
                    private int seller_id;

                    public int getActual_store() {
                        return actual_store;
                    }

                    public void setActual_store(int actual_store) {
                        this.actual_store = actual_store;
                    }

                    public int getCreate_time() {
                        return create_time;
                    }

                    public void setCreate_time(int create_time) {
                        this.create_time = create_time;
                    }

                    public int getDisabled() {
                        return disabled;
                    }

                    public void setDisabled(int disabled) {
                        this.disabled = disabled;
                    }

                    public int getEnable_store() {
                        return enable_store;
                    }

                    public void setEnable_store(int enable_store) {
                        this.enable_store = enable_store;
                    }

                    public String getGift_img() {
                        return gift_img;
                    }

                    public void setGift_img(String gift_img) {
                        this.gift_img = gift_img;
                    }

                    public String getGift_name() {
                        return gift_name;
                    }

                    public void setGift_name(String gift_name) {
                        this.gift_name = gift_name;
                    }

                    public int getGift_price() {
                        return gift_price;
                    }

                    public void setGift_price(int gift_price) {
                        this.gift_price = gift_price;
                    }

                    public int getGift_type() {
                        return gift_type;
                    }

                    public void setGift_type(int gift_type) {
                        this.gift_type = gift_type;
                    }

                    public int getGoods_id() {
                        return goods_id;
                    }

                    public void setGoods_id(int goods_id) {
                        this.goods_id = goods_id;
                    }

                    public int getSeller_id() {
                        return seller_id;
                    }

                    public void setSeller_id(int seller_id) {
                        this.seller_id = seller_id;
                    }
                }

                public static class UseCouponBean {
                    /**
                     * amount : 0
                     * coupon_id : 0
                     * coupon_threshold_price : 0
                     * enable : 0
                     * end_time : 0
                     * member_coupon_id : 0
                     * selected : 0
                     * seller_id : 0
                     * use_term : string
                     */

                    private int amount;
                    private int coupon_id;
                    private int coupon_threshold_price;
                    private int enable;
                    private int end_time;
                    private int member_coupon_id;
                    private int selected;
                    private int seller_id;
                    private String use_term;

                    public int getAmount() {
                        return amount;
                    }

                    public void setAmount(int amount) {
                        this.amount = amount;
                    }

                    public int getCoupon_id() {
                        return coupon_id;
                    }

                    public void setCoupon_id(int coupon_id) {
                        this.coupon_id = coupon_id;
                    }

                    public int getCoupon_threshold_price() {
                        return coupon_threshold_price;
                    }

                    public void setCoupon_threshold_price(int coupon_threshold_price) {
                        this.coupon_threshold_price = coupon_threshold_price;
                    }

                    public int getEnable() {
                        return enable;
                    }

                    public void setEnable(int enable) {
                        this.enable = enable;
                    }

                    public int getEnd_time() {
                        return end_time;
                    }

                    public void setEnd_time(int end_time) {
                        this.end_time = end_time;
                    }

                    public int getMember_coupon_id() {
                        return member_coupon_id;
                    }

                    public void setMember_coupon_id(int member_coupon_id) {
                        this.member_coupon_id = member_coupon_id;
                    }

                    public int getSelected() {
                        return selected;
                    }

                    public void setSelected(int selected) {
                        this.selected = selected;
                    }

                    public int getSeller_id() {
                        return seller_id;
                    }

                    public void setSeller_id(int seller_id) {
                        this.seller_id = seller_id;
                    }

                    public String getUse_term() {
                        return use_term;
                    }

                    public void setUse_term(String use_term) {
                        this.use_term = use_term;
                    }
                }
            }

            public static class GroupListBean {
                /**
                 * activity_id : 0
                 * end_time : 0
                 * is_check : 0
                 * promotion_type : string
                 * remian_quantity : 0
                 * start_time : 0
                 * title : string
                 */

                private int activity_id;
                private int end_time;
                private int is_check;
                private String promotion_type;
                private int remian_quantity;
                private int start_time;
                private String title;

                public int getActivity_id() {
                    return activity_id;
                }

                public void setActivity_id(int activity_id) {
                    this.activity_id = activity_id;
                }

                public int getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(int end_time) {
                    this.end_time = end_time;
                }

                public int getIs_check() {
                    return is_check;
                }

                public void setIs_check(int is_check) {
                    this.is_check = is_check;
                }

                public String getPromotion_type() {
                    return promotion_type;
                }

                public void setPromotion_type(String promotion_type) {
                    this.promotion_type = promotion_type;
                }

                public int getRemian_quantity() {
                    return remian_quantity;
                }

                public void setRemian_quantity(int remian_quantity) {
                    this.remian_quantity = remian_quantity;
                }

                public int getStart_time() {
                    return start_time;
                }

                public void setStart_time(int start_time) {
                    this.start_time = start_time;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class SingleListBean {
                /**
                 * activity_id : 0
                 * end_time : 0
                 * is_check : 0
                 * promotion_type : string
                 * remian_quantity : 0
                 * start_time : 0
                 * title : string
                 */

                private int activity_id;
                private int end_time;
                private int is_check;
                private String promotion_type;
                private int remian_quantity;
                private int start_time;
                private String title;

                public int getActivity_id() {
                    return activity_id;
                }

                public void setActivity_id(int activity_id) {
                    this.activity_id = activity_id;
                }

                public int getEnd_time() {
                    return end_time;
                }

                public void setEnd_time(int end_time) {
                    this.end_time = end_time;
                }

                public int getIs_check() {
                    return is_check;
                }

                public void setIs_check(int is_check) {
                    this.is_check = is_check;
                }

                public String getPromotion_type() {
                    return promotion_type;
                }

                public void setPromotion_type(String promotion_type) {
                    this.promotion_type = promotion_type;
                }

                public int getRemian_quantity() {
                    return remian_quantity;
                }

                public void setRemian_quantity(int remian_quantity) {
                    this.remian_quantity = remian_quantity;
                }

                public int getStart_time() {
                    return start_time;
                }

                public void setStart_time(int start_time) {
                    this.start_time = start_time;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class SpecListBean {
                /**
                 * spec_id : 0
                 * spec_image : string
                 * spec_name : string
                 * spec_type : 0
                 * spec_value : string
                 * spec_value_id : 0
                 */

                private int spec_id;
                private String spec_image;
                private String spec_name;
                private int spec_type;
                private String spec_value;
                private int spec_value_id;

                public int getSpec_id() {
                    return spec_id;
                }

                public void setSpec_id(int spec_id) {
                    this.spec_id = spec_id;
                }

                public String getSpec_image() {
                    return spec_image;
                }

                public void setSpec_image(String spec_image) {
                    this.spec_image = spec_image;
                }

                public String getSpec_name() {
                    return spec_name;
                }

                public void setSpec_name(String spec_name) {
                    this.spec_name = spec_name;
                }

                public int getSpec_type() {
                    return spec_type;
                }

                public void setSpec_type(int spec_type) {
                    this.spec_type = spec_type;
                }

                public String getSpec_value() {
                    return spec_value;
                }

                public void setSpec_value(String spec_value) {
                    this.spec_value = spec_value;
                }

                public int getSpec_value_id() {
                    return spec_value_id;
                }

                public void setSpec_value_id(int spec_value_id) {
                    this.spec_value_id = spec_value_id;
                }
            }
        }
    }
}
