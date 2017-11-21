package interfacetest;

/**
 * @class IFSInterfaceTest
 * @Describtion IFS��������
 * @Date 2011-09-18
 * @Author zhouyouyi
 */

import org.junit.Test;

public class InterfaceTest {

	public static final String XML_HEAD = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
	public static final String ENTER = "\r\n";

	// @Test
	// public void testTask() throws Exception {
	// String url = HttpUtil.SERVERURL
	// + "/FFireServer/Server/pcService/taskLssued";
	// String auth = HttpUtil.AUTH;
	// String methodName = "taskLssued";
	//
	// StringBuffer reqXml = new StringBuffer();
	// reqXml.append(XML_HEAD).append(ENTER);
	// reqXml.append("<taskLssued>").append(ENTER);
	// reqXml.append("<account>hgm</account>").append(ENTER);
	// reqXml.append("<receivers>'hgm','xqq','ipad'</receivers>").append(ENTER);
	// reqXml.append(
	// "<context>TaskLssued{\"nicId\":\"00-E0-4C-77-85-6A\",\"receiver\":\"hgm\",\"task\":{\"taskType\":	0,\"taskId\":\"201308191030\",\"taskName\":\"任务名\",\"startTime\":\"2013-8-16 14:00\",\"desc\":\"查看某一位置信息\","
	// +
	// "\"pointList\":[{\"latitude\":28.655951,\"longitude\":115.953663}]}}</context>")
	// .append(ENTER);
	// reqXml.append("</taskLssued>").append(ENTER);
	//
	// System.out.println("----客户端请求：" + reqXml.toString());
	// String response = HttpUtil.postMethodRequest(methodName,
	// reqXml.toString(), url, auth);
	// System.out.println("----服务器返回：" + response);
	// }

	@Test
	public void testRegist() throws Exception {
		String url = HttpUtil.SERVERURL + "/FFireServer/Server/fireService/pcSendBroadcastAudio";
		String auth = HttpUtil.AUTH;
		String methodName = "pcSendBroadcastAudio";

		StringBuffer reqXml = new StringBuffer();
		reqXml.append(XML_HEAD).append(ENTER);
		reqXml.append("<pcSendBroadcastAudioRequest>").append(ENTER);
		reqXml.append("<title>课文开始开始</title>").append(ENTER);
		reqXml.append("<message>钮扣和</message>").append(ENTER);
		reqXml.append("</pcSendBroadcastAudioRequest>").append(ENTER);

		System.out.println("----客户端请求：" + reqXml.toString());
		String response = HttpUtil.postMethodRequest(methodName, reqXml.toString(), url, auth);
		System.out.println("----服务器返回：" + response);
	}

	// @Test
	// public void testRegist() throws Exception {
	// String url = HttpUtil.SERVERURL +
	// "/ForestFireServer/Server/userService/logout";
	// String auth = HttpUtil.AUTH;
	// String methodName = "logout";
	//
	// StringBuffer reqXml = new StringBuffer();
	// reqXml.append(XML_HEAD).append(ENTER);
	// reqXml.append("<userLogout>").append(ENTER);
	// reqXml.append("<account>111111</account>").append(ENTER);
	// reqXml.append("</userLogout>").append(ENTER);
	//
	// System.out.println("----客户端请求：" + reqXml.toString());
	// String response = HttpUtil.postMethodRequest(methodName,
	// reqXml.toString(), url, auth);
	// System.out.println("----服务器返回：" + response);
	// }

	// @Test
	// public void loginXmpp() {
	// try {
	// XMPPConnection conn = XMPPConn.getConnection("192.168.0.62", 5222);
	//
	// conn.login("admin", "wsksffje");
	//
	// Chat chat =
	// conn.getChatManager().createChat("all@broadcast.192.168.0.62", new
	// MessageListener() {
	// @Override
	// public void processMessage(Chat arg0, Message message) {
	// System.out.print("----Received message:" + message.toXML());
	// }
	// });
	// chat.sendMessage("Hi Heguiming....");
	//
	// } catch (XMPPException e) {
	// e.printStackTrace();
	// }
	// }

	// @Test
	// public void testGetFireRecordList() throws Exception {
	// String url = HttpUtil.SERVERURL +
	// "/ForestFireServer/fireRecordService/getFireRecordList";
	// String auth = HttpUtil.AUTH;
	// String methodName = "login";
	//
	// StringBuffer reqXml = new StringBuffer();
	// reqXml.append(XML_HEAD).append(ENTER);
	// reqXml.append("<getFireRecord>").append(ENTER);
	// reqXml.append("<account>Heguiming</account>").append(ENTER);
	// reqXml.append("</getFireRecord>").append(ENTER);
	//
	// System.out.println("----客户端请求：" + reqXml.toString());
	// String response = HttpUtil.postMethodRequest(methodName,
	// reqXml.toString(), url, auth);
	// System.out.println("----服务器返回：" + response);
	// }

	// @Test
	// public void xmppText() {
	// try {
	// ConnectionConfiguration config = new
	// ConnectionConfiguration("192.168.0.62", 5222);
	// /** 是否启用安全验证 */
	// config.setSASLAuthenticationEnabled(true);
	// XMPPConnection connection = new XMPPConnection(config);
	// config.setCompressionEnabled(true);
	// connection.connect();
	// System.out.println("----连接XMPP服务器111111----");
	// connection.login("111111", "123456");
	//
	// Presence pre = new Presence(Presence.Type.available, "0", 1, null);
	// pre.setFrom("111111@hegm.im-connector.com");
	// pre.setTo("admin@hegm.im-connector.com");
	// connection.sendPacket(pre);
	//
	//
	// } catch (XMPPException e) {
	// e.printStackTrace();
	// }
	// }

}