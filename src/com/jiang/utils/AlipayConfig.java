package com.jiang.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016090800463959";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCoy2wUKSA6heCxUkO3YCde4ebk7y0SrMhnDHMGmsrKvMEG7hS/xZaFXcQ/eNgpi9OUPx2jPv3ho6YxOxU5o7P6SjlUxb+pYkUKOyFQN2G/Bu2uajYzKTWSewIifVGN1L1HDx0jHq9qole036rBRmEZK4uoJKXFW2JlfW6jyHTGnxjCw7B+xXQL8F6a+Xh+Zvxif7ZSuwAv3OohVojjkERa74Bvy5AdN3jx9ZHQULOhLPOG/YT6a0J/iEjjlCQS7cHDx+qaliHzCxXB/FWvRQyNOlV21dfrh/qlsewwB9Zc/arK/TDSelZzLMYcEc6u4oHVz+hbCQFkK5rwkABAzH6jAgMBAAECggEAXzlDY6w/zkQwmlh6czz/ePugMo7rsplfBSN00MkKlo0dqDcv5XAmll5rlBMsf2OunPduVOX4fA7CdwuJ0daUdiLhbP2SPpB9umPZS1OzBP13oOmyYfJhkp1qeuKP25+kmC3ilil9dH7ZU+TKg/acWILEarsh+dCwFelJPYScvF+erXXCE4755IYkrj+FZjnfjfJXxKgnQbsfAJ75XMf2C8FseJjJ1/ht7SJAp3TrOeX8C1JtVo1bCD3sUTkgYZ7FJXYgQhEQJbnJkD+UDd7OjjAo5PdJvJ/ee52zAKGNq48TorOAAiHt8wDXrdGZ8/Neogu4FUnJBq8ZiA07MIkS4QKBgQD2u/FUzbEMjj/5LYtY8UNklbi2H4kyTTITl1qlSEisQHJw/wU3jMbgFcqE1tS3vv65lxgmPbn8xJ1jSQmdvSluums5YKywkfsjR607iaGU7Wh7LBqgQh3ChJnwXs8yZmuMl+DMChJ/1Nt2n1WHv5JQDRxtP26S3hgBj1ed0YOhrwKBgQCvIi7XJ3m8d7vo33vHtpQHaylIxaenbe/vokmz8T2owEjEeryinqkAhIjm9oKHicO4JEDR3X6Mgv3PUFTyxqoMfdvySMac5iGYSI7Uet51Sne03bA7GMWf1Dv4nbOgE/Oktbv2gt+nR0aEl0mkbYudEin65KPjUTPrbE62l7EzTQKBgQDJc3g+elcVXkujYmlE7G/O/2O555O4K0k4r7pF+vfDFDecRMv4qAt0yLX6sgEqKVqHI3OHPZzOYaXze53LWbgrfS+wwFsfK4G2M36gfuYb158X+lGfCXyM5oFdvbtEdlVXjGXxkkO6dYvelYda97I5hArys/jPF2DYXTX3WCo2ZwKBgHoVVRusrAMCYF5sc8f7cWNeg0/0YPOpAmpE8iXa1EWvjMpSWO7vtRXkTd7bFpDPKz1RIFJrmczvf2imMiFu9ZlxnwJdUv6Kuf6DDfX2lN6kg5/0LeZ0VUceVpPvswiy/9Pgz99+ydO9Lt487BwQNRPQVq1L9yb+Mzg4rwI/6km9AoGAEVp0IE+X5eU9ksaBZ66UCz4s0fgT5RhOoLiKZtbH5BWTKCEot7qxK9WAX3Lk2Qxn2yH5XYRRGZdhUGe97IqUEUvpaxyHt7KfOqjmeVWiMryO76r3uEwGp1RpI1/qDAGK+WhB7wtwnLwJKGBAtrrjUwMDWVxdTEjFVD7H2tq4SUY=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqMtsFCkgOoXgsVJDt2AnXuHm5O8tEqzIZwxzBprKyrzBBu4Uv8WWhV3EP3jYKYvTlD8doz794aOmMTsVOaOz+ko5VMW/qWJFCjshUDdhvwbtrmo2Myk1knsCIn1RjdS9Rw8dIx6vaqJXtN+qwUZhGSuLqCSlxVtiZX1uo8h0xp8YwsOwfsV0C/Bemvl4fmb8Yn+2UrsAL9zqIVaI45BEWu+Ab8uQHTd48fWR0FCzoSzzhv2E+mtCf4hI45QkEu3Bw8fqmpYh8wsVwfxVr0UMjTpVdtXX64f6pbHsMAfWXP2qyv0w0npWcyzGHBHOruKB1c/oWwkBZCua8JAAQMx+owIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://47.100.187.69:8080/graduation_project/aliPay";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "E:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

