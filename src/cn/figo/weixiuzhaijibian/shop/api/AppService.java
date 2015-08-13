package cn.figo.weixiuzhaijibian.shop.api;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;
import cn.figo.weixiuzhaijibian.shop.callback.BaseCallback;
import cn.figo.weixiuzhaijibian.shop.model.AdvertiseResponse;
import cn.figo.weixiuzhaijibian.shop.model.AreaResponse;
import cn.figo.weixiuzhaijibian.shop.model.BaseResponse;
import cn.figo.weixiuzhaijibian.shop.model.CompanyInfomationResponse;
import cn.figo.weixiuzhaijibian.shop.model.FeedBack;
import cn.figo.weixiuzhaijibian.shop.model.ImagePathResponse;
import cn.figo.weixiuzhaijibian.shop.model.LicenseTypeResponse;
import cn.figo.weixiuzhaijibian.shop.model.MailCode;
import cn.figo.weixiuzhaijibian.shop.model.MasterResponse;
import cn.figo.weixiuzhaijibian.shop.model.OrderLimitResponse;
import cn.figo.weixiuzhaijibian.shop.model.OrderResponse;
import cn.figo.weixiuzhaijibian.shop.model.OrderSTPriceResponse;
import cn.figo.weixiuzhaijibian.shop.model.ServiceTypeResponse;
import cn.figo.weixiuzhaijibian.shop.model.SystemMsgResponse;
import cn.figo.weixiuzhaijibian.shop.model.UnReadMsgCountResponse;
import cn.figo.weixiuzhaijibian.shop.model.UpdatePwd;
import cn.figo.weixiuzhaijibian.shop.model.VersionUpdateResponse;

/**
 * 访问网络数据接口
 * 
 */
public interface AppService {

	public static final String PREFIX = "/appuser/";

	/**
	 * 获取广告
	 * 
	 * @param mcBelong
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getAd.do")
	public void getAd(@Field("adBelong") Integer mcBelong,
			BaseCallback<AdvertiseResponse> baseCallback);

	/**
	 * 验证邮箱
	 * 
	 * @param signature
	 * @param appid
	 * @param timestampb
	 * @param lol
	 * @param message
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getMailCode.do")
	public void getMailCode(@Field("mcBelong") Integer mcBelong,
			@Field("loginMail") String loginMail,
			BaseCallback<MailCode> baseCallback);

	/**
	 * 师傅端注册接口
	 * 
	 * @param loginMail
	 * @param mailCode
	 * @param mPwd
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/masterRegist.do")
	public void masterRegist(@Field("loginMail") String loginMail,
			@Field("mailCode") String mailCode, @Field("mPwd") String mPwd,
			BaseCallback<MasterResponse> baseCallback);

	/**
	 * 师傅端登录接口
	 * 
	 * @param loginMail
	 * @param mPwd
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/masterLogin.do")
	public void masterLogin(@Field("loginMail") String loginMail,
			@Field("mPwd") String mPwd,
			BaseCallback<MasterResponse> baseCallback);

	/**
	 * 师傅端修改密码接口
	 * 
	 * @param mID
	 * @param oldPwd
	 * @param newdPwd
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/masterUpdatePwd.do")
	public void masterUpdatePwd(@Field("mID") String mID,
			@Field("oldPwd") String oldPwd, @Field("newPwd") String newPwd,
			BaseCallback<UpdatePwd> baseCallback);


	/**
	 * 获取APP版本信息接口
	 * 
	 * @param auAppType
	 * @param auVersionCode
	 * @param auVersionName
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getAppUpdate.do")
	public void getAppUpdate(@Field("auAppType") Integer auAppType,
			@Field("auVersionCode") Integer auVersionCode,
			@Field("auVersionName") String auVersionName,
			BaseCallback<VersionUpdateResponse> baseCallback);

	/**
	 * 获取公司信息接口
	 * 
	 * @param baseCallback
	 */
	@POST(PREFIX + "app/getCompany.do")
	public void getCompany(BaseCallback<CompanyInfomationResponse> baseCallback);

	/**
	 * 投诉建议接口
	 * 
	 * @param uID
	 * @param fbContent
	 * @param fbOrderNO
	 * @param fbUserName
	 * @param fbUserTel
	 * @param fbUserType
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/feedback.do")
	public void feedback(@Field("uID") String uID,
			@Field("fbContent") String fbContent,
			@Field("fbOrderNO") String fbOrderNO,
			@Field("fbUserName") String fbUserName,
			@Field("fbUserTel") String fbUserTel,
			@Field("fbUserType") Integer fbUserType,
			BaseCallback<FeedBack> baseCallback);

	/**
	 * 师傅获取所辖区域内自己服务范围内的用户维修需求列表接口
	 * 
	 * @param mID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getUserDemand.do")
	public void getUserDemand(@Field("mID") String mID,
			@Field("page") Integer page,
			@Field("pageSize") Integer pageSize,
			BaseCallback<OrderResponse> baseCallback);

	
	
	
	
	/**未接订单详情接口
	 * @param oID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getUserDemandDetail.do")
	public void getUserDemandDetail(@Field("oID") String oID,
			BaseCallback<OrderResponse> baseCallback);
	
	
	
	
	/**师傅对用户维修需求提交报价接口
	 * @param oID
	 * @param mID
	 * @param mpContent
	 * @param mpLowPrice
	 * @param mpHeightPrice
	 * @param mpServiceType
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/submitMasterPrice.do")
	public void submitMasterPrice(@Field("oID") String oID,
			@Field("mID") String mID,
			@Field("mpContent") String mpContent,
			@Field("mpLowPrice") String mpLowPrice,
			@Field("mpHeightPrice") String mpHeightPrice,
			@Field("mpServiceType") String mpServiceType,
			BaseCallback<BaseResponse> baseCallback);
	
	
	
	
	/**师傅获取已接订单列表接口
	 * @param mID
	 * @param oStatus
	 * @param page
	 * @param pageSize
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/masterGetHistoryOrder.do")
	public void masterGetHistoryOrder(@Field("mID") String mID,
			@Field("oStatus") Integer oStatus,
			@Field("page") Integer page,
			@Field("pageSize") Integer pageSize,
			BaseCallback<OrderSTPriceResponse> baseCallback);
	
	
	/**师傅完成后确认订单接口
	 * @param oID
	 * @param oFinalPrice
	 * @param oMasterRepairContent
	 * @param oMaintenanceCycle
	 * @param oRepairedImg
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/masterConfirmOrder.do")
	public void masterConfirmOrder(@Field("oID") String oID,
			@Field("oFinalPrice") String oFinalPrice,
			@Field("oMasterRepairContent") String oMasterRepairContent,
			@Field("oMaintenanceCycle") String oMaintenanceCycle,
			@Field("oRepairedImg") String oRepairedImg,
			BaseCallback<BaseResponse> baseCallback);	
	
	
	
	
	
	
	
	
	/**
	 * 完善师傅信息接口
	 * 
	 * @param mID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getMasterInfo.do")
	public void getMasterInfo(@Field("mID") String mID,
			BaseCallback<MasterResponse> baseCallback);
	
	
	
	/**师傅修改个人信息接口（也即完善资料接口）
	 * @param mID
	 * @param mName
	 * @param mTel
	 * @param mWokYear
	 * @param mIntro
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/updateMasterInfo.do")
	public void updateMasterInfo(@Field("mID") String mID,
			@Field("mHeadImg") String mHeadImg,
			@Field("mName") String mName,
			@Field("mTel") String mTel,
			@Field("mWokYear") String mWokYear,
			@Field("mIntro") String mIntro,
			@Field("mLicenseImg") String mLicenseImg,
			BaseCallback<BaseResponse> baseCallback);

	
	
	
	
	
	
	
	
	/**
	 * 获取所有的维修服务项接口
	 * 
	 * @param baseCallback
	 */
	@POST(PREFIX + "app/getServiceType.do")
	public void getServiceType(BaseCallback<ServiceTypeResponse> baseCallback);

	/**
	 * 获取师傅对应的维修服务项接口
	 * 
	 * @param mID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getMasterServiceType.do")
	public void getMasterServiceType(@Field("mID") String mID,
			BaseCallback<ServiceTypeResponse> baseCallback);

	/**
	 * 师傅修改维修项目（修改自己可提供的服务类型）接口
	 * 
	 * @param mID
	 * @param stID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/updateMasterServiceType.do")
	public void updateMasterServiceType(@Field("mID") String mID,
			@Field("stID") String stID, BaseCallback<BaseResponse> baseCallback);

	/**
	 * 获取所有的地区接口
	 * 
	 * @param baseCallback
	 */
	@POST(PREFIX + "app/getArea.do")
	public void getArea(BaseCallback<AreaResponse> baseCallback);

	/**
	 * 师傅获取自己的服务地区（获取自己可服务的地区）接口
	 * 
	 * @param mID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getMasterArea.do")
	public void getMasterArea(@Field("mID") String mID,
			BaseCallback<AreaResponse> baseCallback);

	/**师傅修改自己的服务地区（修改自己可服务的地区）接口
	 * @param mID
	 * @param areaID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/updateMasterArea.do")
	public void updateMasterArea(@Field("mID") String mID,
			@Field("areaID") String areaID,
			BaseCallback<BaseResponse> baseCallback);

	/**
	 * 获取所有许可执照类型列表接口
	 * 
	 * @param baseCallback
	 */
	@POST(PREFIX + "app/getLicenseType.do")
	public void getLicenseType(BaseCallback<LicenseTypeResponse> baseCallback);


	
	/**师傅获取自己的许可执照列表
	 * @param mID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getMasterLicenseType.do")
	public void getMasterLicenseType(@Field("mID") String mID,
			BaseCallback<LicenseTypeResponse> baseCallback);
	
	
	/**师傅修改自己的许可执照列表
	 * @param mID
	 * @param ltID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/updateMasterLicenseType.do")
	public void updateMasterLicenseType(@Field("mID") String mID,
			@Field("ltID") String ltID,
			BaseCallback<BaseResponse> baseCallback);
	
	/**师傅上传头像
	 * @param img
	 * @param baseCallback
	 */
	@Multipart
	@POST(PREFIX + "app/uploadImg.do")
	public void uploadImg(@Part("img") TypedFile img,
			BaseCallback<ImagePathResponse> baseCallback);
	
	
	
	
	/**修改师傅用户姓名接口
	 * @param mID
	 * @param mName
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/updateMasterName.do")
	public void updateMasterName(@Field("mID") String mID,@Field("mName") String mName,
			BaseCallback<BaseResponse> baseCallback);
	
	
	
	/**修改师傅用户头像接口
	 * @param mID
	 * @param img
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/updateMasterHeadImg.do")
	public void updateMasterHeadImg(@Field("mID") String mID,@Field("mHeadImg") String mHeadImg,
			BaseCallback<BaseResponse> baseCallback);
	
	
	
	
	/**师傅获取系统消息列表（即推送消息列表）接口
	 * @param mID
	 * @param page
	 * @param pageSize
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getMasterMsg.do")
	public void getMasterMsg(@Field("mID") String mID,
			@Field("page") Integer page,
			@Field("pageSize") Integer pageSize,
			BaseCallback<SystemMsgResponse> baseCallback);
	
	
	/**获取系统消息详情接口
	 * @param mID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getMsgDetail.do")
	public void getMsgDetail(@Field("mID") String mID,
			BaseCallback<BaseResponse> baseCallback);
	
	
	
	/**获取系统消息未读的消息条目数量接口
	 * @param mID
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/masterUnReadMsgCount.do")
	public void masterUnReadMsgCount(@Field("mID") String mID,
			BaseCallback<UnReadMsgCountResponse> baseCallback);
	
	
	/**阅读消息的动作纪录接口
	 * @param mID
	 * @param rpmUserID
	 * @param rpmUserType
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/readPostMsg.do")
	public void readPostMsg(@Field("pmID") String pmID,
			@Field("rpmUserID") String rpmUserID,
			@Field("rpmUserType") String rpmUserType,
			BaseCallback<BaseResponse> baseCallback);
	
	
	/**师傅添加一个执照照片接口
	 * @param pmID
	 * @param rpmUserID
	 * @param rpmUserType
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/addMasterLicense.do")
	public void addMasterLicense(@Field("mID") String mID,
			@Field("mlImg") String mlImg,
			BaseCallback<BaseResponse> baseCallback);	
	
	
	
	/**师傅删除一个图片的操作接口
	 * @param mID
	 * @param mlImg
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/deleteMasterLicense.do")
	public void deleteMasterLicense(@Field("mID") String mID,
			@Field("mlImg") String mlImg,
			BaseCallback<BaseResponse> baseCallback);
	
	
	/**获取师傅当前未完成订单数
	 * @param mID
	 * @param mlImg
	 * @param baseCallback
	 */
	@FormUrlEncoded
	@POST(PREFIX + "app/getMasterUnfinishOrderCount.do")
	public void getMasterUnfinishOrderCount(@Field("mID") String mID,
			BaseCallback<OrderLimitResponse> baseCallback);	
	
	
	
	
}
