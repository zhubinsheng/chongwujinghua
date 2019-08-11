package com.sim.chongwukongjing.ui.bean;

/**
 * @author Administrator
 */
public class LoginResult {

	/**
	 * code : 10000
	 * msg : 登录成功！
	 * data : {"phone":"13337213721","nickname":"测试用户","id":88,"headpic":"http://7niu.airmedic.cn/ludashi.jpg","mac":"xxxx","token":"efda1f635cf9e7a0fbdd002cb1a3737a"}
	 * optime : 35ms
	 */

	private String code;
	private String msg;
	private DataBean data;
	private String optime;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public DataBean getData() {
		return data;
	}

	public void setData(DataBean data) {
		this.data = data;
	}

	public String getOptime() {
		return optime;
	}

	public void setOptime(String optime) {
		this.optime = optime;
	}

	public static class DataBean {
		/**
		 * phone : 13337213721
		 * nickname : 测试用户
		 * id : 88
		 * headpic : http://7niu.airmedic.cn/ludashi.jpg
		 * mac : xxxx
		 * token : efda1f635cf9e7a0fbdd002cb1a3737a
		 */

		private String phone;
		private String nickname;
		private int id;
		private String headpic;
		private String mac;
		private String token;

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getHeadpic() {
			return headpic;
		}

		public void setHeadpic(String headpic) {
			this.headpic = headpic;
		}

		public String getMac() {
			return mac;
		}

		public void setMac(String mac) {
			this.mac = mac;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
	}
}