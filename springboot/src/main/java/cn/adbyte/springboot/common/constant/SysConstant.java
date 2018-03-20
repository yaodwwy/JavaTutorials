package cn.adbyte.springboot.common.constant;

/**
 * Created by Adam.yao on 2017/10/25.
 */
public class SysConstant {

    public static final int SUCCESS = 0;
    public static final int FAILED = -1;

    public static final int MAX_SIZE = 2000;

    public static final String SUCCESS_MSG = "成功";
    public static final String FAILED_MSG = "失败";

    public static boolean isSuccess(int code) {
        return code >= SUCCESS;
    }

    public class CacheName {
        public static final String RESOURCE = "resource";
        public static final String MEMBERROLE = "memberRole";
        public static final String SRMRESULT = "srmResult";

    }

   /* public class InquiryMsg {
        public static final String department_error = "部门选择不正确";
        public static final String goods_num_error = "商品数量不能小于或等于0";
        public static final String no_right = "对不起,您没有权利操作";
        public static final String submit_success = "提交成功";
        public static final String submit_error = "提交失败";
        public static final String save_success = "保存成功";
        public static final String save_error = "保存失败";
        public static final String remove_success = "删除成功";
        public static final String remove_error = "删除失败";
        public static final String update_success = "修改成功";
        public static final String update_error = "修改失败";
        public static final String get_error = "未get到你的信息";
        public static final String goods_not_found_error = "请添加商品";
        public static final String cancel_success = "取消成功";
        public static final String cancel_error = "取消失败";
        public static final String quote_freight_limit_error = "运费输入不正确";
        public static final String null_error = "请输入必填项";
        public static final String quote_min_time_strategy_error = "报价最小时间不能小于30天";
        public static final String web_error = "页面异常,请重新操作";
        public static final String expire_error = "询价截止日期不能小于等于当前时间";
        public static final String package_num_error = "包装商品数量不能小于或等于0";
        public static final String category_null_error = "请选择平台类目";
    }*/

}
