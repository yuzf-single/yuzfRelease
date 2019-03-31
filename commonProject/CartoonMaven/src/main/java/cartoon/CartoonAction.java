package cartoon;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cartoon.bean.CartoonBean;
import cartoon.bean.UserNeedBean;
import cartoon.util.JdbcUtil;

public class CartoonAction {
	public CartoonBean cartoon = new CartoonBean();
	public UserNeedBean need = new UserNeedBean();
	private static final Logger log = Logger.getLogger(CartoonAction.class);
	
	/**
	 * ��������
	 * @author yuzf
	 * @return
	 */
	public CartoonBean downloadCartoon() {
		JdbcUtil connection = new JdbcUtil();
		List<Object> params = new ArrayList<Object>();
		connection.getConnection();
		String sql = "select downloadUrl from tf_b_cartoon where serialNumber = ?";
		params.add(cartoon.getSerialNumber());
		try {
			List<CartoonBean> list = connection.findMoreRefResult(sql, params , CartoonBean.class);
			cartoon.setDownloadUrl(list.get(0).getDownloadUrl());
			cartoon.setRspCode("0000");
			cartoon.setRspDesc("��ѯ�ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			cartoon.setRspCode("9999");
			cartoon.setRspDesc("��ѯʧ��");
		} 
		return cartoon;
	}
	
	/**
	 * ����Ԥ��
	 * @return
	 */
	public void cartoonPreview() throws Exception {
		
	}
	
	/**
	 * �û����󣨼�¼��
	 * @return
	 */
	public void cartoonNeed() throws Exception {
		JdbcUtil connection = new JdbcUtil();
		List<Object> params = new ArrayList<Object>();
		connection.getConnection();
		String sqlId = "INSERT INTO tf_b_cartoon_suggest(USER_NAME,USER_AGE,NEED_CARTOON,USER_SUGGEST) value(?,?,?,?)";
		params.add(need.getUserName());
		params.add(need.getUserAge());
		params.add(need.getCartoonName());
		params.add(need.getUserSuggest());
		try {
			boolean returnFlag = connection.updateByPreparedStatement(sqlId, params);
			if (returnFlag == false) {
				throw new Exception("�������ݿ�ʧ�ܣ�����");
			}
		} catch (Exception e) {
			log.error("�û��������ʧ�ܣ�Exception :",e);
		}
	}
	
	
	public CartoonBean getCartoon() {
		return cartoon;
	}

	public void setCartoon(CartoonBean cartoon) {
		this.cartoon = cartoon;
	}

}
