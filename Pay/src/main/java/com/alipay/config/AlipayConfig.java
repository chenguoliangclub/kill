package com.alipay.config;

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
    public static String app_id = "2016092800617693";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCNh8M0x6yJz0zG7UbUm4C4+d4cP4Z0YZ6DiCmC6/PaAgnIVPbGxCNMf6NNqclx8aLKnnJVKTxI6k20F4iyEnHZ18PID17EW8Hl2JXH0wzL7W4EF1wySSwNPD0sqO58zj9zqzKkIFrSoX2awIFunFlcmu8rP95u9Kz0Doz8WQKGqrlygOxmvJw+YZYenG5y75KqkUuxPNR09AOo4IcpdTNQ7+YELTWffOSJEUespGPZ3zO8UdnVjOl/t2ZLsQUYNPLbcNvLNwx+poQwmg5tptgw0nuawxCaw1M3DOjoiZs2NbebYjfC86XlC6v41SDJU2f4RrHnH8U0/o+7xxQ+/GmrAgMBAAECggEBAIfqXfuc2cRKcOgEy+W3JEn/o16rXtr7OHgGVh8lqzusg2qAtbCygVegp9oYeJ1VOUmuGgxQmlVHtbGCfELOVcmnXJwOrqqRl8a8VTLeKkbYz7NcH60m+ggH1pPB862lf+f7+5kEfVQTxATOZD4uJd855v84ycrya/nA04IvlK5va+t2dJH5Tm90E3sSLimst5Zk1d6B2huTvdWTiJTuM0JJW5teywtT2PzRA0tFcL8PqfUUHX5CxT8moLrpLQg9HHzeMr18KDQqOIsVqWgkfYtdzkJyC1LCcSZkBcGGgUW8eu4WLjYJVonBo5gUPPSkLZ0hiUsgPja6UDk5nziJB0ECgYEA1KLxjOiGvhjsCePCnxzaLqjbtsmAOlol6w4DRbz6viy0AKDTBKesNt4ekvmDf6L91X3HR1bkgapVvhFIyadVBhaNUDWyZnyx+ByNb8EnY36knyJRu8eJ7QHNjsAwsJ2+eqzYhvfvvWGCjmCMMiUOgO/qATRpVindI2LdIwtg2CECgYEAqmSZFQfP+UYbfgV0CeKjeTfy0EpKb24hL8GWAOPYiQ/9xMFS0bWYgQ1Ug5IRhSQp8QPd0AHGSKj596c4BQcJRdH8186uE+yKugtosfaGGGp2a3wfYyGp+0XE6fnlDYKxcSxYDpkkA+Z6cDyTBcx9FzhD3kW8iKHzD7bvpKIaGEsCgYBc9XtaWdGGS8jtLehIvUlZOrMLPx5S5FRNZYvishKAj3SGDckS8Oal+gctw+1MIIqOCfXT51XwpfHkLRRALGpMRhGzbjY2/e6kBH+EytGYeqYnd855pGtU9QPTk4v1upCqeuQSZVQm2/lCm0zPI0oq7jfEHRCr/ZNSxAbfKjtFYQKBgHpjMXVinCZfDeYxwoxLJsItxpGHmvl4sAueT8Ep5uFkBBGsDnGkrvqa+YZbh4wLUrjT3yHF9IdCH/GbQWiTCj0FPEhCItUaxPYmtbeF7AFjjtVbXSwWBQL/tRWeMcDHi+5PPJyXsL/Ab1domu3CWGMyPcVMb8PwFybFblHhQT8LAoGAflTuFMiCedVn73tfjk4x5TxV1eMELlK0RYWcjLCBGcCYgU+xr5qG4F6RuyVMloFcxOtwTv1mmHVNmuQjQSDoHT5UO0W8NBz/6SkliWgMhKBNX0p3gBp+KHE58l+GJ1ZhDftoI/0Aj6xZjNRPKXg3NbtOfBS79iMGPi1Q2jfZm5g=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAztuqV2LO1UKUsS1TedNZnh9SD2t/8Z2JUr0mlaLKSyA7FfjgjQfK+VKFQEAaZ7HXQKrfF53+cJ18l6qUsWEj4JfxWVWItk6JySd1Mr75kXmTa3n4bUKBMdLVPlOszv9aA7rNr/s+p25ihSs/32f6MWacPo9K+qW/MGLG6sj5PctB8d4G+P4x2YgUkrMP3VYDd2NR3UyviMcwWpT5HVdSuD10R5k+alTzbiR9bRU2sIGNsJn275Xo2Hi6ulHJNTBQkhQwTIJQxt9ZK3dkozkxjUXjOoPpPewUNhwWQ3vIIxj6dWExZADIATVprQfGwsZ1uk/2D3ntQXyK/da29w30vQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://d2r3123872.iok.la/pay_notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://d2r3123872.iok.la/pay_return";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


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

