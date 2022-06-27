package com.example.demo.config;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.example.demo.util.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class AlipayConfig {
    static {
        Factory.setOptions(getOptions());
    }

    private static Config getOptions() {
        Config config = new Config();

        config.protocol = "https";

        // 沙箱环境修改为 openapi.alipaydev.com
        config.gatewayHost = "openapi.alipaydev.com";

        config.signType = "RSA2";

        config.appId = "2021000119681808";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
        config.merchantPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCYSo+9M5L3IvC9jRqRPN2fmt9xD0IC/3Olv0IZFoN1SYHpp6Za9qG1UF5BBPZXdW68BTtgjI9DA+Ywa0HAflEWePNVljwFLIAV61bwK5abZ/v7k0VhiXWWU2ar2aBgjzhGclpNBm0Y7q59XGaL1iH6lXcDqPpVHpzgmEnSzS96ULnSG34rILm1p8Dj+l4VFzz+4vCUuxF78DW7QsSqu6uHw6kpnpyHAThXd+lPljiifWCcdeRsOwZ5d1JVnjfBBrE9QfhDuohKLCB9OlsxR/ie9BnH2kTzFZdxg1jvyEpG3Z03M1pYTO/rNEp0+ekoDkI2xg4e4zKV+MOF45hVF2jjAgMBAAECggEALZuu3n/SlLbfGDVo7nQm/0SXhDmIkHNGhC36Mp8+cWK5SGuP85AHL9DI2XoN+HVP23lJpgmSYgSJ7JhCkc6CpFNGtKwQf5ArL+VSHQ1AxnvmyBn73lEXaRTDG9ZKJIDf3qEikAzLoXrhwONqRpizhmM6yFrT1agcXVreSQZwIJYTvkRwIF3TwMJ+nE4kNoMRWhCLwYagLHac+b4R6bta0yIDq7iTNuYAdF6nz0VoWEI8l8xElcHNIwUEfvPXiOo6+ST+7I4VUzHB+ZvuPJe0DcNZh2l+6H8kSqGLZs6q6gFXvSmBk5Ki6wQZT956ZfU5rBw+X/KsPW6VnxmssE1YCQKBgQDYTPEZRVUJa5zT/xUP8KVAVvhBGkYtagp11JyM5EMDvJKrRRUS+3ExYNCx4O05Y+RCR84Uq6aJC/OpV1QzMWMU/Qn1jPTUaCq24rJ+ZgA+aU29+38UQO7ehZhU1PzGrYvooCKIW8SZewdToMmCLSHkyVLE+dV4OXdLvMA+Y+IKhQKBgQC0PhctIJG1tSM7sOiV29RtGaESWGVLinjb2RjEq86xzfjAdmI9IbALNfNfr0lhxxM+krrtjke7VVsuIFCIeIDgq79wFmfERktGNmYbyUJfB/eTPDcBCTOImWiVQivyTSFRexkHIv6OKdv2FfgNSf8WO6pSan7MKf3SVgpgUhfmRwKBgQC13fvag7E8en8iwRe0j2Ji9r1GABGBneetq1b2H/c47RVlbHCLxV8nZYAUEEtmNdd242kjeDBOiv4VJJhnct8chqYZEKvK7Mr9ex8ZqCoMT/5N/2iaXv55m1q5+VuQsdsxfXOLs51ab1d5uXe1PL2C4MP3WxliPO9RfyMlUlZ9AQKBgDC07rJlMLr3vhOtVMzaYOcym9JHTOhC/TH70rOi8fIehPANjbKTXe1JoQDkb2Ia19p68EStxDidckxesySN29Xa05rIkzX5APJyVNmBY4wmrQJ+rSZ8hA6P1jEvdoy9xxkruLGzBTMJoMU5XpTIwEQTz40B9k3ZsxuZ4Kfyxz6VAoGAYQHeleVNS28o2Jbh3WUbJTybjAFHjZBcvHYOOvl0xE+u8Zc/wveX3SB8SVXjuoMbvvNtorzDoCeOHRmHphNE3Db1KJaHlA3QdK40WrWqOF1W4TrZnthdQE9fqQ9uqTPXa23VFIPn9CnufDOKX9LmWTbwaAEmLz9O5HGLwk0I2kI=";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmEqPvTOS9yLwvY0akTzdn5rfcQ9CAv9zpb9CGRaDdUmB6aemWvahtVBeQQT2V3VuvAU7YIyPQwPmMGtBwH5RFnjzVZY8BSyAFetW8CuWm2f7+5NFYYl1llNmq9mgYI84RnJaTQZtGO6ufVxmi9Yh+pV3A6j6VR6c4JhJ0s0velC50ht+KyC5tafA4/peFRc8/uLwlLsRe/A1u0LEqrurh8OpKZ6chwE4V3fpT5Y4on1gnHXkbDsGeXdSVZ43wQaxPUH4Q7qISiwgfTpbMUf4nvQZx9pE8xWXcYNY78hKRt2dNzNaWEzv6zRKdPnpKA5CNsYOHuMylfjDheOYVRdo4wIDAQAB";

        //可设置异步通知接收服务地址（可选）（该地址需要外网能够访问）
        config.notifyUrl = "";

        return config;
    }

}
